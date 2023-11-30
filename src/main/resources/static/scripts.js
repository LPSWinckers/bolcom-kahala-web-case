document.addEventListener('DOMContentLoaded', function() {
    const pits = document.querySelectorAll('.pit'); // Get all the pits

    pits.forEach(function(pit) { // Add a click event listener to each pit
        pit.addEventListener('click', function(event) {
            console.log('Pit clicked:', event.target.id);
            fetch('/pit-clicked/' + event.target.id, { 
                method: 'POST',})
            .then(response => {
                if (!response.ok) {
                    throw new Error('Server responded with status: ' + response.status);
                }
                return response.json();
            })
            .then(updatedBoard => {
                console.log('Updated board:', updatedBoard);
                if (updatedBoard[14] !== -2) { // Check if the move was valid
                    if (updatedBoard[14] === -1) {  // Check if the game is over
                        const winnerElemnt = document.getElementById('winner');
                        const winner = getWinner();
                        const player1Store = document.getElementById(6);
                        const player2Store = document.getElementById(13);
                        if (winner === 0) { // Check if the game is a tie
                            winnerElemnt.textContent = 'Tie game!';
                        } else if (winner === 1) { // Check if player 1 won
                            winnerElemnt.textContent = 'Player 1 wins! with ' + player1Store.textContent + ' stones';
                        } else {  // Player 2 won
                            winnerElemnt.textContent = 'Player 2 wins! with ' + player2Store.textContent + ' stones';
                        }
                        updatePits(updatedBoard); // Update the pits
                        
                    } else {
                        updatePits(updatedBoard); // Update the pits
                        if (updatedBoard[14] !== currentPlayer) { // Check if the current player gets another turn
                            togglePlayerTurn(); // Toggle the player turn
                        }
                    }
                } else {
                    console.log('Error: invalid move');
                }
            })
            .catch(error => console.error('Error:', error));
        });
    });
});

function updatePits(board) { // Update the pits
    // Iterate over the board object
    for (const [pitId, stones] of Object.entries(board)) {
        // Find the pit or store element by its ID
        const element = document.getElementById(pitId);
        // Update the content of the pit or store element
        if (element) {
            element.textContent = stones.toString();
        }
    }
}
let currentPlayer = 1; // Start with player 1

function getWinner() {
    // Get the number of stones in each player's store
    const player1Store = parseInt(document.getElementById(6).textContent);
    const player2Store = parseInt(document.getElementById(13).textContent);
    
    // Determine the winner
    if (player1Store > player2Store) {
        return 1;
    } else if (player2Store > player1Store) {
        return 2;
    } else {
        return 0;
    }
}

function togglePlayerTurn() {
    // Toggle the current player
    currentPlayer = currentPlayer === 1 ? 2 : 1;
    
    // Update the turn display
    const playerTurnElement = document.getElementById('playerTurn');
    playerTurnElement.textContent = `Player ${currentPlayer}'s turn`;
}
