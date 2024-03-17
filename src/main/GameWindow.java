package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    // initialize a jframe variable with JFrame type, this is also an instance of
    // JFrame class
    private JFrame jframe;

    // add an object JPanel panel into the GameWindow constructor
    // to link the window and the panel together
    public GameWindow(GamePanel gamePanel) {
        // init a object or an instance of JFrame class named jframe
        JFrame jframe = new JFrame();
        // after initializing the jframe object, we can use it with built-in function
        // --- included in JFrame class

        // ALL THE ACTIONS MUST BE DONE BEFORE THE setvisible FUNCTION !!!

        // the unit is in pixel
        // creat a frame
        jframe.setSize(400, 400);

        // this function allow us to kill the terminal whenever clicking the X button of
        // ----the frame, without this the program still running

        // now it is terminated after hitting X button
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add the game panel into the frame
        jframe.add(gamePanel);
        // set the gamewindow to appear in the middle of our pc screen instead of the
        // top right
        jframe.setLocationRelativeTo(null);

        // make it visiable
        jframe.setVisible(true);
    }
}
