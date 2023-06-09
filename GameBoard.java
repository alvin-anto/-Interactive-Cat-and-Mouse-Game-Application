// GameBoard class represents the game board and contains an array of GameObjects.
public class GameBoard {
    private GameObject[][] gameBoard;

    // GameBoard constructor takes the dimensions of the game board (width, height).
    public GameBoard(int width, int height) {
        gameBoard = new GameObject[height][width];
    }

    // addObject method adds a GameObject to the game board.
    public void addObject(GameObject obj) {
        gameBoard[obj.getY()][obj.getX()] = obj;
    }

    // removeObject method removes a GameObject from the game board.
    public void removeObject(GameObject obj) {
        gameBoard[obj.getY()][obj.getX()] = null;
    }

    // getBoard method returns the array representing the game board.
    public GameObject[][] getBoard() {
        return gameBoard;
    }
}