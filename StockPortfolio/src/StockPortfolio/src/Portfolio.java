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

	}

	public void displayCommands() {
		System.out.println("1 - View Individual Stock Data ");
		System.out.println("2 - Add a Stock ");
		System.out.println("3 - View Portfolio ");
		System.out.println("4 - Remove a Stock ");
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

		String inputString = keyboard.nextLine();
		int input;
		
		try {
			input = Integer.parseInt(inputString);
		} catch (Exception e) {
			System.out.println("Only Integers! Try again.");
			input = Integer.parseInt(keyboard.nextLine());
		}

		while (input != 0) {

			switch (input) {

			case 1:
				System.out.println("Enter a ticker symbol:");
				String ticker = keyboard.nextLine();
				portfolio.displayTicker(ticker);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("Incorrect Command Number. Valid Commands:");
				portfolio.displayCommands();
				break;

			}

			input = Integer.parseInt(keyboard.nextLine());
		}
		// portfolio.displayEntries();

	}

	private void displayTicker(String ticker) {
		String alignFormat = "| %-15s | %-8s | %-11d | %-6d | %-6d | %-10d |%n";
		String rowSeperator = "+-----------------+----------+-------------+--------+--------+------------+";
		System.out.println(rowSeperator);
		System.out.format(alignFormat, "ticker" + 1, 1 * 1, 1, 1, 1, 1);
		System.out.println(rowSeperator);

	}

}
