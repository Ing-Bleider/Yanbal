/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import modelo.SplashScreen;
import yanbal.Bienvenida;

/**
 *
 * @author Bleider
 */

public class Main {
   
   public static void main(String[] args) {
       
       
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);
        
        // Simula una carga de trabajo
        try {
            Thread.sleep(3000); // Espera 3 segundos
        } catch (InterruptedException e) {
            
        }
        
        // Cierra la SplashScreen y continúa con la aplicación principal
        splashScreen.setProgress("Carga completa");
        splashScreen.close();
        
       
        Bienvenida bien = new Bienvenida();
        bien.setVisible(true);
    }

}
