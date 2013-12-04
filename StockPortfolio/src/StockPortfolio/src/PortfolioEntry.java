package StockPortfolio.src;

public interface PortfolioEntry {

	public String getName();

	public String getTicker();

	public float getLastPrice();

	public float getChange();

	public void update();

}
