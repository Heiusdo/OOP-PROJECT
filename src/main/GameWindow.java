package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    // initialize a jframe variable with JFrame type, this is also an instance of
    // JFrame class
    private JFrame jframe;

    // add an object parameter, it is a parameter but can consider it as an object,-
    // - JPanel panel into the GameWindow constructor
    //
    public GameWindow(GamePanel gamePanel) {
        // init a object or an instance of JFrame class named jframe
        JFrame jframe = new JFrame();
        // after initializing the jframe object, we can use it with built-in function
        // --- included in JFrame class

        // ALL THE ACTIONS MUST BE DONE BEFORE THE setvisible FUNCTION !!!

        // this function allow us to kill the terminal whenever clicking the X button of
        // ----the frame, without this the program still running

        // now it is terminated after hitting X button
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add the game panel into the frame
        // Inside paintComponent, I have code that draws a rectangle. So, when the
        // GamePanel object is added to the JFrame and the frame is made visible, the
        // paintComponent method is indeed automatically called, and the rectangle is
        // drawn on the panel.
        jframe.add(gamePanel);
        // set the gamewindow to appear in the middle of our pc screen instead of the
        // top right
        jframe.setLocationRelativeTo(null);
        // this line of code is used to make the frame not resizable by dragging from
        // the corner, ( not making the window smaller or bigger ).
        jframe.setResizable(false);
        // this line is telling Jframe to fit the size of the window to the preferred
        // size of its components, we have only one component which is the gamePanel
        // with 1280x800, so Jframe will create a window that fits the jpanel by that
        // dimension(1280x800).
        jframe.pack();
        // make it visiable.
        jframe.setVisible(true);
    }
}
