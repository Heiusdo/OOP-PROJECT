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
    private int frame;
    private long lastCheck;

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

    //
    private void startGameloop() {
        // The this keyword in the startGameloop() method refers to the implicit Game
        // object that is created at the beginning of the program. By passing 'this' to
        // the Thread constructor, you are telling the Thread object to use the run()
        // method of the Game class as the entry point for the new thread.
        gameThread = new Thread(this);
        // This line starts the new thread. When a thread is started, the Java Virtual
        // Machine (JVM) calls the run() method of the Thread object.
        // However, since the Thread class's run() method does nothing by default, thats
        // why we passing "this" ( the Game class's object) to the Thread constructor,
        // so the run() method of the Game class is called instead.
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

            // lệnh if ở dưới là để vẽ ra các frame tương ứng với số thời gian cần thiết để
            // vẽ ra 1 frame

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
                frame++;
            }

            // điều kiện if ở dưới để in ra số frame mỗi giây

            // System.currentTimeMillis() returns the current time in milliseconds since the
            // Unix epoch (midnight, January 1, 1970 UTC)
            // means, even the lastcheck variable can hold the currenttime value, it is
            // still smaller than the currenttime value, since time continue to progress
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frame);
                frame = 0;
            }
            // repaint() call the paintComponent method rapidly in 1 second (due to the if
            // statement >=1000milisec) and that results in the large of frame (fps).
            // in a second, the paintComponent method can be called 1000 times, so the fps
            // will be 1000
            // --
            // the loop
            // repaint();
        }
    }
}
