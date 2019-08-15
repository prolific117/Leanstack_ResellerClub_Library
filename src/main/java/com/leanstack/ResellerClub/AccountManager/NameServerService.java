/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.DomainSearchResponse;
import com.leanstack.ResellerClub.responses.DomainTransferResponse;
import com.leanstack.ResellerClub.responses.ErrorResponse;
import com.leanstack.ResellerClub.responses.ErrorResponseJson;
import com.leanstack.ResellerClub.responses.NameServerResponse;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import com.leanstack.ResellerClub.responses.SuggestionResponse;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author prolific
 */
public class NameServerService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(SuggestionService.class.getName());
  
    public NameServerService(String service, String login, String key){
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
    
    public ResellerResponse update(String domain_name, String ns1, String ns2) {
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         String orderId = getOrderId(domain_name);
         //System.out.print("Order id is "+orderId);
        
         String url = this.getKA_SERVICE()
                 +"domains/modify-ns.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()
                 + "&ns="+ns1
                 + "&ns="+ns2
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
            NameServerResponse resp_ = mapper.readValue(response,  NameServerResponse.class);
           
            //System.out.print(resp_.getStatus());
            if(!"error".equals(resp_.getStatus()) && !"ERROR".equals(resp_.getStatus()) ){
                resp.setCode(0);
                resp.setMessage(resp_.getStatus());
            }
            else{
                resp.setMessage(resp_.getMessage());
            }
                
            System.out.print(resp.getMessage());
            return resp;
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in search domain name", ex);
            resp.setMessage("An error occured");
            
            return resp;
        }
    }
    
   
}

