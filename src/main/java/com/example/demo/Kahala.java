// package com.example.demo;
// import java.util.Scanner;
// import java.util.List;

// // Intitializer kahala
// class Kahala {
//     Board board = new Board();
//     boolean gameEnded = false;
//     boolean currentPlayer = true;

//     public void startGame() {
//         while (!gameEnded) {
//             // print board
//             printBoard();
//             // get input
//             int position = getInput();
//             // make move
//             gameEnded = board.Move(position);
//             // change player
//             currentPlayer = board.GetCurrentPlayer();
//         }
//         // print board
//         printBoard();
//         // print winner
//         printWinner();
//     }

//     public int getInput() {
//         try (Scanner scanner = new Scanner(System.in)) {
//             if (currentPlayer) {
//                 System.out.print("Player 1 - enter position between 1 and 6:");
//             } else {
//                 System.out.print("Player 2 - enter position between 8 and 13:");
//             }
//             int position = scanner.nextInt() - 1;
//             // check if input is valid
//             while (!isValidInput(position)) {
//                 System.out.println("Invalid input, try again: ");
//                 position = scanner.nextInt() - 1;
//             }
//             return position;
//         }
        
//     }

//     public boolean isValidInput(int position) {
//         // check if position is valid
//         if (position < 0 || position > 13) {
//             return false;
//         }
//         // check if position is a store pit
//         if (position == 6 || position == 13) {
//             return false;
//         }
//         // check if position is empty
//         if (board.GetBoard().get(position).GetStones() == 0) {
//             return false;
//         }
//         // check if position is on the right side
//         if (currentPlayer && position > 5) {
//             return false;
//         }
//         // check if position is on the left side
//         if (!currentPlayer && position < 6) {
//             return false;
//         }
//         return true;
//     }

//     public void printBoard() {
//         // get board
//         List<Pit> board = this.board.GetBoard();
//         // print board
//         System.out.println("Player 2");
//         System.out.println("  " + board.get(12).GetStones() + "  " + board.get(11).GetStones() + "  " + board.get(10).GetStones() + "  " + board.get(9).GetStones() + "  " + board.get(8).GetStones() + "  " + board.get(7).GetStones());
//         System.out.println(board.get(13).GetStones() + "                   " + board.get(6).GetStones());
//         System.out.println("  " + board.get(0).GetStones() + "  " + board.get(1).GetStones() + "  " + board.get(2).GetStones() + "  " + board.get(3).GetStones() + "  " + board.get(4).GetStones() + "  " + board.get(5).GetStones());
//         System.out.println("Player 1");
//     }

//     public void printWinner() {
//         // get board
//         List<Pit> board = this.board.GetBoard();
//         // get store pits
//         Pit storePit1 = board.get(6);
//         Pit storePit2 = board.get(13);
//         // print winner
//         if (storePit1.GetStones() > storePit2.GetStones()) {
//             System.out.printf("Player 1 wins! with %d stones.\n player 1 had %s stones and player 2 had %s stones\n", storePit1.GetStones() - storePit2.GetStones(), storePit1.GetStones(), storePit2.GetStones());
//         } else if (storePit1.GetStones() < storePit2.GetStones()) {
//             System.out.printf("Player 2 wins! with %d stones.\n player 1 had %s stones and player 2 had %s stones\n", storePit2.GetStones() - storePit1.GetStones(), storePit1.GetStones(), storePit2.GetStones());
//         } else {
//             System.out.println("It's a tie!");
//         }
//     }
// }