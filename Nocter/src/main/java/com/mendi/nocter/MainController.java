/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mendi.nocter.Classes.Data.httpMethods;
import com.mendi.nocter.Classes.message;
import com.mendi.nocter.Classes.messageCode;
import com.mendi.nocter.nfc.NFCObjectListener;
import com.mendi.nocter.nfc.SystemStatus;
import com.mendi.nocter.nfc.reader.Reader;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import static java.util.Objects.isNull;
import java.util.Optional;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import javax.swing.Timer;
import org.json.simple.JSONArray;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class MainController implements Initializable {
    private static Reader currentReader;
    private static SystemStatus systemStatus;
    private static boolean shouldBuzz = true;
    Scene scene;
    @FXML
    TextField txtUser,txtPassword;
    @FXML
    Text txtDate,txtTime;    
    LocalDateTime lastNFCRead;
    String lastNFCText;
    Integer timeToSign = 60;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastNFCRead = java.time.LocalDateTime.now();
        lastNFCText = "";
        MainController.systemStatus = new SystemStatus();
        systemStatus.setNFCTagReadedListener(new NFCObjectListener(){
            @Override
            public void onNFCTagReaded(String text) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        LocalDateTime tmpTimeNow = java.time.LocalDateTime.now();
                        int seconds = (int) ChronoUnit.SECONDS.between(lastNFCRead,tmpTimeNow);
                        if(seconds < 3 || seconds < timeToSign && lastNFCText.equals(text.trim()))
                            return;
                        lastNFCText = text.trim();
                        lastNFCRead = tmpTimeNow;
                        sign(text.trim());
                    }
                });
            }
        });             
        startReader(true);
        Timer timer = new Timer (500, new ActionListener () 
        { 
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(now);
                String time = DateTimeFormatter.ofPattern("HH:mm").format(now);
                txtDate.setText(date);
                txtTime.setText(time);
            }
        }); 
        timer.start();
    }
    public void sign(String code){
        JSONArray User = httpMethods.Send("GET", "signRegisterUsers","NFCCode="+ code.trim(),"");
        insertSign(User);
    }
    public void sign(String user, String password){
         try{
            if(user.length() == 0 || password.length() == 0){
                message.show("Aviso", "Debe introducir el usuario y la contraseña");
            }
        }
        catch(Exception ex){
            message.show("Error",ex.getMessage());
        }
        JSONArray User = httpMethods.Send("GET", "signRegisterUsers","user="+ user+"&password="+ password,"");
        insertSign(User);
        txtUser.setText("");
        txtPassword.setText("");
    }
    public void insertSign(JSONArray aUser){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String actualDate = java.time.LocalDateTime.now().format(formatter);
        Integer idUser = 0;
        JSONObject User = (JSONObject)aUser.get(0);
        try{
            if(User.get("response").toString().length()>=0){
                message.show("Aviso",messageCode.getString(((JSONObject)User.get(0)).get("response").toString()));
                return;
            }
        }catch(Exception ex){}
        if(User.size()>0){
            if(User.get("active").equals("0")){
                message.show(messageCode.getString("userInactive"));
                return;
            }      
            //insertamos
           JSONObject jSign = new JSONObject();
           jSign.put("iduser", User.get("id"));
           jSign.put("idGroup", User.get("usergroup"));
           jSign.put("idschedule", User.get("idSchedule"));
           jSign.put("date", actualDate.toString());
           jSign.put("movtype", User.get("movtype"));

           JSONArray response = httpMethods.Send("POST", "signRegister/add","", jSign.toJSONString());
           try{
               if(((JSONObject)response.get(0)).get("response").toString().length()>=0){
                   message.show("Aviso",User.get("movtype").toString() + " - " +
                           messageCode.getString(((JSONObject)response.get(0)).get("response").toString()) + " - " +
                           User.get("user").toString());
                   return;
               }
           }catch(Exception ex){}
            //message.show("El usuario " + User.get("user").toString()+ " ha fichado correctamente");
        }else{
            message.show(messageCode.getString("noData"));
        } 
    }
    public void btnLogin_Clicked(){
       sign(txtUser.getText(),txtPassword.getText());
    }
    public void btnSettings_Clicked(){
        Nocter.addScene("Login");
    }
    public void btnClose_Clicked(){
        Platform.exit();
        System.exit(0);
    }
    public static void startReader(boolean start) {
		initializeReader(MainController.systemStatus).ifPresent(reader -> {
			MainController.currentReader = reader;
			if (start) {
				reader.start();
			}
		});
    }

    public static void stopReader() {
            Optional.ofNullable(MainController.currentReader).ifPresent(reader -> reader.stop());
            currentReader = null;
    }

    private static Optional<Reader> initializeReader(SystemStatus systemStatus) {
            try {
                    return Optional.ofNullable(new Reader(systemStatus, shouldBuzz));
            }
            catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return Optional.empty();
            }
    }
}
