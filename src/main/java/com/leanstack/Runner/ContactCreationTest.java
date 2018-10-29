/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.AccountCreationService;
import com.leanstack.ResellerClub.AccountManager.ContactCreationService;
import com.leanstack.ResellerClub.responses.ResellerResponse;

/**
 *
 * @author prolific
 */
public class ContactCreationTest {
    
    public static void main(String[] args) throws Exception{
        ContactCreationService service = new ContactCreationService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        ResellerResponse res = service.createCustomerContact("08145426058", "olatunji", "100001", "oduro", "lagos", "home", 19387285 , "NG", "tunji@yahoo.com");
        
        /*JAXBContext context = JAXBContext.newInstance(Integer.class);
        Unmarshaller un = context.createUnmarshaller();
        StringReader reader = new StringReader("<int>343454</int>");
        Integer Data = (Integer)un.unmarshal(reader);*/
        
        System.out.print(res.getMessage());
    }
}
