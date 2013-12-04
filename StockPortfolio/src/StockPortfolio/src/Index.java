package StockPortfolio.src;

public class Index implements PortfolioEntry {

	private String name;
	private String symbol;
	private float lastPrice;
	private float change;
	private String market;
	
	public Index(String symbol) {
		this.symbol = symbol;
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
		// TODO Auto-generated method stub
		
	}
	
}
