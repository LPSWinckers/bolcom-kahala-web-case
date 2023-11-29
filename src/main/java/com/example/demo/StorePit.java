package com.example.demo;
public class StorePit extends Pit{// pit object for the store
    
    StorePit(){
        super();
    }
    // Add a number of stones to the store
    public void AddStones(int number){
        stones += number;
    }
}
