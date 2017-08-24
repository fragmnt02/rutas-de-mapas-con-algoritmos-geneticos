/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import android.content.Context;

import com.example.pako_.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pako_
 */
public class Grafo {
    private String[] contenidos;//LISTA DE LOS NOMBRES DE LOS LUGARES
    private Nodo[] nodos;//LISTA DE TODOS LOS NODOS
    private ArrayList<Integer> aleatorios=new ArrayList<Integer>();
    private Poblacion0 p0=new Poblacion0();
    private Poblacion1 p1;
    private Context context;
    private String caden="";
    
    public Grafo(Context cont){
        this.context=cont;
        leerArchivo();
    }
    
    private void leerArchivo(){//LEE EL ARCHIVO Y CREA LOS NODOS ASI COMO LA LISTA CON LOS NOMBRES DE LOS LUGARES
        ArrayList<String> lineas = new ArrayList<String>();
        try {

            InputStream is = context.getResources().openRawResource(R.raw.ordenado);
            BufferedReader lectura = new BufferedReader(new InputStreamReader(is));
            while(lectura.ready()){
                lineas.add(lectura.readLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
        contenidos=lineas.get(0).split(",");
        int tamañoNodos=lineas.size()-1;
        nodos=new Nodo[tamañoNodos];
        for(int i=0;i<tamañoNodos;i++){
            int temp=i+1;
            nodos[i]=new Nodo(lineas.get(temp));
        }
    }

    public String[] getContenidos(){
        return contenidos;
    }

    public ArrayList<Integer> recorrer(String origen, String destino, int pasosMaximo, int cantidadPoblacion){
        ArrayList<Integer> tempo=new ArrayList<Integer>();
        int indiceOrigen=getIndice(origen);//OBTIENE INDICE ORIGEN
        int indiceDestino=getIndice(destino);//OBTIENE INDICE DESTINO
        if(indiceOrigen==indiceDestino){//VERIFICA SI EL DESTINO Y ORIGEN NO SON EL MISMO LUGAR
            tempo.add(indiceOrigen);//tempo="Origen y destino son el mismo lugar";
        }else if(nodos[indiceOrigen].esAdyacenteCon(indiceDestino)){//VERIFICA SI SON ADYACENTES
            tempo.add(indiceOrigen);
            tempo.add(indiceDestino);
        }else{//INICIA RECORRIDO ALEATORIO
            while(p0.getTamaño()<cantidadPoblacion){
                ArrayList<Integer> recorrido = new ArrayList<Integer>(); 
                int nodoActual=indiceOrigen;
                recorrido.add(nodoActual);
                int pasos=0;
                while(nodoActual!=indiceDestino || pasos<pasosMaximo){
                    nodoActual=nodoAleatorio(nodoActual);
                    recorrido.add(nodoActual);
                    pasos++;
                }
                if(nodoActual==indiceDestino){
                    p0.agregar(aleatorios,calcularDistanciaRecorrido(recorrido),true);
                }else{
                    p0.agregar(aleatorios,calcularDistanciaRecorrido(recorrido),false);
                }
                aleatorios=new ArrayList<Integer>();
            }
            System.out.println("Mejor de Población 0:");
            p0.ordenar();
            p0.mostrarMejorRecorrido();
            p1=new Poblacion1(p0.getMejoresRecorridos());
            Recorrido[] cruzados=p1.getCruzados();
            Recorrido[] mutados=p1.getMutados();
            for(int i=0;i<cruzados.length;i++){cruzados[i]=leerRecorrido(indiceOrigen,indiceDestino,cruzados[i].getNumerosAleatorios());}
            for(int j=0;j<mutados.length;j++){ mutados[j]=leerRecorrido(indiceOrigen,indiceDestino,mutados[j].getNumerosAleatorios());}
            p1.setRecorridos(cruzados, mutados);
            //leerRecorridoFinal(indiceOrigen,indiceDestino,p1.getMejorRecorrido().getNumerosAleatorios());
            p1.calcularMejoresRecorrido();
            //p1.mostrar();
            System.out.println("Mejor de Población 1:");
            p1.mostrarMejorRecorrido();
            //guardarRecorrido(indiceOrigen,indiceDestino);
            Poblacion1[] poblaciones=new Poblacion1[500];
            poblaciones[0]=p1;
            for(int i=1;i<500;i++){
                poblaciones[i]=new Poblacion1(poblaciones[i-1].getMejoresRecorridos());
                Recorrido[] cruzadosX=poblaciones[i].getCruzados();
                Recorrido[] mutadosX=poblaciones[i].getMutados();
                for(int k=0;k<cruzadosX.length;k++){cruzadosX[k]=leerRecorrido(indiceOrigen,indiceDestino,cruzadosX[k].getNumerosAleatorios());}
                for(int j=0;j<mutadosX.length;j++){ mutadosX[j]=leerRecorrido(indiceOrigen,indiceDestino,mutadosX[j].getNumerosAleatorios());}
                poblaciones[i].setRecorridos(cruzadosX, mutadosX);
                poblaciones[i].calcularMejoresRecorrido();
                int numerodepoblacion=i+1;
                System.out.println("Mejor de Población "+numerodepoblacion+":");
                poblaciones[i].mostrarMejorRecorrido();
            }
            tempo= leerRecorridoFinal(indiceOrigen,indiceDestino,poblaciones[499].getMejorRecorrido().getNumerosAleatorios());
        }
        crearCaden(tempo);
        return tempo;
    }

    private void crearCaden(ArrayList<Integer> x){
        caden="";
        BigDecimal tot=new BigDecimal(0);
        for (int i=1;i<x.size();i++){
            BigDecimal temp =new BigDecimal(nodos[x.get(i-1)].getDistanciaAdyacencia(x.get(i)));
            temp =temp.setScale(2,BigDecimal.ROUND_HALF_UP);
            caden=caden+contenidos[x.get(i-1)]+" - "+contenidos[x.get(i)]+": "+temp+" KMs\n";
            tot= tot.add(temp);
        }
        caden=caden+"Recorrido total: "+tot+" KMs";
    }

    public String getCaden(){
        return caden;
    }

    private ArrayList<Integer> leerRecorridoFinal(int indiceOrigen, int indiceDestino, int[] aleatorios){
        int nodoActual=indiceOrigen;
        int bandera=0;
        ArrayList<Integer>  recorrido= new ArrayList<Integer>();
        recorrido.add(nodoActual);
        while(nodoActual!=indiceDestino){
            if(bandera<aleatorios.length){
                nodoActual=nodos[nodoActual].getIndiceNodoAdyacente(aleatorios[bandera]%nodos[nodoActual].getNumeroAdyacencias());
                recorrido.add(nodoActual);
                bandera++;
            }else{break;}
        }
        String x="";
        for(int indice:recorrido){
            System.out.println(contenidos[indice]);
            x=x+contenidos[indice]+"    ";
        }
        System.out.println(calcularDistanciaRecorrido(recorrido)+"KMs");
        return recorrido;
    }
    
    private Recorrido leerRecorrido(int indiceOrigen, int indiceDestino, int[] aleatorios){
        int nodoActual=indiceOrigen;
        int bandera=0;
        ArrayList<Integer>  recorrido= new ArrayList<Integer>();
        recorrido.add(nodoActual);
        while(nodoActual!=indiceDestino){
            if(bandera<aleatorios.length){
                nodoActual=nodos[nodoActual].getIndiceNodoAdyacente(aleatorios[bandera]%nodos[nodoActual].getNumeroAdyacencias());
                recorrido.add(nodoActual);
                bandera++;
            }else{break;}
        }
        if(nodoActual==indiceDestino){
            ArrayList<Integer> aleatoriosNuevos=new ArrayList<Integer>();
            for(int i=0;i<bandera;i++){aleatoriosNuevos.add(aleatorios[i]);}
            return new Recorrido(recalcularFitness(recorrido),aleatoriosNuevos);
        }else{
            ArrayList<Integer> aleatoriosNuevos=new ArrayList<Integer>();
            for(int j=0;j<aleatorios.length;j++){aleatoriosNuevos.add(aleatorios[j]);}
            return new Recorrido(0,aleatoriosNuevos);
        }
    }
    
    private double recalcularFitness(ArrayList<Integer> recorrido){
        double distanciaTotal=0;
        for(int i=0;i<recorrido.size();i++){
            if(i!=0){
                distanciaTotal=distanciaTotal+nodos[recorrido.get(i-1)].getDistanciaAdyacencia(recorrido.get(i));
            }
        }
        return 10000-distanciaTotal;
    }
    
    private double calcularDistanciaRecorrido(ArrayList<Integer> recorrido){
        double distanciaTotal=0;
        for(int i=0;i<recorrido.size();i++){
            if(i!=0){
                distanciaTotal=distanciaTotal+nodos[recorrido.get(i-1)].getDistanciaAdyacencia(recorrido.get(i));
            }
        }
        return distanciaTotal;
    }       
    
    private int nodoAleatorio(int nodoActual){//ELIGE UN NODO ADYACENTE ALEATORIO... CON EL RESIDUO DE LA DIVISION NUMERO_ALEATORIO/CANTIDAD_ADYACENCIAS
        Random aleatorio = new Random();
        int numAleatorio=aleatorio.nextInt(100);
        aleatorios.add(numAleatorio);
        return nodos[nodoActual].getIndiceNodoAdyacente(numAleatorio%nodos[nodoActual].getNumeroAdyacencias());
    }
    
    private int getIndice(String contenido){//BUSCA EL NOMBRE DE UN LUGAR EN LA LISTA Y REGRESA SU INDICE
        int indice=0;
        for(int i=0;i<contenidos.length;i++){
            if(contenido.equals(contenidos[i])){
                indice=i;
            }
        }
        return indice;
    }

    
}
