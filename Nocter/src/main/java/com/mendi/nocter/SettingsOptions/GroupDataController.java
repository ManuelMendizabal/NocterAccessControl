/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.SettingsOptions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.mendi.nocter.Classes.message;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class GroupDataController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnSave,btnClose;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void btnSave_Clicked(){
        //guardamos
        Stage thisStage = (Stage)btnSave.getScene().getWindow();
        thisStage.close();
    }
    public void btnClose_Clicked(){
        Stage thisStage = (Stage)btnClose.getScene().getWindow();
        thisStage.close();
    }

}
