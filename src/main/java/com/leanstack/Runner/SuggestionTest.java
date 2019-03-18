/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.Runner;

import com.leanstack.ResellerClub.AccountManager.AccountCreationService;
import com.leanstack.ResellerClub.AccountManager.SuggestionService;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import com.leanstack.ResellerClub.responses.SuggestionResponse;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public class SuggestionTest {
    
     public static void main(String[] args) throws Exception{
        SuggestionService service = new SuggestionService("https://test.httpapi.com/api/","754524","wrgWpIRjPsC5mk8F2URn1Mn4WuP75QXd");
        List<String> list = Arrays.asList("net","online","gov");
        SuggestionResponse resp = service.search("olatunji", "com", list);
        
        System.out.print(resp.getCode());
    }
}
