/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.NameServerService;
import com.leanstack.ResellerClub.AccountManager.SuggestionService;
import com.leanstack.ResellerClub.responses.SuggestionResponse;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public class NameserverTest {
     public static void main(String[] args) throws Exception{
        NameServerService service = new NameServerService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        service.update("agogogewo.com", "ns1.sedoparking.com", "ns2.sedoparking.com");
        
        //System.out.print(resp.getCode());
    }
}
