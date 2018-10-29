/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.DomainOrderResponse;
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
public class SubscriptionCreationService extends BaseClass{
    
    private static final Logger logger = Logger.getLogger(SubscriptionCreationService.class.getName());
  
    public SubscriptionCreationService(String service, String login, String password){
        super(service, login , password);
    }
    
    public ResellerResponse createSubscription(String domainName, String ns1, String ns2, 
            String contactId, int duration, String userId, Boolean whois ) throws Exception{
        
       ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         
         String url = this.getKA_SERVICE()+
                  "domains/register.xml?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&domain-name="+domainName+"&years="
                  +duration+"&"
                 + "ns="+ns1+"&ns="+ns2+"&customer-id="+userId+"&"
                 + "tech-contact-id="+contactId+"&billing-contact-id="+contactId+"&"
                 + "reg-contact-id="+contactId+"&admin-contact-id="+contactId+"&"
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
            
            JAXBContext context = JAXBContext.newInstance(DomainOrderResponse.class);
            Unmarshaller un = context.createUnmarshaller();
            StringReader reader = new StringReader(response);
            DomainOrderResponse data = (DomainOrderResponse)un.unmarshal(reader);
        
            List<Entry> entries = data.entries;
            //List
            System.out.print(entries.get(0).values.get(0));
        
            if("error".equals(entries.get(0).values.get(1))){
                resp.setMessage(entries.get(1).values.get(1));
                return resp;
            }
            else{
                resp.setCode(0);
                resp.setMessage(entries.get(10).values.get(1));
                return resp;
            }
          
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in create reseller club account", ex);
            resp.setMessage("An error occured");
            return resp;
        }
     
    }
}
