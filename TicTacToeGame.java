import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize or reset the board
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if the board is full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for a win
    public boolean checkForWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check the rows for a win
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check the columns for a win
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check the diagonals for a win
    private boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer));
    }

    // Change player marks back and forth
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Place a mark at the cell specified by row and col with the current player's mark
    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    // Get the current player
    public char getCurrentPlayer() {
        return currentPlayer;
    }
}

public class TicTacToeGame 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        game.initializeBoard();
        System.out.println("Tic-Tac-Toe Game!");
        game.printBoard();

        // Log moves to a file
        try {
            File file = new File("D://Filling01//TicTacToeMoves.txt");
            if (file.createNewFile()) {
                System.out.println("Log file created: " + file.getName());
            } else {
                System.out.println("Log file already exists.");
            }

            PrintStream out = new PrintStream(file);
            out.println("Tic-Tac-Toe Game Log:");

            while (true) {
                int row, col;
                System.out.println("Player " + game.getCurrentPlayer() + ", enter your move (row and column): ");
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (game.placeMark(row, col)) {
                    game.printBoard();
                    out.println("Player " + game.getCurrentPlayer() + " placed mark at row " + row + " col " + col);
                    if (game.checkForWin()) {
                        System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                        out.println("Player " + game.getCurrentPlayer() + " wins!");
                        break;
                    }
                    if (game.isBoardFull()) {
                        System.out.println("The game is a tie!");
                        out.println("The game is a tie!");
                        break;
                    }
                    game.changePlayer();
                } else {
                    System.out.println("This move is not valid");
                }
            }

            out.close();
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        scanner.close();
    }
}
