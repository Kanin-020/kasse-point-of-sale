/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import vista.*;
import modelo.*;
import control.*;

/**
 *
 * @author l2001
 */
public class Kasse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       
        VistaInicio vistaInicio = new VistaInicio();
        vistaInicio.setVisible(true);
        Thread.sleep(1300);
        vistaInicio.dispose();
        Usuario modeloRegistro = new Usuario();
        VistaLogin vistaLogin = new VistaLogin();
        ControlLogin controlLogin = new ControlLogin(vistaLogin);

        
        vistaLogin.setVisible(true);
     
    }

}
