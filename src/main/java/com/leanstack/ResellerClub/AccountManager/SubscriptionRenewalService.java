/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.OrderDetailsResponse;
import com.leanstack.ResellerClub.responses.RenewalResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author prolific
 */
public class SubscriptionRenewalService extends BaseClass{
    
    private static final Logger logger = Logger.getLogger(SubscriptionRenewalService.class.getName());
  
    public SubscriptionRenewalService(String service, String login, String password){
        super(service, login , password);
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
    
    private String getOrderEnd(String orderId){
        //get Order
       String orderUrl = this.getKA_SERVICE()+
                  "domains/details.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&order-id="+orderId+"&options=OrderDetails";
       
       OkHttpClient client = new OkHttpClient();
       ObjectMapper mapper = new ObjectMapper();
         
       try {
            Request request = new Request.Builder()
              .url(orderUrl)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            Response response = client.newCall(request).execute();
            
            OrderDetailsResponse resp = mapper.readValue(response.body().string(), OrderDetailsResponse.class);
        
            return resp.getEndtime();
          
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in create reseller club account", ex);
            
        }
     
     return "";  
    }
    
    public ResellerResponse renewSubscription(String domainName, Boolean whois, int duration) throws Exception{
        
       ResellerResponse resp = new ResellerResponse();
       resp.setCode(10);
       
       String orderId = getOrderId(domainName);
       
       if("".equals(orderId)){
           resp.setMessage("Cannot retrieve orderId");
           return resp;
       }
       String endtime = getOrderEnd(orderId);
      
       String url = this.getKA_SERVICE()+
                  "domains/renew.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&order-id="+orderId+"&years="
                 +duration+"&exp-date="+endtime+"&invoice-option=NoInvoice"
                 +"&purchase-privacy="+whois;
         
         OkHttpClient client = new OkHttpClient();
         ObjectMapper mapper = new ObjectMapper();
      
         try {
            Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            Response response = client.newCall(request).execute();
            //System.out.print(response);
            RenewalResponse resp_ = mapper.readValue(response.body().string(), RenewalResponse.class);
        
            if("Success".equals(resp_.getStatus())){
                resp.setCode(0);
            }
            else{
                resp.setMessage(resp_.getMessage());    
            }
            
            return resp;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in renew account", ex);
            resp.setMessage("An error occured");
            return resp;
        }
     
    }
}
