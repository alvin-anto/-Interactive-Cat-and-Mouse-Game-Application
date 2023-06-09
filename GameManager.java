import java.util.Random;

//The GameManager class handles game logic and keeps track of game objects.
public class GameManager {
    private GameBoard gameBoard;
    private Cat cat;
    private Mouse mouse;
    private Cheese cheese;
    private boolean gameOver;
    private Random random;
    private String winner;

    // Constructor for the GameManager class.
    public GameManager(int width, int height, int catX, int catY, int mouseX, int mouseY) {
        gameBoard = new GameBoard(width, height);
        cat = new Cat(catX, catY, this);
        mouse = new Mouse(mouseX, mouseY);
        random = new Random();
        int cheeseX, cheeseY;

        // Randomly place the cheese while avoiding the initial positions of the cat and mouse.
        do {
            cheeseX = random.nextInt(width);
            cheeseY = random.nextInt(height);
        } while ((cheeseX < width / 2 && cheeseY < height / 2) || (cheeseX == catX && cheeseY == catY) || (cheeseX == mouseX && cheeseY == mouseY));

        cheese = new Cheese(cheeseX, cheeseY);
        gameBoard.addObject(cat);
        gameBoard.addObject(mouse);
        gameBoard.addObject(cheese);
        gameOver = false;
        mouse.setCheese(cheese);
        winner = "";

    }
    
    // Getter method for winner.
    public String getWinner()
    {
    	return winner;
    }

    // Method to check game over in the game.
    public void playTurn() {
        checkGameOver(); // Check for game over after the cat moves
        if (!gameOver) { // Only move the mouse if the game is not over
            checkGameOver(); // Check for game over after the mouse moves
        }
    }

    // Getter method for gameBoard.
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    // Getter method for gameOver.
    public boolean isGameOver() {
        return gameOver;
    }
    
    // Method to move the mouse and check for game over.
    public void moveMouse(int dx, int dy) {
        mouse.move(gameBoard.getBoard(), dx, dy);
        checkGameOver();
        if (!gameOver) {
            cat.move(gameBoard.getBoard());
            checkGameOver();
        }
    }

    // Check if the game is over and set the winner accordingly.
    protected void checkGameOver() {
        if (cat.getX() == mouse.getX() && cat.getY() == mouse.getY()) {
            gameOver = true;
            System.out.println("Game Over! Cat caught the mouse.");
            winner = "Cat";
        } else if (mouse.getX() == cheese.getX() && mouse.getY() == cheese.getY()) {
            gameOver = true;
            System.out.println("Game Over! Mouse reached the cheese.");
            winner = "Mouse";
        }
    }
}