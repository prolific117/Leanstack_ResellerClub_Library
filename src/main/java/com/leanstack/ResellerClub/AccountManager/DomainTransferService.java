/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.DomainOrderResponse;
import com.leanstack.ResellerClub.responses.DomainSearchResponse;
import com.leanstack.ResellerClub.responses.DomainTransferResponse;
import com.leanstack.ResellerClub.responses.Entry;
import com.leanstack.ResellerClub.responses.ErrorResponseJson;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 * @author prolific
 */
public class DomainTransferService extends BaseClass{
    
    private static final Logger logger = Logger.getLogger(SubscriptionCreationService.class.getName());
  
    public DomainTransferService(String service, String login, String password){
        super(service, login , password);
    }
    
    public ResellerResponse createSubscription(String domainName, String ns1, String ns2, 
            String contactId, int duration, String userId, Boolean whois, String authCode ) throws Exception{
        
       ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         
         String url = this.getKA_SERVICE()+
                  "domains/transfer.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&domain-name="+domainName
                 + "&ns="+ns1+"&ns="+ns2+"&customer-id="+userId+"&"
                 + "tech-contact-id="+contactId+"&billing-contact-id="+contactId+"&"
                 + "reg-contact-id="+contactId+"&admin-contact-id="+contactId+"&"
                 + "&auth-code="+authCode+"&"
                 + "invoice-option=KeepInvoice&purchase-privacy="+whois;
         
         OkHttpClient client = new OkHttpClient();
         ObjectMapper mapper = new ObjectMapper();
      
         try {
            Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            String response = client.newCall(request).execute().body().string();
            
            DomainTransferResponse resp_ = mapper.readValue(response, DomainTransferResponse.class);
            System.out.print(response);
            
            if(!"error".equals(resp_.getStatus()) && !"ERROR".equals(resp_.getStatus()) ){
                resp.setCode(0);
                resp.setMessage(resp_.getPrivacydetails().getEntityid());
            }
            else{
                resp.setMessage(resp_.getError());
            }
                
            return resp;
          
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in create reseller club account", ex);
            resp.setMessage("An error occured");
            return resp;
        }
     
    }
}
