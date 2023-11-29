package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// board class
public class Board {
    private List<Pit> board;
    // true equels player 1 false equels player 2
    private boolean currentPlayer;

    Board(){
        this.board = new ArrayList<>();
        this.currentPlayer = true;

        // create board where pos 5 and 11 are storepits
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new StorePit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new PlayPit());
        board.add(new StorePit());
    }
    // getters for board and current player
    public List<Pit> GetBoard(){
        return board;
    }

    public boolean GetCurrentPlayer(){
        return currentPlayer;
    }

    // make a valid move (checking if position is valid is done in kahala class)
    public Map<Integer, Integer> Move(int position){
        Pit pit = board.get(position);
        int stones = pit.GetStones();
        if (isValidInput(position)){
                if (pit instanceof PlayPit) {
                // Cast to PlayPit and call RemoveStones
                ((PlayPit) pit).RemoveStones();
                }
                // pushes the stones forward
                push(position + 1, stones);
                if (!gameEnded()) {// returns board state if game has not ended
                    Map<Integer, Integer> boardState = getBoardState();
                    if (currentPlayer){ // adds 1 to the end of the board state if player 1 is current player
                        boardState.put(14, 1);
                    } else { // adds 2 to the end of the board state if player 2 is current player
                        boardState.put(14, 2);
                    }
                    return boardState;
                } else {
                    Map<Integer, Integer> endState = new HashMap<Integer, Integer>();
                    endState = getBoardState();
                    endState.put(14, -1);
                    return endState; // retuns -1 if game has ended
                }
                
        } else {
            Map<Integer, Integer> invalid = new HashMap<Integer, Integer>();
            invalid = getBoardState();
            invalid.put(14, -2);
            return invalid; // returns null if input is invalid
        }
    }

    private void push(int position, int stones){
        Pit currentPit = board.get(position);
        // if there are more than 1 stone remove a stone and push forward
        if (stones > 1){
            if (position < board.size() - 1){
                currentPit.AddStone();
                stones--;
                push(position + 1, stones);
            } else { // makes indax 0 if position above board size
                currentPit.AddStone();
                stones--;
                push(0, stones);
            }
            
        } else { // checks where the stone has ended for player 1
            if (currentPlayer){
                // get the store pit of player 1
                Pit storePit = board.get(6);
                switch (position) {
                    case 0, 1, 2, 3, 4, 5: // checks if stone ended on its own side
                        if (currentPit.GetStones() == 0){ // checks if position of stone is empty 
                            Pit oppositePit = board.get(12 - position); // remove opposite stones and ads them to player store
                            ((StorePit) storePit).AddStones(oppositePit.GetStones() + 1);
                            ((PlayPit)oppositePit).RemoveStones();
                            currentPlayer = !currentPlayer; // swap current player
                        } else { // add last stone and swap current player 
                            currentPit.AddStone();
                            currentPlayer = !currentPlayer;
                        }
                        break;
                    case 6: // if stone ended on store add stone but dont chenge current player
                        currentPit.AddStone();
                        break;
                    
                    default: // add stone and swap current player
                        currentPit.AddStone();
                        currentPlayer = !currentPlayer;
                        break;
                }
            } else { // checks where the stone has ended for player 2
                // get the store pit of player 2
                Pit storePit = board.get(13);
                switch (position) {
                    case 7, 8, 9, 10, 11, 12: // checks if stone ended on its own side
                        if (currentPit.GetStones() == 0){ // checks if position of stone is empty 
                            Pit oppositePit = board.get(12 - position); // remove opposite stones and ads them to player store
                            ((StorePit) storePit).AddStones(oppositePit.GetStones() + 1);
                            ((PlayPit)oppositePit).RemoveStones();
                            currentPlayer = !currentPlayer; // swap current player
                        } else { // add last stone and swap current player 
                            currentPit.AddStone();
                            currentPlayer = !currentPlayer;
                        }
                        break;
                    case 13: // if stone ended on store add stone but dont chenge current player
                        currentPit.AddStone();
                        break;
                    
                    default: // if stone ended on store add stone but dont chenge current player
                        currentPit.AddStone();
                        currentPlayer = !currentPlayer;
                        break;
                }
            }
        }
    }

    public Map<Integer, Integer> getBoardState(){
        Map<Integer, Integer> boardState = new HashMap<>();
        for (int i = 0; i < board.size(); i++){
            boardState.put(i, board.get(i).GetStones());
        }
        return boardState;
    }
    private boolean gameEnded(){
        // makes a list of all the stones on board
        List<Integer> stoneList = new ArrayList<>();
        for (Pit pit: board){
            stoneList.add(pit.GetStones());
        }
        // checks if one of the boards is empty
        if (stoneList.get(0) == 0 && stoneList.get(1) == 0 && stoneList.get(2) == 0 && stoneList.get(3) == 0 && stoneList.get(4) == 0 && stoneList.get(5) == 0 ||
         stoneList.get(7) == 0 && stoneList.get(8) == 0 && stoneList.get(9) == 0 && stoneList.get(10)== 0 && stoneList.get(11) == 0 && stoneList.get(12) == 0){
            
            // add remaining stones to store
            Pit player1Pit = board.get(6);
            Pit player2Pit = board.get(13);
            ((StorePit)player1Pit).AddStones(stoneList.get(0)+stoneList.get(1)+stoneList.get(2)+stoneList.get(3)+stoneList.get(4)+stoneList.get(5));
            ((StorePit)player2Pit).AddStones(stoneList.get(7)+stoneList.get(8)+stoneList.get(9)+stoneList.get(10)+stoneList.get(11)+stoneList.get(12));

            // clear remainig board
            for (Pit pit: board){
                if (pit instanceof PlayPit){
                    ((PlayPit)pit).RemoveStones();
                }
            }
            // returns true if game ended
            return true;
        }
        
        return false;
    }

        public boolean isValidInput(int position) {
        // check if position is valid
        if (position < 0 || position > 13) {
            return false;
        }
        // check if position is a store pit
        if (position == 6 || position == 13) {
            return false;
        }
        // check if position is empty
        if (GetBoard().get(position).GetStones() == 0) {
            return false;
        }
        // check if position is on the right side
        if (currentPlayer && position > 5) {
            return false;
        }
        // check if position is on the left side
        if (!currentPlayer && position < 6) {
            return false;
        }
        return true;
    }


}
