/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxhttprequest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author siddhartha
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblName,lblAge;
    Integer ss = 12;
     private final String USER_AGENT = "Mozilla/5.0";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       lblName.setText("Name: --");
            lblAge.setText("Age: --");
       
    }  
    
    @FXML
   private void btnConnect(Event e) throws MalformedURLException, IOException{
     
        try {
            
            String url = "https://creative-lizard.000webhostapp.com/getDetailse.php";
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // optional default is GET
            con.setRequestMethod("GET");
            
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            
            StringBuffer response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            
            
            JSONParser parser = new JSONParser();
            JSONObject parse = (JSONObject)parser.parse(response.toString());
            
            JSONObject data = (JSONObject) parse.get("data");
            
            String sName = (String) data.get("name");
            Long sAge = (Long) data.get("age");
            lblName.setText("Name: "+sName);
            lblAge.setText("Age: "+sAge);
           
            
        }    catch (ParseException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
              
   }


  
    
}
