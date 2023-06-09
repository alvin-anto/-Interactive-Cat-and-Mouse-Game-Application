// GameObject is an abstract class representing a game object (e.g. Cat or Mouse) on the game board.
public abstract class GameObject {
    // The x and y variables store the current position of the game object on the game board.
    protected int x;
    protected int y;

    // GameObject constructor takes the initial position (x, y) of the game object.
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // The abstract move method defines how the game object moves on the game board.
    // It must be implemented by each subclass (e.g. Cat, Mouse).
    public abstract void move(GameObject[][] gameBoard);

    // getX method returns the x-coordinate of the game object.
    public int getX() {
        return x;
    }

    // getY method returns the y-coordinate of the game object.
    public int getY() {
        return y;
    }

    // setX method sets the x-coordinate of the game object to the given value.
    public void setX(int x) {
        this.x = x;
    }

    // setY method sets the y-coordinate of the game object to the given value.
    public void setY(int y) {
        this.y = y;
    }
}