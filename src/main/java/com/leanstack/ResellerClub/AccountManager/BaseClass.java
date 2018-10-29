/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;

/**
 *
 * @author prolific
 */
public class BaseClass {
    
    private String KA_SERVICE;
    private String REMOTE_API_LOGIN;
    private String APIKEY;
   
    public BaseClass(String service, String login, String apiKey){
        this.KA_SERVICE = service;
        this.REMOTE_API_LOGIN = login;
        this.APIKEY = apiKey;
    }

   
    public String getKA_SERVICE() {
        return KA_SERVICE;
    }

    public void setKA_SERVICE(String KA_SERVICE) {
        this.KA_SERVICE = KA_SERVICE;
    }

    public String getREMOTE_API_LOGIN() {
        return REMOTE_API_LOGIN;
    }

    public void setREMOTE_API_LOGIN(String REMOTE_API_LOGIN) {
        this.REMOTE_API_LOGIN = REMOTE_API_LOGIN;
    }

    public String getAPIKEY() {
        return APIKEY;
    }

    public void setAPIKEY(String APIKEY) {
        this.APIKEY = APIKEY;
    }
}
