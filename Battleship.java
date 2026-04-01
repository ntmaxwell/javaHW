import java.util.Scanner;
public class Battleship {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to Battleship!");

		String player1 = "Player 1";
		String player2 = "Player 2";

		char[][] player1_board = shipPlacement(player1, input);
		printBattleShip(player1_board);
		print100();

		char[][] player2_board = shipPlacement(player2, input);
		printBattleShip(player2_board);
		print100();

		char[][] player1_targetHistory = createGrid();
		char[][] player2_targetHistory = createGrid();

		int player1_lifeTotal = 5;
		int player2_lifeTotal = 5;

		do {

			player2_lifeTotal = chooseTarget(player1_targetHistory, player2_board, player1, player2, input, player2_lifeTotal);
			if (player2_lifeTotal == 0) {
				break;
			}
			player1_lifeTotal = chooseTarget(player2_targetHistory, player1_board, player2, player1, input, player1_lifeTotal);
			if (player1_lifeTotal == 0) {
				break;
			}
		} while(true);

		if (player1_lifeTotal == 0) {
			System.out.println(player2 + " WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
			System.out.println(" ");
			System.out.println("Final Boards");
			System.out.println(" ");

			System.out.println("Player 1's Board");
			printBattleShip(player1_targetHistory);

			System.out.println("Player 2's Board");
			printBattleShip(player2_targetHistory);
		}
		else {
			System.out.println(player1 + " WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
			System.out.println(" ");
			System.out.println("Final Boards");
			System.out.println(" ");

			System.out.println("Player 1's Board");
			printBattleShip(player1_targetHistory);

			System.out.println("Player 2's Board");
			printBattleShip(player2_targetHistory);
		}
	

		

	}

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}

	private static char[][] createGrid() {
		char[][] gameBoard = new char[5][5];

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = '-';
			}
		}

			return gameBoard;
	}

	private static void print100() {
		for (int i = 0; i < 101; i++) {
			System.out.println(" ");
		}
	}

	private static char[][] shipPlacement(String playerName, Scanner input) {

		char[][] playerBoard = createGrid();

		System.out.println(playerName + ", ENTER YOUR SHIP'S COORDINATES");

		
		for (int i = 0; i < 5; i++) {

			boolean validInput = true;

			do {
				System.out.println("Enter ship " + (i + 1) + " location: ");
				String coordinates = input.nextLine();
				String[] parts = coordinates.split(" ");
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);

				if (x > 4 || x < 0 || y > 4 || y < 0) {
					System.out.println("Invalid Coordinates. Choose different coordinates (0-4)");
				}
				else if (playerBoard[x][y] == '@') {
					System.out.println("You already have a ship there. Choose different coordinates.");
				}
				else {
					playerBoard[x][y] = '@';
					validInput = false;
				}

			} while (validInput);

		}

		return playerBoard;
	}

	private static int chooseTarget (char[][] historyBoard, char[][] playerBoard, String player1, String player2, Scanner input, int lifeTotal) {

		System.out.println(player1 + ", enter hit row/column");

		boolean validInput = true;

		do {
			String coordinates = input.nextLine();
			String[] parts = coordinates.split(" ");
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);

			if (x > 4 || x < 0 || y > 4 || y < 0) {
				System.out.println("Invalid Coordinates. Choose different coordinates (0-4)");
				}
			else if (historyBoard[x][y] != '-') {
				System.out.println("You already fired on this spot. Choose different coordinates.");
			}
			else if (historyBoard[x][y] == '-') {
				if (playerBoard[x][y] == '-') {
					System.out.println(player1 + " MISSED!");
					historyBoard[x][y] = 'O';
					System.out.println(player1 + "'s Target History Board");
					printBattleShip(historyBoard);
					validInput = false;
				}
				else if (playerBoard[x][y] == '@') {
					System.out.println(player1 + " HIT " + player2 + "'S SHIP!");
					historyBoard[x][y] = 'X';
					System.out.println(player1 + "'s Target History Board");
					printBattleShip(historyBoard);
					lifeTotal--;
					System.out.println(player2 + "'s LIFE TOTAL: " + lifeTotal);
					validInput = false;
				}
			}
			else {
				System.out.println("WE RAN INTO AN UNEXPECTED ERROR");
			}
		} while (validInput);

		return lifeTotal;

	}
}