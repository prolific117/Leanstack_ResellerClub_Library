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
public class DomainSecretResponse {
      String domsecret;

    public String getDomsecret() {
        return domsecret;
    }

    public void setDomsecret(String domsecret) {
        this.domsecret = domsecret;
    }
      
      
}
