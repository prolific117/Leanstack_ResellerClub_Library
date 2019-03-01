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
public class DomainTransferResponse {
 
    String status;
    String error;
    PrivacyDetails privacydetails;

    public PrivacyDetails getPrivacydetails() {
        return privacydetails;
    }

    public void setPrivacydetails(PrivacyDetails privacydetails) {
        this.privacydetails = privacydetails;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     
}
