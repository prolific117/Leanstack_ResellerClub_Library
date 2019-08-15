/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.DomainSecretService;
import com.leanstack.ResellerClub.AccountManager.TheftProtectionService;

/**
 *
 * @author olatunji.oduro
 */
public class TheftProtectionTest {
     public static void main(String[] args) throws Exception{
       TheftProtectionService service = new TheftProtectionService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
       service.addProtection("agogogewo.com"); 
        //System.out.print(resp.getCode());
    }
}
