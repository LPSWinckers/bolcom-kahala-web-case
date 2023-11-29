package com.example.demo;
public class Pit {// pit object for store and regular pits
    
    public int stones;
    
    Pit(){// set the starting number of stones to 0
        this.stones = 0;
    }

    public void AddStone(){// adds 1 stone to the pit
        stones++;
    }

    public int GetStones(){// gets the number of stones in the pit
        return stones;
    }
}
