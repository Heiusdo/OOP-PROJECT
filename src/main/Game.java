package main;

public class Game {
    // init the GameWindow object
    private GameWindow gameWindow;
    // init the GamePanel object
    private GamePanel gamePanel;

    public Game() {
        // since I already previously initialized the object or the instance of
        // GameWindow class, can do this instead of GameWindow gameWindow = new
        // GameWindow();

        // init 2 object

        // need to init the GamePanel one first, if we place it below the gamewindow,
        // there exists nothing to pass through the frame
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

    }
}
