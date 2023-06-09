// Cat class represents the cat in the game and extends GameObject.
public class Cat extends GameObject {
    private GameManager gameManager;

    // Cat constructor takes its initial position (x, y) and a reference to the game
    // manager.
    public Cat(int x, int y, GameManager gameManager) {
        super(x, y);
        this.gameManager = gameManager;
    }

    // The move method defines how the cat moves on the game board.
    @Override
    public void move(GameObject[][] gameBoard) {
        int dx = gameBoard[0].length;
        int dy = gameBoard.length;

        // Find the position of the mouse on the game board.
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[0].length; x++) {
                if (gameBoard[y][x] instanceof Mouse) {
                    dx = x - this.x;
                    dy = y - this.y;
                    break;
                }
            }
        }

        // Check if the cat is one cell away from the mouse.
        boolean oneCellAway = Math.abs(dx) <= 1 && Math.abs(dy) <= 1;

        // Calculate the new position for the cat.
        int newX = this.x + Integer.signum(dx);
        int newY = this.y + Integer.signum(dy);

        // Check if the new position is within the game board boundaries.
        if (newX >= 0 && newX < gameBoard[0].length && newY >= 0 && newY < gameBoard.length) {
            // If the new position contains a mouse, move the cat to that position and check
            // for game over.
            if (gameBoard[newY][newX] instanceof Mouse) {
                gameBoard[this.y][this.x] = null;
                gameBoard[newY][newX] = this;
                this.x = newX;
                this.y = newY;
                gameManager.checkGameOver();
                // If the cat is one cell away from the mouse and the new position is empty,
                // move the cat diagonally.
            } else if (gameBoard[newY][newX] == null && oneCellAway) {
                gameBoard[this.y][this.x] = null;
                gameBoard[newY][newX] = this;
                this.x = newX;
                this.y = newY;
                // If the horizontal distance is greater than the vertical distance, move the
                // cat horizontally.
            } else if (Math.abs(dx) > Math.abs(dy)) {
                newX = this.x + Integer.signum(dx);
                newY = this.y;
                if (newX >= 0 && newX < gameBoard[0].length && gameBoard[this.y][newX] == null) {
                    gameBoard[this.y][this.x] = null;
                    gameBoard[this.y][newX] = this;
                    this.x = newX;
                }
                
                // If the horizontal distance is not greater than the vertical distance, move
                // the cat vertically.
            } else {
                newX = this.x;
                newY = this.y + Integer.signum(dy);
                if (newY >= 0 && newY < gameBoard.length && gameBoard[newY][this.x] == null) {
                    gameBoard[this.y][this.x] = null;
                    gameBoard[newY][this.x] = this;
                    this.y = newY;
                }
            }
        }
    }
}