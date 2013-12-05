package StockPortfolio.src;

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
		// Get
		try {
			DataAccess d = new DataAccess(this.symbol);
			d.connect();
			d.getJSONQuote();
			this.name = d.getName();
			String marketQuery = d.getMarket();
			if (!(marketQuery.equals("null")))
				this.market = marketQuery;
			d.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	@Override
	public String getTicker() {
		return this.symbol;
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
			//e.printStackTrace();
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

	public String getMarket() {
		return this.market;
	}

}
