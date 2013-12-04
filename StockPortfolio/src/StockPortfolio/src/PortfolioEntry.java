package StockPortfolio.src;

// This is a test comment!

public interface PortfolioEntry {

	public String getName();

	public String getTicker();

	public float getLastPrice();

	public float getChange();
	
	public float getShares();

	public void update();

}
