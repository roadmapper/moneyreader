package StockPortfolio.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {

	// Singleton instance of the Portfolio class.
	private static Portfolio instance;

	private ArrayList<PortfolioEntry> entryList;

	private Portfolio() {
		entryList = new ArrayList<PortfolioEntry>();
	}

	public static Portfolio getInstance()

	{
		if (instance == null)
			instance = new Portfolio();

		return instance;
	}

	public void displayEntries() {
		if (entryList.size() <= 0) {
			System.out.println("----------------------------------------------------");
			System.out.println("Portfolio currently empty, Command 2 to add a stock!");
			System.out.println("----------------------------------------------------");
			System.out.println();
			return;
		}
		System.out.println("Portfolio Entries");
		System.out.println("------------------------");
		String alignFormat = "| %-15s | %-8s | %-11f | %-7f | %-10s | %-10f |%n";
		String rowSeparator = "+-----------------+----------+-------------+-----------+------------+------------+";
		// Print the table header.
		System.out.println(rowSeparator);
		System.out.printf("|      Name       |  Symbol  | Last Price  | Change    | Market     | Num Shares |%n");
		System.out.println(rowSeparator);

		for (int i = 0; i < entryList.size(); i++) {
			if(entryList.get(i) instanceof Stock){
				System.out.format(
					alignFormat,
					entryList.get(i).getName().substring(0,	Math.min(entryList.get(i).getName().length(), 15)), entryList.get(i).getTicker(), entryList.get(i).getLastPrice(),
					entryList.get(i).getChange(), ((Stock) entryList.get(i)).getMarket(), (entryList
							.get(i)).getShares());
			}
			else{
				System.out.format(
						alignFormat,
						entryList.get(i).getName().substring(0,	Math.min(entryList.get(i).getName().length(), 15)), entryList.get(i).getTicker(), entryList.get(i).getLastPrice(),
						entryList.get(i).getChange(), "---------", (entryList
								.get(i)).getShares());
			}
			System.out
					.format("+-----------------+----------+-------------+-----------+------------+------------+%n");
		}

		System.out.println();

	}

	public void displayCommands() {
		System.out.println("1 - View Individual Stock Data ");
		System.out.println("2 - Add a Stock ");
		System.out.println("3 - Add an Index");
		System.out.println("4 - View Portfolio ");
		System.out.println("5 - Update Portfolio");
		System.out.println("6 - Remove a Stock ");
		System.out.println("0 - Close Application ");
	}
	
	private void displayTicker(PortfolioEntry entry) {
		String alignFormat = "| %-15s | %-8s | %-11f | %-7f |%n";
		String rowSeperator = "+-----------------+----------+-------------+-----------+";
		System.out.printf("|      Name       |  Symbol  | Last Price  |  Change   |%n");
		System.out.println(rowSeperator);
		System.out.format(alignFormat, entry.getName().substring(0, Math.min(entry.getName().length(), 15)),
				entry.getTicker(), entry.getLastPrice(), entry.getChange());
		System.out.println(rowSeperator);
		System.out.println();

	}

	public void update() {
		for (int i = 0; i < entryList.size(); i++) {
			entryList.get(i).update();
		}
	}

	public void addStock(String ticker) {
		System.out.println("How many shares? (Integer):");
		Scanner keyboard = new Scanner(System.in);
		String numShares = keyboard.nextLine();
		Stock s = (Stock) EntryFactory.createEntry(EntryType.STOCK, new String[] {
				ticker, numShares });
		s.update();
		entryList.add(s);
	}
	
	private void addIndex(String ticker) {
		Index i = (Index) EntryFactory.createEntry(EntryType.INDEX, new String[] {
				ticker });
		i.update();
		entryList.add(i);
	}

	private void removeEntry(String ticker) {
		for (int i = 0; i < entryList.size(); i++) {
			if (entryList.get(i).getTicker().equals(ticker)) {
				entryList.remove(i);
			}
		}
	}
	
	public static void main(String[] args) {

		Portfolio portfolio = Portfolio.getInstance();

		// Retrieve user input for Portfolio actions
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Personal Stock Portfolio");
		System.out.println("------------------------");
		System.out.println("Enter a command: ");
		portfolio.displayCommands();

		String input = keyboard.nextLine();

		while (!input.equals("0")) {

			switch (input) {

			// Individual Stock Data
			case "1":
				System.out.println("Enter a stock ticker symbol:");
				String ticker = keyboard.nextLine();
				Stock s = (Stock) EntryFactory.createEntry(EntryType.STOCK,
						new String[] { ticker, "0" });
				s.update();
				portfolio.displayTicker(s);
				portfolio.displayCommands();
				break;
			// Add Stock
			case "2":
				System.out
						.println("Enter the ticker symbol of the stock you would like to add:");
				ticker = keyboard.nextLine();
				portfolio.addStock(ticker);
				portfolio.displayCommands();
				break;
			// Add index
			case "3":
				System.out.println("Enter the ticker symbol of the index (e.g NASDAQ) you would like to add:");
				ticker = keyboard.nextLine();
				portfolio.addIndex(ticker);
				portfolio.displayCommands();
				break;
			// View Portfolio
			case "4":

				portfolio.displayEntries();
				portfolio.displayCommands();
				break;
			// Update Portfolio
			case "5":
				System.out.println("Updating Portfolio...");
				portfolio.update();
				System.out.println("Done.");
				break;
			// Remove Stock
			case "6":
				System.out.println("Enter the ticker symbol of the stock you would like to remove:");
				ticker = keyboard.nextLine();
				portfolio.removeEntry(ticker);
				portfolio.displayCommands();
				break;
			case "":
				System.out.println();
				break;
			default:
				System.out.println("Incorrect Input. Enter Valid Command!");
				portfolio.displayCommands();
				break;

			}

			try {
				input = keyboard.nextLine();
			} catch (Exception e) {
				System.out.println("Only Integers! Try again.");
				input = keyboard.nextLine();
			}
		}
		// portfolio.displayEntries();
		keyboard.close();
	}

	

	

	

}
