/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.Runner;

import com.leanstack.ResellerClub.responses.AccountCreationResponse;
import com.leanstack.ResellerClub.AccountManager.AccountCreationService;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author prolific
 */
public class AccountCreationTest {
    
    public static void main(String[] args) throws Exception{
        AccountCreationService service = new AccountCreationService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        ResellerResponse res = service.createCustomerAccount("1", "2348145426958", "ola", "100001", "oduro","lagos", "home", "lagos", "9Ynilag$1991@", "infinushk1800@yahooo.com");
        
        /*JAXBContext context = JAXBContext.newInstance(Integer.class);
        Unmarshaller un = context.createUnmarshaller();
        StringReader reader = new StringReader("<int>343454</int>");
        Integer Data = (Integer)un.unmarshal(reader);*/
        
        System.out.print(res.getCode());
    }
}

