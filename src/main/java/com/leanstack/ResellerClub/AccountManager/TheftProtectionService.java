/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leanstack.ResellerClub.AccountManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.DomainSecretResponse;
import com.leanstack.ResellerClub.responses.NameServerResponse;
import com.leanstack.ResellerClub.responses.ProtectionResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 * @author olatunji.oduro
 */
public class TheftProtectionService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(SuggestionService.class.getName());
  
    public TheftProtectionService(String service, String login, String key){
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
    
    public ResellerResponse addProtection(String domain_name) {
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         String orderId = getOrderId(domain_name);
         //System.out.print("Order id is "+orderId);
        
         String url = this.getKA_SERVICE()
                 +"domains/enable-theft-protection.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()
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
            ProtectionResponse resp_ = mapper.readValue(response, ProtectionResponse.class);
           
            System.out.print(resp_.getStatus());
            if("Success".equals(resp_.getStatus())){
                resp.setCode(0);
                resp.setMessage(resp_.getStatus());
            }
            else{
                resp.setCode(10);
                resp.setMessage(resp_.getError());
            }
            
            return resp;
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in add theft protectiu=on", ex);
            resp.setMessage("An error occured");
            
            return resp;
        }
    }
    
    public ResellerResponse removeProtection(String domain_name) {
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         String orderId = getOrderId(domain_name);
         //System.out.print("Order id is "+orderId);
        
         String url = this.getKA_SERVICE()
                 +"domains/disable-theft-protection.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()
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
             
            ProtectionResponse resp_ = mapper.readValue(response, ProtectionResponse.class);
           
            System.out.print(resp_.getStatus());
            if("Success".equals(resp_.getStatus())){
                resp.setCode(0);
                resp.setMessage(resp_.getStatus());
            }
            else{
                resp.setCode(10);
                resp.setMessage(resp_.getError());
            }
            
            return resp;
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in remove protection", ex);
            resp.setMessage("An error occured");
            
            return resp;
        }
    }
}
