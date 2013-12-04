package StockPortfolio.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {

	// Singleton instance of the Portfolio class.
	private static Portfolio instance;

	private ArrayList<PortfolioEntry> entryList;

	private Portfolio() {
		entryList = new ArrayList<PortfolioEntry>();
		addEntry("NYSE");
		addEntry("SP500");
	}

	public static Portfolio getInstance()

	{
		if (instance == null)
			instance = new Portfolio();

		return instance;
	}

	public void displayEntries() {

		String alignFormat = "| %-15s | %-8s | %-11d | %-6d | %-6d | %-10d |%n";
		String rowSeperator = "+-----------------+----------+-------------+--------+--------+------------+";
		// Print the table header.
		System.out.println(rowSeperator);
		System.out
				.printf("|      Name       |  Symbol  | Last Price  | Change | Market | Num Shares |%n");
		System.out.println(rowSeperator);

		for (int i = 0; i < 5; i++) {
			System.out.format(alignFormat, "some data" + i, i * i, i, i, i, i);
			System.out
					.format("+-----------------+----------+-------------+--------+--------+------------+%n");
		}
		
		System.out.println();

	}

	public void displayCommands() {
		System.out.println("1 - View Individual Stock Data ");
		System.out.println("2 - Add a Stock ");
		System.out.println("3 - View Portfolio ");
		System.out.println("4 - Update Portfolio");
		System.out.println("5 - Remove a Stock ");
		System.out.println("0 - Close Application ");
	}

	public void update() {
		for (int i = 0; i < entryList.size(); i++) {
			entryList.get(i).update();
		}
	}

	public boolean addEntry(String string) {

		return false;

	}

	public boolean removeEntry() {

		return false;

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
			
			//Individual Stock Data
			case "1":
				System.out.println("Enter a stock ticker symbol:");
				String ticker = keyboard.nextLine();
				Stock s = (Stock) EntryFactory.createEntry("stock", new String[]{ticker, "0"});
				s.update();
				portfolio.displayTicker(s);
				portfolio.displayCommands();
				break;
			//Add Stock 
			case "2":
				System.out.println("Enter the  ticker symbol of the stock you would like to add:");
				ticker = keyboard.nextLine();
				if (isIndex(ticker)){
					System.out.println("How many shares? (Integer):");
					int numShares = keyboard.nextInt();
				}
				//Factory Method??
				portfolio.displayCommands();
				break;
			//View Portfolio	
			case "3":
				portfolio.displayEntries();
				portfolio.displayCommands();
				break;
			//Remove a Stock	
			case "4":
				System.out.println("Updating Portfolio...");
				portfolio.update();
				System.out.println("Done.");
			case "5":
				System.out.print("Entries to remove (type ticker): \n");
				for(int i = 0; i < portfolio.entryList.size(); i++){
					//Get ticker can change
					System.out.print(portfolio.entryList.get(i).getTicker());
					if(i != portfolio.entryList.size() -1)
						System.out.print(", ");
					else 
						System.out.println(":");
				}
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

	}

	private static boolean isIndex(String ticker) {
		// TODO Auto-generated method stub
		return false;
	}

	private void displayTicker(PortfolioEntry entry) {
		String alignFormat = "| %-15s | %-8s | %-11f | %-8f |%n";
		String rowSeperator = "+-----------------+----------+-------------+----------+";
		System.out
		.printf("|      Name       |  Symbol  | Last Price  |  Change  |%n");
		System.out.println(rowSeperator);
		System.out.format(alignFormat, entry.getName(), entry.getTicker(), entry.getLastPrice(), entry.getChange());
		System.out.println(rowSeperator);
		System.out.println();

	}

}
