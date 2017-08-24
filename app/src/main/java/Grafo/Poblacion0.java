/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author pako_
 */
public class Poblacion0 {
    private ArrayList<Recorrido> recorridos=new ArrayList<Recorrido>();
    Recorrido[] mejoresRecorridos=new Recorrido[5];
        
    public int getTamaño(){
        return recorridos.size();
    }    
    
    public void agregar(ArrayList<Integer> numerosAleatorios, double distancia ,boolean llego){
        double fit;
        if(llego==true){
            fit=10000-distancia;
        }else{
            fit=distancia;
        }
        recorridos.add(new Recorrido(fit,numerosAleatorios));
    }
    
    public void ordenar(){
        BigDecimal[] fits = new BigDecimal[recorridos.size()];
        for(int i=0;i<fits.length;i++){
            fits[i]=recorridos.get(i).getFitness();
        }
        Arrays.sort(fits);
        int inicio=fits.length-1;
        int bandera=fits.length-5;
        int medidorIndice=0;
        for(int j=inicio;j>=bandera;j--){
            for(int k=0;k<recorridos.size();k++){
                if(fits[j].compareTo(recorridos.get(k).getFitness())==0){
                    mejoresRecorridos[medidorIndice]=recorridos.get(k);
                    medidorIndice++;
                }
            }
        }
        
    }
    
    public Recorrido[] getMejoresRecorridos(){
        return mejoresRecorridos;
    }
    
    public void mostrarMejorRecorrido(){
        System.out.println(mejoresRecorridos[0].getFitness()+"|"+Arrays.toString(mejoresRecorridos[0].getNumerosAleatorios()));
    }
}
