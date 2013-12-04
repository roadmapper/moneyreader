package StockPortfolio.src;

public class Index implements PortfolioEntry {

	private String name;
	private String symbol;
	private float lastPrice;
	private float change;

	public Index(String symbol) {
		this.symbol = symbol;
		try {
			DataAccess d = new DataAccess(this.symbol);
			d.connect();
			d.getJSONQuote();
			this.name = d.getName();
			d.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getTicker() {
		return this.symbol;
	}

	@Override
	public float getLastPrice() {
		return this.lastPrice;
	}

	@Override
	public float getChange() {
		return this.change;
	}

	@Override
	public void update() {
		try {
			DataAccess d = new DataAccess(this.symbol);
			d.connect();
			d.getJSONQuote();
			this.lastPrice = d.getLastPrice();
			this.change = d.getChange();
			d.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
