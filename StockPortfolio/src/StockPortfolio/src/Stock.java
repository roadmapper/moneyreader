package StockPortfolio.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Stock implements PortfolioEntry {
	private String name;
	private String symbol;
	private float lastPrice;
	private float change;
	private String market;
	private float numShares;
	
	public Stock(String symbol) {
		this.symbol = symbol;
		
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	@Override
	public void update() {
		try {
			
			URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22" + this.symbol + "%22)&format=json&diagnostics=false&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"); 		//forming url for request
			
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
			/*Gson gson = new Gson();
		    Collection collection = new ArrayList();
		    collection.add("hello");
		    collection.add(5);
		    String json = gson.toJson(collection);
		    System.out.println("Using Gson.toJson() on a raw collection: " + json);
		    JsonParser parser = new JsonParser();
		    JsonArray array = parser.parse(json).getAsJsonArray();
		    String message = gson.fromJson(array.get(0), String.class);*/
			
		    JsonElement jelement = new JsonParser().parse(finalJSON);
		    JsonObject  jobject = jelement.getAsJsonObject();
		    jobject = jobject.getAsJsonObject("query");
		    jobject = jobject.getAsJsonObject("results");
		    jobject = jobject.getAsJsonObject("quote");
		    System.out.println(jobject.get("Name"));
		    
			System.out.println(finalJSON);

			  connection.getResponseCode(); 
			connection.disconnect(); 

		}
		catch(Exception e) { 
        	throw new RuntimeException(e); 
        }

	}

	@Override
	public char[] getTicker() {
		// TODO Auto-generated method stub
		return null;
	}

}
