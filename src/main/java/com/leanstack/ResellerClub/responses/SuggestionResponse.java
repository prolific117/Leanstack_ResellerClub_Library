/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.ResellerClub.responses;

import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public class SuggestionResponse {
    
    int code;
    String message;
    List<String> availableDomains;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getAvailableDomains() {
        return availableDomains;
    }

    public void setAvailableDomains(List<String> availableDomains) {
        this.availableDomains = availableDomains;
    }
    
    
}
