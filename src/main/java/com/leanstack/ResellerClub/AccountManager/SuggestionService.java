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
import com.leanstack.ResellerClub.responses.ErrorResponse;
import com.leanstack.ResellerClub.responses.ErrorResponseJson;
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
public class SuggestionService extends BaseClass {
    
    private static final Logger logger = Logger.getLogger(SuggestionService.class.getName());
  
    public SuggestionService(String service, String login, String key){
        super(service, login , key);
        
    }
    
    public SuggestionResponse search(String domain_name, String tld, List<String> tlds) {
        
         SuggestionResponse resp = new SuggestionResponse();
         resp.setCode(10);
         
         String suggestion = "";
         for(String val : tlds){
             suggestion += "&tld-only="+val;
         }
         
         String url = this.getKA_SERVICE()
                 +"domains/v5/suggest-names?auth-userid="+this.getREMOTE_API_LOGIN()+""
                 + "&api-key="+this.getAPIKEY()+"&no-of-results=15&exact-match=False&"
                 + "keyword="+domain_name+suggestion;
         
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
            List<String> availableDomains = parseJsonSpecification(response);
            for(String available : availableDomains){
                System.out.print(available + "\n");
            }
            
            resp.setCode(0);
            resp.setAvailableDomains(availableDomains);
                
            return resp;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception found in search domain name", ex);
            resp.setMessage("An error occured");
            
            return resp;
        }
    }
    
    public List<String> parseJsonSpecification(String jsonString) throws IOException {
        JsonFactory factory = new JsonFactory();
        
        List<String> availableDomains = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(jsonString);  

        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {

            Map.Entry<String,JsonNode> field = fieldsIterator.next();
            //System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
            
            availableDomains.add(field.getKey());
        }
        
        return availableDomains;
    }
}

