package StockPortfolio.src;

public class EntryFactory {
	public PortfolioEntry createEntry(String type, Object args){
		String arguments[] = (String[]) args;
		PortfolioEntry entry = null; 
		switch (type){
			case "stock":
				entry = new Stock(arguments[0], Float.parseFloat(arguments[1]));
				break;
			case "index":
				break;
			default:
				break;
		}
		return entry;
	}
}
