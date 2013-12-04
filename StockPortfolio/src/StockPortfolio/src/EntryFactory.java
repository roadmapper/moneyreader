package StockPortfolio.src;

public class EntryFactory {
	public static PortfolioEntry createEntry(EntryType type, Object args) {
		String arguments[] = (String[]) args;
		PortfolioEntry entry = null;
		switch (type) {
		case STOCK:
			entry = new Stock(arguments[0], Float.parseFloat(arguments[1]));
			break;
		case INDEX:
			entry = new Index(arguments[0]);
			break;
		default:
			break;
		}
		return entry;
	}
}
