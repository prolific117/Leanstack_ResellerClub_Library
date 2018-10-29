/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.AccountManager;

import com.leanstack.ResellerClub.responses.AccountCreationResponse;
import com.leanstack.ResellerClub.responses.ErrorResponse;
import com.leanstack.ResellerClub.base.StrongPasswordGen;
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
public class AccountCreationService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(AccountCreationService.class.getName());
  
    public AccountCreationService(String service, String login, String key){
        super(service, login , key);
        
    }
    
    public ResellerResponse createCustomerAccount(String customerId, String phone, String firstname,
            String zip, String lastname, String city, String address,
            String state, String password, String userEmail) throws JAXBException, Exception{
        
         ResellerResponse resp = new ResellerResponse();
         resp.setCode(10);
         
         if(phone.startsWith("0")){
             phone = "234" + phone.substring(1, phone.length());
         }
         
         String url = this.getKA_SERVICE()+
                  "customers/signup.xml?auth-userid="+this.getREMOTE_API_LOGIN()+
                  "&api-key="+this.getAPIKEY()+"&username="+userEmail+
                  "&passwd="+password+
                  "&name="+URLEncoder.encode(firstname+ " "+lastname, "UTF-8")+"&company="+URLEncoder.encode(firstname+ " "+lastname, "UTF-8")+
                  "&address-line-1="+URLEncoder.encode(address, "UTF-8")+
                  "&city="+URLEncoder.encode(city, "UTF-8")+"&state="+URLEncoder.encode(state, "UTF-8")+
                  "&country=NG&zipcode="+ zip +"&phone-cc=234&"+
                  "phone="+phone.substring(3, phone.length())+"&lang-pref=en";
         
         OkHttpClient client = new OkHttpClient();
         //ObjectMapper mapper = new ObjectMapper();
      
         try {
            Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Cache-Control", "no-cache")
              .build();

            String response = client.newCall(request).execute().body().string();
            //StringReader reader = new StringReader(response.body().string());

            //try to extract int
            
            if(response.startsWith("<response>")){
                //initialize jaxb classes
                JAXBContext context = JAXBContext.newInstance(ErrorResponse.class);
                Unmarshaller un = context.createUnmarshaller();
                //convert to desired object
                StringReader reader = new StringReader(response);
                ErrorResponse Data = (ErrorResponse)un.unmarshal(reader);
               
                resp.setMessage(Data.message);
                return resp;
                
            }
            
            else{
                String intValue = response.replaceAll("[^0-9]", "");
               
                System.out.print(response);
                resp.setCode(0);
                resp.setMessage(intValue+"");
                
                return resp;
            }
           
                    
            
        
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in create reseller club account", ex);
            resp.setMessage("An error occured");
            return resp;
        }
    }
}
