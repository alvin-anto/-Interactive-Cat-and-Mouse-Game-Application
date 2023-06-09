// The Mouse class represents the mouse character in the game, which extends the GameObject class.
public class Mouse extends GameObject {
    private Cheese cheese;
    
    // Constructor for the Mouse class.
    public Mouse(int x, int y) {
        super(x, y);
    }
    
    // Setter method to set the cheese object for the mouse.
    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    // Method to move the mouse on the game board based on the given dx and dy.
    public void move(GameObject[][] gameBoard, int dx, int dy) {
        int newX = this.x + dx;
        int newY = this.y + dy;
        
        // Wrap around the game board if the mouse moves out of bounds.
        if (newX < 0) {
    		newX = 9;
    	}
    	else if (newX >= gameBoard[0].length) {
    		newX = 0;
    	}
    	if (newY < 0) {
    		newY = 9;
    	}
    	else if (newY >= gameBoard.length) {
    		newY = 0;
    	}
    	
    	// Move the mouse if the new position is empty or contains the cheese.
        if ((gameBoard[newY][newX] == null || gameBoard[newY][newX] instanceof Cheese)) {
            gameBoard[this.y][this.x] = null;
            gameBoard[newY][newX] = this;
            this.x = newX;
            this.y = newY;
        }
    }

	@Override
	public void move(GameObject[][] gameBoard) {
		//do nothing
		
	}
}

