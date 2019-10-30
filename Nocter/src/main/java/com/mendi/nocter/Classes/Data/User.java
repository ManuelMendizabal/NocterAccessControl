/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.Classes.Data;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author manuel
 */
public class User {
    @Getter @Setter private Integer idUser;
    @Getter @Setter private String user;
    @Getter @Setter private String password;
    @Getter @Setter private Group group;
    @Getter @Setter private Schedule schedule;
    @Getter @Setter private String nfccode;
    @Getter @Setter private Boolean activate;
    @Getter @Setter private Boolean administrator; 
    public User(){}
    public User(Integer idUser,String user, String password, Group group, Schedule schedule, String nfccode, Boolean activate, Boolean administrator){
        this.idUser = idUser;
        this.user = user;
        this.password = password;
        this.group = group;
        this.schedule = schedule;
        this.nfccode = nfccode;
        this.activate = activate;
        this.administrator = administrator;
    }
}
