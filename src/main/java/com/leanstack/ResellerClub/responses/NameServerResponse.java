/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.ResellerClub.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NameServerResponse {
    
    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
