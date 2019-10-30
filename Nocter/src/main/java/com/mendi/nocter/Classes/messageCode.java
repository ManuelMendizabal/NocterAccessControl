/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.Classes;

import java.lang.reflect.Field;

/**
 *
 * @author manuel
 */
public class messageCode {
    private static String Language = "ESP";
    public static String getString(String messageCode){
        String response = "";
        try{
            //response = ((Field)errorMessageCode.class.getField(Language + messageCode)).get(null).toString();
            response = ((Field)Class.forName("com.mendi.nocter.Classes.messageCode$" + Language).getField(messageCode)).get(null).toString();
        }catch(Exception ex){
            response = messageCode;
        }        
        return response;
    }
    public static String getString(String messageCode, String Language){
        String response = "";
        try{
            response = ((Field)Class.forName("com.mendi.nocter.Classes.messageCode$" + Language).getField(messageCode)).get(null).toString();
        }catch(Exception ex){
            response = messageCode;
        }        
        return response;
    }
    public static class ESP{
        public static String emptyUser = "Debe introducir el usuario y la contraseña";
        public static String incorrectUserPassword = "Las credenciales son invalidas";
        public static String userInactive = "El usuario está desactivado";
        public static String noData = "No se han podido obtener datos";
        public static String userAlreadyExists = "Ya existe un usuario con este nombre";
        public static String newUseradded = "Usuario añadido";
        public static String signRegistersNotFound = "No se han encontrado registros para el filtro seleccionado";
        public static String signAdded= "confirmada";
        public static String information_date = "Fecha:";
        public static String information_movtype = "Tipo de movimiento:";
    }
    public static class ENG{
        public static String emptyUser = "You must enter the username and password";
        public static String incorrectUserPassword = "The credentials are incorrect";
        public static String userInactive = "The user is diabled";
        public static String noData = "Unable to obtain data";
        public static String userAlreadyExists = "User already exists";
        public static String signRegistersNotFound = "No records found for the selected filter";
        public static String signAdded= "confirmed";
        public static String information_date = "Date:";
        public static String information_movtype = "Type of movement:";
    }    
}
