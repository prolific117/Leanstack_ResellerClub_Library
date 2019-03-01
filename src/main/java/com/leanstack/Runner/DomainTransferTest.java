/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.DomainTransferService;
import com.leanstack.ResellerClub.AccountManager.SubscriptionCreationService;
import com.leanstack.ResellerClub.responses.DomainOrderResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author olatunji.oduro
 */
public class DomainTransferTest {
    
    public static void main(String[] args) throws Exception{
        
        DomainTransferService service = new DomainTransferService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        ResellerResponse res = service.createSubscription("aburokarun.com", "ns1.sedoparking.com", "ns2.sedoparking.com", "79998399", 1, "19387285", Boolean.TRUE, "FP6PAK[eWP");
        System.out.print("Status is : " + res.getCode() + " error is "+ res.getMessage());
    }
}
