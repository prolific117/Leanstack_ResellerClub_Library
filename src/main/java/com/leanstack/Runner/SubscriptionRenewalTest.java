/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.SubscriptionCreationService;
import com.leanstack.ResellerClub.AccountManager.SubscriptionRenewalService;
import com.leanstack.ResellerClub.responses.DomainOrderResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import static java.lang.String.format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author olatunji.oduro
 */
public class SubscriptionRenewalTest {
    
    public static void main(String[] args) throws Exception{
        
        JAXBContext context = JAXBContext.newInstance(DomainOrderResponse.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //convert to desired object
        SubscriptionRenewalService service = new SubscriptionRenewalService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        ResellerResponse res = service.renewSubscription("theblink024.com", Boolean.TRUE, 1);
        System.out.print("Status is : " + res.getCode() + " error is "+ res.getMessage());
    }
}
