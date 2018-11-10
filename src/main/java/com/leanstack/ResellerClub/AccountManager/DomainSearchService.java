/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanstack.ResellerClub.responses.DomainSearchResponse;
import com.leanstack.ResellerClub.responses.ErrorResponse;
import com.leanstack.ResellerClub.responses.ErrorResponseJson;
import com.leanstack.ResellerClub.responses.ResellerResponse;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author prolific
 */
public class DomainSearchService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(DomainSearchService.class.getName());
  
    public DomainSearchService(String service, String login, String key){
        super(service, login , key);
        
    }
    
    public ResellerResponse search(String domain_name, String tld) throws JAXBException, Exception{
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         
         String url = this.getKA_SERVICE()+
                  "domains/available.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&"
                 + "domain-name="+domain_name+"&tlds="+tld;
         
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
            
            //try to extract int
            DomainSearchResponse resp_ = mapper.readValue(response, DomainSearchResponse.class);
            resp.setMessage(resp_.getUnknownFields().get(domain_name+"."+tld).getStatus());
            
            if("available".equals(resp.getMessage()))
                resp.setCode(0);
                
            return resp;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in search domain name", ex);
            resp.setMessage("An error occured");
            return resp;
        }
    }
}
