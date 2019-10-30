/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.nfc;

import lombok.Getter;

/**
 *
 * @author manuel
 */
public interface NFCObjectListener {
    public void onNFCTagReaded(String text);    
}
