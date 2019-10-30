/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.SettingsOptions;

import com.mendi.nocter.Classes.Data.Group;
import com.mendi.nocter.Classes.Data.Schedule;
import com.mendi.nocter.Classes.Data.User;
import com.mendi.nocter.Classes.Data.httpMethods;
import com.mendi.nocter.Classes.message;
import com.mendi.nocter.Classes.messageCode;
import com.mendi.nocter.Nocter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class UsersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnSave;
    @FXML
    ListView grdUsers;
    @FXML
    TextField txtFind;
    ArrayList<User> Users = new ArrayList<User>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadList("");
        grdUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                openUser(Users.get(grdUsers.getSelectionModel().getSelectedIndex()));
            }
        });
    } 
    public void loadList(String value){
        value = (value.length()>0?"user="+value: value);
        JSONArray jUsers = httpMethods.Send("GET", "users",value, "");
        try{
            if(((JSONObject)jUsers.get(0)).get("response").toString().length()>=0){
                message.show("Aviso",messageCode.getString(((JSONObject)jUsers.get(0)).get("response").toString()));
                return;
            }
        }catch(Exception ex){}
        Iterator ar = jUsers.iterator();
        grdUsers.getItems().clear();
        Users.clear();
        while(ar.hasNext()){
            JSONObject jo = (JSONObject)ar.next();  
            grdUsers.getItems().add(
                    jo.get("user").toString());
            //String user, String password, Group group, Schedule schedule, String nfccode, Boolean activate, Boolean administrator
            User user = new User(
                    Integer.parseInt(jo.get("id").toString()),
                    jo.get("user").toString(),
                    jo.get("password").toString(),
                    new Group(),
                    new Schedule(),
                    jo.get("nfcCode").toString(),
                    jo.get("active").toString().equals("1"),
                    jo.get("administrator").toString().equals("1")
            );
            Users.add(user);
        }
    }
    
    public void btnNew_Clicked(){
        openUser(null);
        //Nocter.addScene("SettingsOptions/UserData");
    }
    public void openUser(User user){
                try {
            Stage tmpstage = new Stage();
            //Parent root = FXMLLoader.load(Nocter.class.getResource("/fxml/SettingsOptions/UserData.fxml"));
            UserDataController.setUser(user);
            Parent root = Nocter.getParentRoot("SettingsOptions/UserData");
            Scene tmpscene = new Scene(root);
            tmpstage.initStyle(StageStyle.DECORATED);
            tmpstage.setScene(tmpscene);
            tmpstage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    loadList("");
                }
            });        
            UserDataController.parentController = this;
            tmpstage.show();
        } catch (Exception ex) {
            message.show(ex.getMessage());
        }
    }
    public void btnFind_Clicked(){
        loadList(txtFind.getText());
    }
}
