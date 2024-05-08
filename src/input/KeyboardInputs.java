package input;

import static utilz.Constants.Direction.DOWN;
import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.Direction.RIGHT;
import static utilz.Constants.Direction.UP;

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

        // gán reference để cùng chỉ vào 1 bộ nhớ
        // Đây là dạng pass by reference, nếu không tham chiếu biến gamePanel tới đối
        // tượng
        // của lớp GamePanel thì không thể thay đổi giá trị của đối tượng đó
        // nếu không tham chiếu, vẫn có thể truy cập methods, nhưng không thể thay đổi
        // thuộc tính của đối tượng.
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
                gamePanel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
        }

    }

    /* */
    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
