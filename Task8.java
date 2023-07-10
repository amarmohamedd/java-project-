import java.util.Scanner;

class Task8 {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        // initialize the empty grid to play X-O
        char[][] board = {{EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}};

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("You are playing as 'X'. Enter your moves as row and column numbers (0-2).");

        // first boolean to know which player's turn, second one to stop the game once one player wins
        boolean playerXTurn = true;
        boolean gameOver = false;

        /*
        while a player has not won yet, print the board and get an input from the player, then make the play for the computer based on the algorithm
        then print the board again and check for the winning condition, if true then print the score and stop the game, else keep playing
         */
        while (!gameOver) {
            printBoard(board);

            if (playerXTurn) {
                makePlayerMove(board, PLAYER_X, scanner);
            } else {
                makeComputerMove(board);
            }

            if (isGameOver(board)) {
                printBoard(board);
                gameOver = true;
                if (hasPlayerWon(board, PLAYER_X)) {
                    System.out.println("Congratulations! You won!");
                } else if (hasPlayerWon(board, PLAYER_O)) {
                    System.out.println("Sorry! You lost!");
                } else {
                    System.out.println("It's a draw!");
                }
            }

            playerXTurn = !playerXTurn;
        }

        scanner.close();
    }

    // Function to evaluate the score of a game state
    // this function evaluates all the paths and returns 1 if player wins, 0 if draw, -1 if CPU wins
    private static int evaluate(char[][] board) {
        if (hasPlayerWon(board, PLAYER_X)) {
            return 1;
        } else if (hasPlayerWon(board, PLAYER_O)) {
            return -1;
        } else if (isBoardFull(board)) {
            return 0; // Draw
        } else {
            return evaluateNextMove(board, PLAYER_X);
        }
    }
    // Function to evaluate the next move for the AI player
    // this is the one responsible for choosing a better play
    private static int evaluateNextMove(char[][] board, char player) {
        char opponent = (player == PLAYER_X) ? PLAYER_O : PLAYER_X;

        // Check for immediate wins or losses
        if (canPlayerWin(board, player)) {
            return 1; // AI can win on next move
        } else if (canPlayerWin(board, opponent)) {
            return -1; // Player can win on next move
        } else {
            return 0; // No immediate wins or losses
        }
    }
    // Function to check if a player can win on the next move
    private static boolean canPlayerWin(char[][] board, char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == EMPTY) {
                return true;
            }
            if (board[row][0] == EMPTY && board[row][1] == player && board[row][2] == player) {
                return true;
            }
            if (board[row][0] == player && board[row][1] == EMPTY && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == EMPTY) {
                return true;
            }
            if (board[0][col] == EMPTY && board[1][col] == player && board[2][col] == player) {
                return true;
            }
            if (board[0][col] == player && board[1][col] == EMPTY && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == EMPTY) {
            return true;
        }
        if (board[0][0] == EMPTY && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == EMPTY) {
            return true;
        }
        if (board[0][2] == EMPTY && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // Function to check if the game has ended
    private static boolean isGameOver(char[][] board) {
        return hasPlayerWon(board, PLAYER_X) || hasPlayerWon(board, PLAYER_O) || isBoardFull(board);
    }

    // Function to check if a player has won the game
    private static boolean hasPlayerWon(char[][] board, char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // Function to check if the board is full to announce draw
    private static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to make the player's move
    // this function takes the input from the user and validates it, if the position is valid and empty then it plays, otherwise asks the user for valid position
    private static void makePlayerMove(char[][] board, char player, Scanner scanner) {
        boolean validMove = false;
        int row, col;

        do {
            System.out.print("Enter row number (0-2): ");
            row = scanner.nextInt();
            System.out.print("Enter column number (0-2): ");
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
                board[row][col] = player;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        } while (!validMove);
    }

    // Function to make the best move for the computer player
    private static void makeComputerMove(char[][] board) {
        int[] bestMove = minimax(board, 0, true);
        int row = bestMove[0];
        int col = bestMove[1];

        if (row != -1 && col != -1) {
            board[row][col] = PLAYER_O;
        }
    }

    // Minimax algorithm to determine the best move
    private static int[] minimax(char[][] board, int depth, boolean isMaximizingPlayer) {
        if (isGameOver(board)) {
            int[] result = {evaluate(board), -1, -1}; // Score, row, column
            return result;
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            int[] bestMove = {-1, -1};

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == EMPTY) {
                        board[row][col] = PLAYER_X;
                        int[] score = minimax(board, depth + 1, false);
                        board[row][col] = EMPTY;

                        if (score[0] > bestScore) {
                            bestScore = score[0];
                            bestMove[0] = row;
                            bestMove[1] = col;
                        }
                    }
                }
            }
            return bestMove;
        } else {
            int bestScore = Integer.MAX_VALUE;
            int[] bestMove = {-1, -1};

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == EMPTY) {
                        board[row][col] = PLAYER_O;
                        int[] score = minimax(board, depth + 1, true);
                        board[row][col] = EMPTY;

                        if (score[0] < bestScore) {
                            bestScore = score[0];
                            bestMove[0] = row;
                            bestMove[1] = col;
                        }
                    }
                }
            }
            return bestMove;
        }
    }


    // Function to print the current state of the board
    private static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }
}
