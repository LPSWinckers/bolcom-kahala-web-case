package com.example.demo;
public class PlayPit extends Pit {// pit object for the play pits
    PlayPit(){
        super();
        stones = 6;// set the starting number of stones to 6
    }

    public void RemoveStones(){ // set the number of stones to 0
        stones = 0;
    }
}
