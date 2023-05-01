import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    char [][] board = {{' ',' ',' '},
                       {' ',' ',' '},
                       {' ',' ',' '}};

    while (true) {
        printBoard(board);
        userTurn(board);
        if (isGameover(board)) break;
        botTurn(board);
    }
    }

    private static boolean isGameover(char[][] board) {

        if (whoWon(board, 'X')){
            printBoard(board);
            System.out.println("You won! （＾∀＾●）ﾉｼ ");
            return true;
        }

        if (whoWon(board, 'O')) {
            printBoard(board);
            System.out.println("Bots won! (⌐■_■) ");
            return true;
        }

        for (int i = 0; i < board.length; i++) { // check if any empty
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') return false;
            }
        }

        printBoard(board);
        System.out.println("it's a Tie (´。＿。｀) ");
        return true;
    }

    private static boolean whoWon (char[][] board, char symbol) {
        if (    (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol)|| // column
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol)||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol)||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol)|| // row
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol)||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol)||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)|| // diagonal
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) ) {
            return true;
        }
        return false;
    }

    private static void botTurn(char[][] board) {
        Random rand = new Random();
        int botMove;
        while (true){
            botMove =rand.nextInt(9)+1; // bot choose from (0-8) +1
            if (isValidMove(board, botMove)) {
                break;
            }
        }
        System.out.println(" The bot chose: " +  botMove);
        placeMove(board,Integer.toString(botMove), 'O');
    }

    private  static boolean isValidMove (char[][] board, int position){
        return switch (position) {
            case 1 -> (board[0][0] == ' ');
            case 2 -> (board[0][1] == ' ');
            case 3 -> (board[0][2] == ' ');
            case 4 -> (board[1][0] == ' ');
            case 5 -> (board[1][1] == ' ');
            case 6 -> (board[1][2] == ' ');
            case 7 -> (board[2][0] == ' ');
            case 8 -> (board[2][1] == ' ');
            case 9 -> (board[2][2] == ' ');
            default -> false;
        };
    }

    private static void userTurn (char[][] board) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            System.out.println("where would you like to play? (1-9): ");
            userInput = scanner.nextLine();
            if (isValidMove(board, Integer.parseInt(userInput))) {
                break;
            } else {
                System.out.println(userInput + "is an invalid move");
            }
        }
        placeMove(board,userInput, 'X');
    }

    private static void placeMove (char[][] board, String position, char symbol) {
        switch (position) {
            case "1" -> board[0][0] = symbol;
            case "2" -> board[0][1] = symbol;
            case "3" -> board[0][2] = symbol;
            case "4" -> board[1][0] = symbol;
            case "5" -> board[1][1] = symbol;
            case "6" -> board[1][2] = symbol;
            case "7" -> board[2][0] = symbol;
            case "8" -> board[2][1] = symbol;
            case "9" -> board[2][2] = symbol;
            default -> System.out.println(":(");
        }
    }

    private static void printBoard (char[][] board) {
        System.out.println(board[0][0]+ "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0]+ "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0]+ "|" + board[2][1] + "|" + board[2][2]);
    }
}