/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prolific
 */

@XmlRootElement(name="response")
public class ErrorResponse {
    
    @XmlElement(name="status")
    public String status;
    
    @XmlElement(name="message")
    public String message;

    
}






