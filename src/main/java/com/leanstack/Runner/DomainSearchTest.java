/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.DomainSearchService;
import com.leanstack.ResellerClub.responses.ResellerResponse;

/**
 *
 * @author olatunji.oduro
 */
public class DomainSearchTest{
     public static void main(String[] args) throws Exception{
        DomainSearchService domainservice = new DomainSearchService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        ResellerResponse res = domainservice.search("tunji", "com");
        //ResellerResponse res = service.createCustomerContact("08145426058", "olatunji", "100001", "oduro", "lagos", "home", 19387285 , "NG", "tunji@yahoo.com");
        
        
        System.out.print("Response is "+res.getMessage());
     }
}
