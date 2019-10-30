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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnClose;
    @FXML
    Button btnUsers,btnGroups,btnSchedules,btnInformation;
    @FXML
    Parent layoutUsers,layoutGroups,layoutSchedules,layoutInformation;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void btnUsers_Clicked(){
        showLayout(layoutUsers);
    }
    public void btnGroups_Clicked(){
        showLayout(layoutGroups);
    }
    public void btnSchedules_Clicked(){
        showLayout(layoutSchedules);
    }
    public void btnInformation_Clicked(){
        showLayout(layoutInformation);
    }
    public void btnClose_Clicked(){
        Nocter.changeScene("Main", true);
    }
    public void showLayout(Parent tmpLayout){
        layoutUsers.setVisible(false);
        layoutGroups.setVisible(false);
        layoutSchedules.setVisible(false);
        layoutInformation.setVisible(false);
        tmpLayout.setVisible(true);
    }
}
