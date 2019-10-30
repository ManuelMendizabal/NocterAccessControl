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
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author manuel
 */
public class InformationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ListView grdInformation;
    @FXML
    DatePicker dtStartTime,dtEndTime;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LocalDate actualDate = java.time.LocalDate.now();
        dtStartTime.setValue(actualDate);
        dtEndTime.setValue(actualDate.plusDays(1));
        loadList(dtStartTime.getValue().toString(),dtEndTime.getValue().toString(), false);
    } 
    public void loadList(String startDate, String endDate, Boolean showMessageResult){
        String params = "startdate=" + startDate + "&enddate="+ endDate;
        //http://programoyformateo.com/Nocter/public/index.php/api/signRegister?startdate=2019-06-08&enddate=2019-06-08&key=^STp2P$gturU4QPtG2HnTYzxLbwfMQ$H*tq@K8e5!vLSHG!mtJZUTv4rbeKjabu@FYXKnV7PzWh@fKea9Kdb5EQP
        JSONArray jSignReg = httpMethods.Send("GET", "signRegister",params, "");
        try{
            if(((JSONObject)jSignReg.get(0)).get("response").toString().length()>=0){
                if(showMessageResult){
                    message.show("Aviso",messageCode.getString(((JSONObject)jSignReg.get(0)).get("response").toString()));
                }
                return;
            }
        }catch(Exception ex){}
        grdInformation.getItems().clear();
        Iterator ar = jSignReg.iterator();
        while(ar.hasNext()){
            JSONObject jo = (JSONObject)ar.next();  
            /*String dateString = jo.get("user").toString() + " - " + 
                    messageCode.getString("information_date") + " - " + jo.get("date").toString() + " - " +
                    messageCode.getString("information_movtype") + " - " + jo.get("movtype").toString();*/
            String dateString = jo.get("user").toString() + " - " + 
                    jo.get("date").toString() + " - " +
                    jo.get("movtype").toString();
            grdInformation.getItems().add(dateString);
        }
    }
    
    public void btnFind_Clicked(){
        loadList(dtStartTime.getValue().toString(),dtEndTime.getValue().toString(),true);
    }
}
