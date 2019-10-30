/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.SettingsOptions;

import com.mendi.nocter.Classes.Data.httpMethods;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.mendi.nocter.Classes.message;
import com.mendi.nocter.nfc.NFCObjectListener;
import com.mendi.nocter.nfc.SystemStatus;
import com.mendi.nocter.nfc.reader.Reader;
import com.mendi.nocter.Classes.Data.User;
import com.mendi.nocter.Classes.messageCode;
import java.security.NoSuchAlgorithmException;
import static java.util.Objects.isNull;
import java.util.Optional;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class UserDataController implements Initializable {
    private static Reader currentReader;
    private static SystemStatus systemStatus;
    private static boolean shouldBuzz = true;
    public static UsersController parentController;
    @Getter @Setter private static User user;
    //public static Boolean isNew;
    public static Integer idUser;
    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnSave,btnDelete;
    @FXML
    TextField txtNFC,txtUser,txtSchedule,txtGroup;
    @FXML
    PasswordField txtPassword;
    @FXML
    CheckBox chkActivated,chkAdministrator;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(!isNull(user))
            loadUser();
        UserDataController.systemStatus = new SystemStatus();
        systemStatus.setNFCTagReadedListener(new NFCObjectListener(){
            @Override
            public void onNFCTagReaded(String text) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        txtNFC.setText(text);
                    }
                });
            }
        });             
        startReader(true);
    } 
    public void loadUser(){
        btnDelete.setDisable(false);
        txtUser.setText(user.getUser());
        txtPassword.setText(user.getPassword());
        //txtGroup.setText(user.getGroup());
        //txtSchedule.setText(user.getSchedule());
        txtNFC.setText(user.getNfccode());
        chkActivated.setSelected(user.getActivate());
        chkAdministrator.setSelected(user.getAdministrator());
    }
    public void btnSave_Clicked(){
        //guardamos
        if(txtUser.getText().length()==0 || txtPassword.getText().length()==0){
            message.show("Aviso","Los campos usuario y contraseña son obligatorios");
            return;
        }
        JSONObject juser = new JSONObject();
        //juser.put("id", user.getIdUser());
        juser.put("user", txtUser.getText());
        juser.put("password", txtPassword.getText());
        juser.put("active", chkActivated.isSelected());
        juser.put("administrator", chkAdministrator.isSelected());
        juser.put("usergroup", "0");
        juser.put("idSchedule", "0");
        juser.put("nfcCode", txtNFC.getText());
        JSONArray User = new JSONArray();
        if(isNull(user)){
            User = httpMethods.Send("POST", "users/add","", juser.toJSONString());
        }else{
            //{"password":"test","administrator":0,"nfcCode":"65C540E2","usergroup":"0","active":1,"idSchedule":"0","user":"Manu"}
            User = httpMethods.Send("PUT", "users/update/" + user.getIdUser(),"", juser.toJSONString());
        }
        try{
            if(((JSONObject)User.get(0)).get("response").toString().length()>=0){
                message.show("Aviso",messageCode.getString(((JSONObject)User.get(0)).get("response").toString()));
            }
        }catch(Exception ex){}
        Stage thisStage = (Stage)btnSave.getScene().getWindow();
        parentController.loadList("");
        thisStage.close();
    }
    public void btnDelete_Clicked(){
          JSONArray User = httpMethods.Send("DELETE", "users/delete/" + user.getIdUser(),"", "");
        try{
            if(((JSONObject)User.get(0)).get("response").toString().length()>=0){
                message.show("Aviso",messageCode.getString(((JSONObject)User.get(0)).get("response").toString()));
            }
        }catch(Exception ex){}
        Stage thisStage = (Stage)btnSave.getScene().getWindow();
        parentController.loadList("");
        thisStage.close();
    }
     public static void startReader(boolean start) {
		initializeReader(UserDataController.systemStatus).ifPresent(reader -> {
			UserDataController.currentReader = reader;
			if (start) {
				reader.start();
			}
		});
    }

    public static void stopReader() {
            Optional.ofNullable(UserDataController.currentReader).ifPresent(reader -> reader.stop());
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
