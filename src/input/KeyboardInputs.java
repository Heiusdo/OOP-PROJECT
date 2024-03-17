package input;

import java.awt.event.*;

import main.GamePanel;

// A class included filled methods, an Interface includes empty methods
// you extend a Class but implement an Interface
// you can only extend 1 Class (1 child has 1 Dad), but you can implement many Interface
public class KeyboardInputs implements KeyListener {
    // init a gamePanel variable with GamePanel type
    // the purpose is to have this variable interacted with the methods, which
    // interact with the keyboard, included inside the KeyListener interface
    private GamePanel gamePanel;

    // adding the GamePanel object as the object parameter into KeyboardInputs
    // constructor
    // To link the keyboard and the game panel together, so the object of
    // KeyboardInputs class can interact with the object of GamePanel class

    public KeyboardInputs(GamePanel gamePanel) {
        // the variable initialized above is assigned equally to the "object parameter
        // gamePanel"
        // --
        // anything happen with the variable will affect the parameter as well.
        // mean the object parameter gamePanel is changed based on the object variable
        // gamePanel
        this.gamePanel = gamePanel;

    }

    // public void setGamePanel(GamePanel gamePanel){
    // this.gamePanel = gamePanel;
    // }
    @Override
    // these methods will show out everything that I interact with the keyboard.

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-5);

                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
