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

	public Stock(String symbol, float shares) {
		this.symbol = symbol;
		this.numShares = shares;
	}

	@Override
	public String getTicker() {
		return this.symbol;
	}

	@Override
	public void update() {
		try {

			URL url = new URL(
					"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22"
							+ this.symbol
							+ "%22)&format=json&diagnostics=false&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"); // forming
																															// url
																															// for
																															// request
			// {"query":{"count":1,"created":"2013-12-03T21:27:44Z","lang":"en-US","results":{"quote":{"symbol":"GOOG","AverageDailyVolume":"1811780","Change":"-1.22","DaysLow":"1049.02","DaysHigh":"1063.4399","YearLow":"682.33","YearHigh":"1068.00","MarketCapitalization":"351.9B","LastTradePriceOnly":"1053.26","DaysRange":"1049.02 - 1063.4399","Name":"Google Inc.","Symbol":"GOOG","Volume":"1650650","StockExchange":"NasdaqNM"}}}}
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection(); // this uses a factory! :D
			connection.setDoOutput(true); // Tells the connection to look for
											// output?
			connection.setInstanceFollowRedirects(false); // ?
			connection.setRequestMethod("GET"); // can change request type here
			connection.setRequestProperty("Content-Type", "application/json"); // ?

			OutputStream os = connection.getOutputStream();
			os.flush(); // ?

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(connection.getInputStream()))); // Getting the response
														// from the webservice

			String output;
			String finalJSON = "";

			while ((output = br.readLine()) != null) {
				finalJSON = finalJSON + output; // building the response in JSON
												// format
			}

			JsonElement jelement = new JsonParser().parse(finalJSON);
			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("query");
			jobject = jobject.getAsJsonObject("results");
			jobject = jobject.getAsJsonObject("quote");
			this.name = jobject.get("Name").toString();
			this.symbol = jobject.get("Symbol").toString();
			this.lastPrice = jobject.get("LastTradePriceOnly").getAsNumber().floatValue();
			this.change = jobject.get("Change").getAsNumber().floatValue();
			// this.name = jobject.get("");
			System.out.println();

			// System.out.println(finalJSON);

			connection.getResponseCode();
			connection.disconnect();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getLastPrice() {
		return this.lastPrice;
	}

	@Override
	public float getChange() {
		return this.change;
	}
	
	public float getShares() {
		return this.numShares;
	}

}
