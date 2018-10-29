/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;


import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ContactCreationService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(ContactCreationService.class.getName());
  
    public ContactCreationService(String service, String login, String key){
        super(service, login , key);
        
    }
    
    public ResellerResponse createCustomerContact(String phone, String firstname,
            String zip, String lastname, String city, String address,
            int custId, String country, String userEmail) throws JAXBException, Exception{
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         
         if(phone.startsWith("0")){
             phone = "234" + phone.substring(1, phone.length());
         }
         
         String url = this.getKA_SERVICE()+
                  "contacts/add.json?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&"
                 + "name="+firstname + " "+lastname+"&company="+lastname+"&email="+userEmail+"&"
                 + "address-line-1="+address+"&city="+city+"&country="+country+"&zipcode="+zip
                 + "&phone-cc=234&phone="+phone.substring(3, phone.length())+"&customer-id="+custId+"&type=Contact";
         
         OkHttpClient client = new OkHttpClient();
         ObjectMapper mapper = new ObjectMapper();
      
         try {
            Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            String response = client.newCall(request).execute().body().string();
            
            //try to extract int
            try{
                int contactId = Integer.parseInt(response);
                resp.setCode(0);
                resp.setMessage(contactId+"");
                
                return resp;
            }
            catch(Exception ex){
            
                ErrorResponseJson errorResp = mapper.readValue(response, ErrorResponseJson.class);
                resp.setMessage(errorResp.getMessage());
                return resp;
            }
          
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in create reseller club account", ex);
            resp.setMessage("An error occured");
            return resp;
        }
    }
}
