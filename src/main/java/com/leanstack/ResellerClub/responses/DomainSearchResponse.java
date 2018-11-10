/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.ResellerClub.responses;

/**
 *
 * @author olatunji.oduro
 */

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class DomainSearchResponse {
    private Map<String, SearchData> unknownFields = new HashMap<>();

    // Getters and setters (except for unknownFields)
    public Map<String, SearchData> getUnknownFields() {
        return unknownFields;
    }
   
    @JsonAnySetter
    public void setUnknownField(String name, SearchData value) {
        unknownFields.put(name, value);
    }
}