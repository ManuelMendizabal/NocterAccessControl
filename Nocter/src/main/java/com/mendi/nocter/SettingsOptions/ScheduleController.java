/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.SettingsOptions;

import com.mendi.nocter.Nocter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class ScheduleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnSave;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void btnNew_Clicked(){
        Nocter.addScene("SettingsOptions/GroupData");
    }
    public void btnFind_Clicked(){
    }
}
