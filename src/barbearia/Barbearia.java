/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbearia;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Barbearia {
    boolean atendido = false;
    int nrCadeiras;
    LinkedList<Thread> listaEspera = new LinkedList<Thread>() {};

    private Barbearia(int nrCadeiras) {
        this.nrCadeiras = nrCadeiras;
    }
    
    public synchronized void verificaClientes() throws InterruptedException{
        while(listaEspera.size()<=0){
            System.out.println("Barbeiro Dormindo");
            wait();
        }
        atendeCliente();
    }
    public synchronized void atendeCliente() throws InterruptedException {
        atendido = true;
        notify();
        System.out.println("Atendendo "+  listaEspera.getFirst().getName());
        listaEspera.removeFirst();
        verificaClientes();
    }
    
    public synchronized void entraNaFila() throws InterruptedException {
       if(listaEspera.size()<nrCadeiras){
           if(listaEspera.size()<1){
               System.out.println(Thread.currentThread().getName()+" acordou barbeiro");
               notifyAll();
            }
           System.out.println(Thread.currentThread().getName()+
                   " esperando para ser atendido");
            listaEspera.add(Thread.currentThread());
            while(!atendido){
                wait();
            }
       }else{
           System.out.println("Barbearia lotada, cliente "
                       +Thread.currentThread().getName()+" foi embora");
       } 
    }
    public static void main(String[] args) {
        int nrCadeiras = 4;
        LinkedList<Thread> clientes = new LinkedList<Thread>() {};
        Barbearia barbearia = new Barbearia(nrCadeiras);
        Thread barbeiro = new Thread(new Barbeiro(barbearia), "Barbeiro");
        barbeiro.start();
        
        for(int i=0; i<10; i++){
            clientes.add(new Thread(new Cliente(barbearia),"Cliente "+i));
        }
        for (int i = 0; i < 10; i++) {
            clientes.get(i).start();
        }
    }
    
}
