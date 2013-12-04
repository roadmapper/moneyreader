package StockPortfolio.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DataAccess {

	private URL url;
	private HttpURLConnection connection;
	private JsonObject quote;

	public DataAccess(String symbol) throws MalformedURLException {
		this.url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22" + symbol + "%22)&format=json&diagnostics=false&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"); // forming url for request
	}

	public void connect() throws Exception {
		connection = (HttpURLConnection) url.openConnection(); // this uses a factory! :D
		connection.setDoOutput(true); // Tells the connection to look for output?
		connection.setInstanceFollowRedirects(false); // ?
		connection.setRequestMethod("GET"); // can change request type here
		connection.setRequestProperty("Content-Type", "application/json"); // set type to JSON
	}

	public void getJSONQuote() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream()))); // Getting the response
		// from the webservice

		String output;
		String finalJSON = "";

		while ((output = br.readLine()) != null) {
			finalJSON = finalJSON + output; // building the response in JSON
			// format
		}

		JsonElement jelement = new JsonParser().parse(finalJSON);
		JsonObject jobject = jelement.getAsJsonObject().getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote");
		quote = jobject;
	}

	public void close() throws IOException {
		connection.getResponseCode();
		connection.disconnect();
	}

	public String getName() {
		return this.quote.get("Name").toString().replaceAll("\"", "");
	}

	public String getSymbol() {
		return this.quote.get("Symbol").toString();
	}

	public float getLastPrice() {
		return this.quote.get("LastTradePriceOnly").getAsNumber().floatValue();
	}

	public float getChange() {
		return this.quote.get("Change").getAsNumber().floatValue();
	}

	public String getMarket() {
		return this.quote.get("StockExchange").toString();
	}
}
