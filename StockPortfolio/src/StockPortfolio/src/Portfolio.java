package StockPortfolio.src;

import java.util.ArrayList;

public class Portfolio {

	private static Portfolio instance;

	private ArrayList<PortfolioEntry> entryList;

	private Portfolio() {
		entryList = new ArrayList<PortfolioEntry> ();
		instance.addEntry("NYSE");
		instance.addEntry("SP500");
	}

	public static Portfolio getInstance()

	{
		if (instance == null)
			instance = new Portfolio();

		return instance;
	}

	public void display() {

	}

	public void update() {
		for (int i = 0; i < entryList.size(); i++){
			entryList.get(i).update();
		}
	}

	public boolean addEntry(String string) {

		return false;

	}

	public boolean removeStock() {

		return false;

	}

	public static void main(String[] args) {

		Portfolio portfolio = Portfolio.getInstance();
		
		
	}

}
