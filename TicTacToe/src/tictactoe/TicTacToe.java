/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
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

    private boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            System.out.println("Invalid move, try again.");
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) || 
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void playGame() {
        Scanner scan = new Scanner(System.in);
        int row, col;
        boolean gameInProgress = true;

        while (gameInProgress) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");

            while (true) {
                try {
                    System.out.print("Enter row (0-2): ");
                    row = Integer.parseInt(scan.nextLine());
                    System.out.print("Enter column (0-2): ");
                    col = Integer.parseInt(scan.nextLine());

                    if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                        if (makeMove(row, col)) {
                            break; // Valid move, exit loop
                        }
                    } else {
                        System.out.println("Invalid input! Row and column must be between 0 and 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter numbers between 0 and 2.");
                }
            }

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameInProgress = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameInProgress = false;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}