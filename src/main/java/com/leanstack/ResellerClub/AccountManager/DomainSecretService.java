/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.ResellerClub.AccountManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.DomainSecretResponse;
import com.leanstack.ResellerClub.responses.NameServerResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 * @author olatunji.oduro
 */
public class DomainSecretService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(SuggestionService.class.getName());
  
    public DomainSecretService(String service, String login, String key){
        super(service, login , key);
        
    }
    
    private String getOrderId(String domainName){
      String orderUrl = this.getKA_SERVICE()+
                  "domains/orderid.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&domain-name="+domainName;
       
       OkHttpClient client = new OkHttpClient();
       ObjectMapper mapper = new ObjectMapper();
         
       try {
            Request request = new Request.Builder()
              .url(orderUrl)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            String response = client.newCall(request).execute().body().string();
            
            return response;
          
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in create reseller club account", ex);
            
        }
     
     return "";     
    }
    
    public ResellerResponse retrieveSecret(String domain_name) {
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         String orderId = getOrderId(domain_name);
         //System.out.print("Order id is "+orderId);
        
         String url = this.getKA_SERVICE()
                 +"domains/details.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()
                 + "&options=OrderDetails"
                 + "&order-id="+orderId;
         
         OkHttpClient client = new OkHttpClient();
         ObjectMapper mapper = new ObjectMapper();
      
          try {
            Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            String response = client.newCall(request).execute().body().string();
             
            //System.out.print(response);
            DomainSecretResponse resp_ = mapper.readValue(response, DomainSecretResponse.class);
           
            System.out.print(resp_.getDomsecret());
            resp.setCode(0);
            resp.setMessage(resp_.getDomsecret());

            return resp;
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in search domain name", ex);
            resp.setMessage("An error occured");
            
            return resp;
        }
    }
}
