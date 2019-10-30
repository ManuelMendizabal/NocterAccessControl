/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter;

import com.mendi.nocter.Classes.message;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author manuel
 */
public class Nocter extends Application {
    private static double xOffSet = 0;
    private static double yOffSet = 0;
    public static Stage stage;
    public static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        changeScene("Main", true);
    }
    static EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        }
    };
    static EventHandler<MouseEvent> mouseDragged = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        }
    };
    public static void changeScene(String fxml){
        try {
            stage.close();
            stage = new Stage();
            Parent root = FXMLLoader.load(Nocter.class .getResource("/fxml/" + fxml + ".fxml"));
            scene = new Scene(root);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            message.show(ex.getMessage());
        }
    }
    public static void addScene(String fxml){
        try {
            Stage tmpstage = new Stage();
            Parent root = FXMLLoader.load(Nocter.class .getResource("/fxml/" + fxml + ".fxml"));
            Scene tmpscene = new Scene(root);
            tmpstage.initStyle(StageStyle.DECORATED);
            tmpstage.setScene(tmpscene);
            tmpstage.show();
        } catch (Exception ex) {
            message.show(ex.getMessage());
        }
    }
    public static Parent getParentRoot(String fxml){
        try {
            return FXMLLoader.load(Nocter.class .getResource("/fxml/" + fxml + ".fxml"));
        } catch (Exception ex) {
            return null;
        }
    }
    public static void changeScene(String fxml, Boolean withoutWindowsBar){
        try {
            stage.close();
            stage = new Stage();
            Parent root = FXMLLoader.load(Nocter.class .getResource("/fxml/" + fxml + ".fxml"));
            if(withoutWindowsBar){
                root.setOnMouseDragged(mouseDragged);
                root.setOnMousePressed(mousePressed);
                stage.initStyle(StageStyle.UNDECORATED);
            }else{
                stage.initStyle(StageStyle.DECORATED);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            message.show(ex.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
