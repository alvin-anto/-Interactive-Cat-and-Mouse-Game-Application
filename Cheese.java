// The Cheese class represents the cheese object in the game, which extends the GameObject class.
public class Cheese extends GameObject {
	
	// Constructor for the Cheese class.
    public Cheese(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(GameObject[][] gameBoard) {
        // Cheese does not move.
    }
}
