/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.qldt;


 import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.qldt.controller.LoginController;
 import com.qldt.view.LoginView;
 
 public class App {
     public static void main(String[] args) {
         EventQueue.invokeLater(new Runnable() {
             public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                } catch (UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 LoginView view = new LoginView();
                 LoginController controller = new LoginController(view);
                 // hiển thị màn hình login
                 controller.showLoginView();
             }
         });
     }
 }
 