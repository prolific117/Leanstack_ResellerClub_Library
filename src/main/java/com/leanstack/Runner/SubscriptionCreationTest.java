/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.SubscriptionCreationService;
import com.leanstack.ResellerClub.responses.DomainOrderResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author prolific
 */
public class SubscriptionCreationTest {
    
    public static void main(String[] args) throws Exception{
        
        JAXBContext context = JAXBContext.newInstance(DomainOrderResponse.class);
        Unmarshaller un = context.createUnmarshaller();
        //convert to desired object
        SubscriptionCreationService service = new SubscriptionCreationService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        ResellerResponse res = service.createSubscription("thetunbrinbra.com", "ns1.sedoparking.com", "ns2.sedoparking.com", "79998399", 1, "19387285", Boolean.TRUE);
        System.out.print("Status is : " + res.getCode() + " error is "+ res.getMessage());
    }
}
