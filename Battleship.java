import java.util.Scanner;
public class Battleship {
	public static void main(String[] args) {

		System.out.println("Welcome to Battleship!");

		char[][] player1_board = shipPlacement("Player 1");
		printBattleShip(player1_board);

		

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

	private static char[][] shipPlacement(String playerName) {

		char[][] playerBoard = createGrid();

		System.out.println(playerName + ", ENTER YOUR SHIP'S COORDINATES");

		Scanner input = new Scanner(System.in);

		
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
}