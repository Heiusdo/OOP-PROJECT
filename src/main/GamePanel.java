package main;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    public GamePanel() {

    }
    // the paintComponent method is in JPaned, we never call it, it is called when
    // the game is started

    // JPaned itself can not draw, it needs a graphic object to do it
    // JFrame + JPanel = GameWindow
    // Graphics allow us to draw to the Panel
    // basically, when we need to draw something, we need paintComponent method from
    // JPanel class with
    // object "g" from Graphics class
    public void paintComponent(Graphics g) {
        // this line below is calling JComponent's paintComponent, JComponent is the
        // superclass of JPanel
        // It looks like this, " public class JPanel extends JComponent implements
        // Accessible "
        super.paintComponent(g);

        // 'g' can use built-in functions of Graphics class, cuz it's a Graphics
        // instance
        // 1st = x posi, 2nd = y posi, 3rd = width, 4th = height
        g.drawRect(100, 100, 200, 50);
        // fill the retangle
        g.fillRect(100, 100, 200, 50);

    }
}
