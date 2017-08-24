/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;

/**
 *
 * @author pako_
 */
public class Nodo {
    private double[] tablaAdyacencias;//TABLA DE ADYACENCIAS DEL LUGAR
    private ArrayList<Integer> adyacencias= new ArrayList<Integer>();//LISTA CON LOS INDICES DE LUGARES ADYACENTES
    
    public Nodo(String cadenaIndices){//INICIALIZAR
        tablaAdyacencias=arregloStringAArregloDouble(cadenaIndices.split(","));//CONVIERTE LA CADENA EN UN ARREGLO NUMERICO
        crearAdyacencias();//GUARDA LOS LUGARES ADYACENTES
    }
    
    private double[] arregloStringAArregloDouble(String[] arregloString){//CONVIERTE UN ARREGLO DE CADENAS EN UN ARREGLO DE NUMEROS
        double[] arregloDouble=new double[arregloString.length];
        for(int i=0;i<arregloDouble.length;i++){
            arregloDouble[i]=Double.parseDouble(arregloString[i]);
        }
        return arregloDouble;
    }



    private void crearAdyacencias(){//VERIFICA CON CUALES LUGARES HAY ADYACENCIAS Y LOS GUARDA EN LA LISTA
        for(int i=0;i<tablaAdyacencias.length;i++){
            if(tablaAdyacencias[i]>0){
                adyacencias.add(i);
            }
        }
    }
    
    public boolean esAdyacenteCon(int indiceDestino){//VERIFICA SI ES ADYACENTE CON ALGUN LUGAR EN ESPECIFICO
        boolean result=false;
        for(int indice:adyacencias){
            if(indice==indiceDestino){
                result=true;
            }
        }
        return result;
    }
    
    public double getDistanciaAdyacencia(int indiceDestino){//REGRESA LA DISTANCIA ENTRE ESTE NODO Y UNO ADYACENTE
        return tablaAdyacencias[indiceDestino];
    }
    
    public int getNumeroAdyacencias(){//REGRESA EL NUMERO DE NODOS ADYACENTES
        return adyacencias.size();
    }
    
    public int getIndiceNodoAdyacente(int posicion){
        return adyacencias.get(posicion);
    }
}
