/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbearia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago
 */
public class Cliente implements Runnable{
    
    Barbearia barbearia;

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
    }
    
    @Override
    public void run() {
        try {
            barbearia.entraNaFila();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
