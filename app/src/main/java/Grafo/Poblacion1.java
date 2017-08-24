/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author pako_
 */
public class Poblacion1 {
    Recorrido[] recorridosPoblacion0;
    Recorrido[] recorridosReproducidos;
    Recorrido[] recorridosMutados;
    ArrayList<Recorrido> recorridosTotales=new ArrayList<Recorrido>();
    Recorrido mejorRecorrido;
    Recorrido[] mejoresRecorridos;
    
    public Poblacion1(Recorrido[] mejoresRecorridos){
        recorridosPoblacion0=mejoresRecorridos;
        recorridosReproducidos=new Recorrido[recorridosPoblacion0.length*recorridosPoblacion0.length];
        recorridosMutados=new Recorrido[recorridosPoblacion0.length];
        cruzar();
        mutar();
    }
    
    public void mostrar(){
        System.out.println("Los mejores de la poblacion 0:");
        for(Recorrido r:recorridosPoblacion0){
            System.out.println(r.getFitness()+"|"+Arrays.toString(r.getNumerosAleatorios()));
        }
        System.out.println("Reproducidos:");
        for(Recorrido r:recorridosReproducidos){
            System.out.println(r.getFitness()+"|"+Arrays.toString(r.getNumerosAleatorios()));
        }
        System.out.println("Mutantes:");
        for(Recorrido r:recorridosMutados){
            System.out.println(r.getFitness()+"|"+Arrays.toString(r.getNumerosAleatorios()));
        }
    }
    
    private void cruzar(){
        for(int i=0;i< recorridosReproducidos.length;i++){
            for (int j=0;j<recorridosPoblacion0.length;j++){
                for (int k=0;k<recorridosPoblacion0.length;k++){
                    recorridosReproducidos[i]=reproducir(recorridosPoblacion0[j],recorridosPoblacion0[k]);
                }
            }
        }
    }
    
    private Recorrido reproducir(Recorrido padre, Recorrido madre){
        int mitadPadre=padre.getNumerosAleatorios().length/2;
        int mitadMadre=madre.getNumerosAleatorios().length/2;
        ArrayList<Integer> nuevosAleatorios=new ArrayList<Integer>();
        for(int i=0;i<mitadPadre;i++){
            nuevosAleatorios.add(padre.getAleatorio(i));
        }
        for(int j=mitadMadre;j<madre.getNumerosAleatorios().length;j++){
            nuevosAleatorios.add(madre.getAleatorio(j));
        }
        return new Recorrido(0,nuevosAleatorios);
        
    }
    
    private void mutar(){
        Random aleatorio=new Random();
        for(int i=0;i<recorridosPoblacion0.length;i++){
            Recorrido temporal=recorridosPoblacion0[i];
            int numerodeCambios=aleatorio.nextInt(temporal.getNumerosAleatorios().length);
            for(int j=0;j<numerodeCambios;j++){
                int nuevoAleatorio=aleatorio.nextInt(100);
                int indiceCambiado=aleatorio.nextInt(100)%temporal.getNumerosAleatorios().length;
                temporal.reemplazarValorAleatorios(indiceCambiado, nuevoAleatorio);
            }
            recorridosMutados[i]=temporal;
        }
    }
    
    public Recorrido[] getMutados(){
        return recorridosMutados;
    }
    
    public Recorrido[] getCruzados(){
        return recorridosReproducidos;
    }
    
    public void setRecorridos(Recorrido[] cruzados, Recorrido[] mutados){
        recorridosReproducidos=cruzados;
        recorridosMutados=mutados;
    }
    
    public Recorrido getMejorRecorrido(){
        return mejorRecorrido;
    }
    
    public void mostrarMejorRecorrido(){
        System.out.println(mejorRecorrido.getFitness()+Arrays.toString(mejorRecorrido.getNumerosAleatorios()));
    }
    
    public void calcularMejoresRecorrido(){
        Recorrido[] mejoresRecorridos=new Recorrido[5];
        for(Recorrido r:recorridosPoblacion0){recorridosTotales.add(r);}
        for(Recorrido r2:recorridosReproducidos){recorridosTotales.add(r2);}
        for(Recorrido r3:recorridosMutados){recorridosTotales.add(r3);}
        BigDecimal[] fits=new BigDecimal[recorridosTotales.size()];
        for(int i=0;i<recorridosTotales.size();i++){
            fits[i]=recorridosTotales.get(i).getFitness();
        }
        Arrays.sort(fits);
        for(int i=0;i<recorridosTotales.size();i++){
            if(fits[recorridosTotales.size()-1].compareTo(recorridosTotales.get(i).getFitness())==0){
                mejoresRecorridos[0]=recorridosTotales.get(i);
            }
            if(fits[recorridosTotales.size()-2].compareTo(recorridosTotales.get(i).getFitness())==0){
                mejoresRecorridos[1]=recorridosTotales.get(i);
            }
            if(fits[recorridosTotales.size()-3].compareTo(recorridosTotales.get(i).getFitness())==0){
                mejoresRecorridos[2]=recorridosTotales.get(i);
            }
            if(fits[recorridosTotales.size()-4].compareTo(recorridosTotales.get(i).getFitness())==0){
                mejoresRecorridos[3]=recorridosTotales.get(i);
            }
            if(fits[recorridosTotales.size()-5].compareTo(recorridosTotales.get(i).getFitness())==0){
                mejoresRecorridos[4]=recorridosTotales.get(i);
            }
        }
        mejorRecorrido=mejoresRecorridos[0];
        this.mejoresRecorridos=mejoresRecorridos;
    }
    
    public Recorrido[] getMejoresRecorridos(){
        return mejoresRecorridos;
    }
}
