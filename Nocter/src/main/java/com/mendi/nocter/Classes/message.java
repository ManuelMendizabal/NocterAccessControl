/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javax.swing.Timer;

/**
 *
 * @author manuel
 */
public class message {
    static Timer timer;
    static int timeToClose = 1500;
    public static void show(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        timer = new Timer(timeToClose, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert.close();
                    timer.stop();
                }
             });
        }});
        alert.show();
        timer.start();
    }
    public static void show(String title, String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        timer = new Timer(timeToClose, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert.close();
                    timer.stop();
                }
             });
        }});
        alert.show();
        timer.start();
    }
    public static void show(String title, String message, String header ){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
         timer = new Timer(timeToClose, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert.close();
                    timer.stop();
                }
             });
        }});
        alert.show();
        timer.start();
    }
}
