/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.SettingsOptions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author manuel
 */
public class usersGrid {
    private  StringProperty id;
     private  StringProperty user;
     private  StringProperty password;
     private  StringProperty active;
     private  StringProperty administrator;
     private  StringProperty usergroup;     
     private  StringProperty idSchedule; 
    public usersGrid(String id, String user,String password, String active, String administrator, String usergroup, String idSchedule){
        this.id = new SimpleStringProperty(id);
        this.user = new SimpleStringProperty(user);
        this.password = new SimpleStringProperty(password);
        this.active = new SimpleStringProperty(active);
        this.administrator = new SimpleStringProperty(administrator);
        this.usergroup = new SimpleStringProperty(usergroup);
        this.idSchedule = new SimpleStringProperty(idSchedule);
    }
}
