/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.DomainSecretService;
import com.leanstack.ResellerClub.AccountManager.NameServerService;

/**
 *
 * @author olatunji.oduro
 */
public class DomainSecretTest {
     public static void main(String[] args) throws Exception{
       DomainSecretService service = new DomainSecretService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
       service.retrieveSecret("agogogewo.com"); 
        //System.out.print(resp.getCode());
    }
}
