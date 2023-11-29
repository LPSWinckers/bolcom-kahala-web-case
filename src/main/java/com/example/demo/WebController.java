package com.example.demo;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController { // This is the main controller for the Spring Boot application
    private Board board;
    @GetMapping("/") // This is the main page sets up the board and returns the index.html
    public String index() {
        board = new Board();
        return "index";
    }

    @PostMapping("/pit-clicked/{id}") // This is the endpoint for when a pit is clicked
    public ResponseEntity<Map<Integer, Integer>> handlePitClick(@PathVariable("id") int pitId) {
        try {
            Map<Integer, Integer> updatedBoard = board.Move(pitId); // Move the stones in the pit
            return ResponseEntity.ok(updatedBoard); // Return the updated board
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
