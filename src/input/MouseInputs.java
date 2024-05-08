package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
    // init a variable named gamePanel type GamePanel to reference to the GamePanel
    // object passed on the constructor
    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        // this is a reference to the GamePanel object passed on the constructor
        // if class-level variable is not refernced to the object passed on the
        // constructor, it will not be able to interact with stuff included inside the
        // Class of the object
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("");

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // the getX and getY methods are built-in methods of the MouseEvent class and
        // they can be used to retrieve the current x and y coordinates of the mouse
        // pointer.
        // gamePanel.setRectPosi(e.getX(), e.getY());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            gamePanel.getGame().getPlayer().setAttacking(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
