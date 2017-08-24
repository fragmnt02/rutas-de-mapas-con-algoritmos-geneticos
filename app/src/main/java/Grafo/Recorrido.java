/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author pako_
 */
public class Recorrido {
    BigDecimal fitness;
    ArrayList<Integer> numerosAleatorios=new ArrayList<>();
    
    public Recorrido(double fitness, ArrayList<Integer> numerosAleatorios){
        this.fitness=new BigDecimal(fitness);
        this.fitness=this.fitness.setScale(2,BigDecimal.ROUND_HALF_UP);
        this.numerosAleatorios=numerosAleatorios;
    }
    
    public BigDecimal getFitness(){
        return fitness;
    }
    
    public int[] getNumerosAleatorios(){
        int[] temp=new int[numerosAleatorios.size()];
        for(int i=0;i<numerosAleatorios.size();i++){
            temp[i]=numerosAleatorios.get(i);
        }
        return temp;
    }
    
    public void reemplazarValorAleatorios(int indice, int valor){
        numerosAleatorios.set(indice, valor);
    }
    
    public int getAleatorio(int indice){
        return numerosAleatorios.get(indice);
    }
    
    public void setFitness(double fitness){
        this.fitness=new BigDecimal(fitness);
        this.fitness=this.fitness.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
