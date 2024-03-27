package main;

public class Game implements Runnable {
    // init the GameWindow variable
    private GameWindow gameWindow;
    // init the GamePanel variable
    private GamePanel gamePanel;
    // init the Thread variable for running the game in a separate thread
    private Thread gameThread;
    // init the variable for the frames per second
    private final int FPS_SET = 120;

    public Game() {
        // since I already previously initialized the object or the instance of
        // GameWindow class, can do this instead of GameWindow gameWindow = new
        // GameWindow();

        // init 2 object

        // need to init the GamePanel one first, if we place it below the gamewindow,
        // there exists nothing to pass through the frame
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        // this line let we know that the things we interact with keyboard got focused
        gamePanel.requestFocus();
        startGameloop();

    }

    private void startGameloop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // this is where the gameloop stays
    @Override
    public void run() {
        // this is the variable that stores the duration each frame should last.
        // It is calculated by dividing the number of nanoseconds in one second
        // (1,000,000,000) by the desired frames per second (FPS_SET)
        // the timePerFrame is the time taken for each frame to be displayed
        double timePerFrame = 1000000000.0 / FPS_SET;
        // this is the variable that stores the time at which the last frame was
        // displayed
        // this line run before the game loop starts, so the nanotime the lastFrame
        // variable initially take as its value at first will always smaller compare to
        // the nanotime in the if statement of while loop

        long lastFrame = System.nanoTime();

        while (true) {
            // if timePerFrame is set to 120 FPS, the value of timePerFrame would be
            // 8333333.33 nanoseconds (1000000000.0 / 120.0).
            // --
            // This means that the game would wait for 8333333.33 nanoseconds (8.333
            // milliseconds) before calling the repaint() method again, and displaying the
            // next frame.
            // --
            // Therefore, repaint() method would be called 120 times in one second,
            // resulting in a frame rate of 120 FPS (frames per second).
            if (System.nanoTime() - lastFrame >= timePerFrame) {

                gamePanel.repaint();
                // update the lastFrame variable to the current time for the next loop
                lastFrame = System.nanoTime();
            }
        }
    }
}
