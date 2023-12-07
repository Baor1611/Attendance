/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package attendancemanagementsystem;

import java.awt.EventQueue;

/**
 *
 * @author admin
 */
public class AttendanceManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //mainFrame frame = new mainFrame();
        //frame.setVisible(true);
        LoginSignupApp window;
        window = new LoginSignupApp();
        window.frame.setVisible(true);
                
        EventQueue.invokeLater(() -> {
            try {

            } catch (Exception e) {
            }
        });
    }
}

