/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.base;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author prolific
 */
public class StrongPasswordGen {
    
    public static String generatePassword(){
        
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String pwd = RandomStringUtils.random( 10, characters );
       
        return pwd;
    }
}
