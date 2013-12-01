import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class test {

	public static void main(String[] args) {
		
		System.out.print("Welcome to our Stock App!  Please enter a stock smybol to look up: ");
		
		Scanner keyboard = new Scanner(System.in);
		String stock = keyboard.nextLine();				//user input for stock symbol
		
		System.out.println("Looking up information about " + stock + "...");
		
		//initializing gui?
        JFrame  appFrame = new JFrame("4240 Stock App");
        appFrame.setSize(800, 600);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create a label
        //JLabel appLabel = new JLabel("4240 Stock App");
        //appFrame.add(appLabel);
        
        //Create a text thing
        JTextArea stockText = new JTextArea();
        //stockText.setText("Hello World!");    //That works.
        appFrame.add(stockText);
        
        //appFrame.setVisible(true);	//actually makes it visible
       
        try { 
        	//skeleton code from stackexchange.  It works.  Woo.
            URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22" + stock + "%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"); 		//forming url for request
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
            
            stockText.setText(finalJSON);		//putting this stuff on our gui
            System.out.println(finalJSON);
        	
        	  connection.getResponseCode(); 
            connection.disconnect(); 
            
        }
        
        catch(Exception e) { 
        	throw new RuntimeException(e); 
        }   
    }
}
