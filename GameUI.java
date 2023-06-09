import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class represents the user interface for the Cat and Mouse game.
public class GameUI {
	
	// Declare necessary variables for the user interface components.
    private ImageIcon catIcon;
    private ImageIcon mouseIcon;
    private ImageIcon cheeseIcon;
    private GameManager gameManager;
    private JFrame frame;
    private JPanel gridPanel;
    private JButton startButton;
    private JButton restartButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private Timer timer;
    private static final int TILE_SIZE = 50;
    private static final int IMAGE_SIZE = TILE_SIZE - 10;

    // Constructor for the GameUI class.
    public GameUI() {
    	// Load the images for cat, mouse, and cheese.
        try {
            Image catImage = ImageIO.read(getClass().getResource("/cat.png"));
            Image mouseImage = ImageIO.read(getClass().getResource("/mouse.png"));
            Image cheeseImage = ImageIO.read(getClass().getResource("/cheese.png"));
            catIcon = new ImageIcon(catImage.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
            mouseIcon = new ImageIcon(mouseImage.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
            cheeseIcon = new ImageIcon(cheeseImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize the game manager and UI components.
        gameManager = new GameManager(10, 10, 0, 0, 9, 9);
        frame = new JFrame("Cat and Mouse Game");
        gridPanel = new JPanel(new GridLayout(gameManager.getGameBoard().getBoard().length, gameManager.getGameBoard().getBoard()[0].length));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gridPanel, BorderLayout.CENTER);

        // Create the button panel and add buttons to it.
        JPanel buttonPanel = new JPanel();
        
        // Initialize and set preferred size for each button.
        startButton = new JButton("Start");
        restartButton = new JButton("Reset");
        upButton = new JButton("Up");
        downButton = new JButton("Down");
        leftButton = new JButton("Left");
        rightButton = new JButton("Right");
        startButton.setPreferredSize(new Dimension(75, 30));
        restartButton.setPreferredSize(new Dimension(75, 30));
        upButton.setPreferredSize(new Dimension(45, 30));
        downButton.setPreferredSize(new Dimension(75, 30));
        leftButton.setPreferredSize(new Dimension(60, 30));
        rightButton.setPreferredSize(new Dimension(75, 30));
        
        // Add each button to the button panel.
        buttonPanel.add(startButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(TILE_SIZE * gameManager.getGameBoard().getBoard()[0].length, TILE_SIZE * gameManager.getGameBoard().getBoard().length);
        frame.setVisible(true);
        playGame();
    }

 // Main game loop, including setting up action listeners for buttons and updating the grid panel.
    private void playGame() {
    	 // Initialize the timer and set up the action listener for the game loop.
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.playTurn();
                updateGridPanel();
                
                if (gameManager.isGameOver()) {
                    timer.stop();
                    if (gameManager.getWinner().equals("Mouse")) {
                        JOptionPane.showMessageDialog(frame, "Game Over! Mouse won! Mouse reached the cheese.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Game Over! Cat won! Cat caught the mouse.");
                    }
                }

            }
        });

        // Set up the action listeners for the start, restart, and directional buttons.
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
                startButton.setEnabled(false);
                upButton.setEnabled(true);
                downButton.setEnabled(true);
                leftButton.setEnabled(true);
                rightButton.setEnabled(true);
                restartButton.setEnabled(true);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                gameManager = new GameManager(10, 10, 0, 0, 9, 9);
                updateGridPanel();
                startButton.setEnabled(true);
                upButton.setEnabled(false);
                downButton.setEnabled(false);
                leftButton.setEnabled(false);
                rightButton.setEnabled(false);
                
            }
        });
        
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.moveMouse(0, -1);
                updateGridPanel();
            }
        });
        
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.moveMouse(0, 1);
                updateGridPanel();
            }
        });
        
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.moveMouse(-1, 0);
                updateGridPanel();
            }
        });
        
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.moveMouse(1, 0);
                updateGridPanel();
            }
        });

        // Set the initial state of the buttons.
        startButton.setEnabled(true);
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
    }

    // Update the grid panel based on the current state of the game board.
    private void updateGridPanel() {
        GameObject[][] gameBoard = gameManager.getGameBoard().getBoard();
        gridPanel.removeAll();
        // Iterate through the game board and update the grid panel with the cat, mouse, and cheese positions.
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[0].length; x++) {
                JPanel cellPanel = new JPanel(new BorderLayout());
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (gameBoard[y][x] instanceof Cat) {
                    JLabel catLabel = new JLabel(catIcon);
                    cellPanel.add(catLabel, BorderLayout.CENTER);
                } else if (gameBoard[y][x] instanceof Mouse) {
                    JLabel mouseLabel = new JLabel(mouseIcon);
                    cellPanel.add(mouseLabel, BorderLayout.CENTER);
                } else if (gameBoard[y][x] instanceof Cheese) {
                    JLabel cheeseLabel = new JLabel(cheeseIcon);
                    cellPanel.add(cheeseLabel, BorderLayout.CENTER);
                }
                gridPanel.add(cellPanel);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

 // Main method to run the game.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameUI();
            }
        });
    }
}
