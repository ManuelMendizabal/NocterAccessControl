/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.mendi.nocter.Classes.Data.httpMethods;
import com.mendi.nocter.Classes.message;
import com.mendi.nocter.Classes.messageCode;
import static com.mendi.nocter.Nocter.stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author manuel
 */
public class LoginController implements Initializable {
    Scene scene;
    @FXML
    TextField txtUser;
    @FXML
    TextField txtPassword;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    public void btnLogin_Clicked(){
        try{
            if(txtUser.getText().length() == 0 || txtPassword.getText().length() == 0){
                message.show("Aviso", "Debe introducir el usuario y la contraseña");
            }
        }
        catch(Exception ex){
            message.show("Error",ex.getMessage());
        }

        JSONArray User = httpMethods.Send("GET", "admlogin","user="+ txtUser.getText()+"&password="+ txtPassword.getText(), "");
        try{
            if(((JSONObject)User.get(0)).get("response").toString().length()>=0){
                message.show("Aviso",messageCode.getString(((JSONObject)User.get(0)).get("response").toString()));
                return;
            }
        }catch(Exception ex){}
        if(User.size()>0){
            if(((JSONObject)User.get(0)).get("active").equals("0")){
                message.show(messageCode.getString("userInactive"));
                return;
            }    
            Nocter.changeScene("Settings", true);
            Stage thisStage = (Stage)txtUser.getScene().getWindow();
            thisStage.close();
            
        }else{
            message.show(messageCode.getString("noData"));
        } 
    }
    public void btnLostPassword_Clicked(){
        message.show("Si ha perdido sus credenciales de administrador puede hablar con el servicio técnico.");
    }
    public void btnClose_Clicked(){
        Platform.exit();
        System.exit(0);
    }
    
}
