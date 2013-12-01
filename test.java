import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		System.out.print("Welcome to our Stock App!  Please enter a stock smybol to look up: ");
		
		Scanner keyboard = new Scanner(System.in);
		String stock = keyboard.nextLine();				//user input for stock symbol
		
		System.out.println("Looking up information about " + stock + "...");
		
		
        try { 

        	//skeleton code from stackexchange.  It works.  Woo.
            URL url = new URL("http://dev.markitondemand.com/Api/v2/Quote/jsonp?symbol=" + stock); 		//forming url for request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 	//this uses a factory! :D
            connection.setDoOutput(true); 						//Tells the connection to look for output?
            connection.setInstanceFollowRedirects(false); 		//?
            connection.setRequestMethod("GET"); 				//can change request type here
            connection.setRequestProperty("Content-Type", "application/json"); 		//?

            OutputStream os = connection.getOutputStream(); 
            os.flush(); 	//?
            
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream()))); // Getting the response from the webservice

            String output;
            String finalJSON = "";
            
            while ((output = br.readLine()) != null) {
                finalJSON = finalJSON + output;			//building the response in JSON format
            }
            
            System.out.println(finalJSON);
               
            connection.getResponseCode(); 
            connection.disconnect(); 
            
        } catch(Exception e) { 
            throw new RuntimeException(e); 
        } 

    }

}