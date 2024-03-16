package main;

import javax.swing.JPanel;

import input.KeyboardInputs;// import a class from different package
import input.MouseInputs;
// import java.awt.Graphics;
// import java.awt.event.KeyListener;

import java.awt.*; // this is used for importing Graphics class ( java.awt.Graphics)
import java.awt.event.*; // this is used for importing KeyEvent class (java.awt.event.KeyEvent)

public class GamePanel extends JPanel {
    // init the object(instance) of MouseInputs class
    private MouseInputs mouseInputs;
    // init the value for moving the rectangle
    private int xDelta = 0, yDelta = 0;
    private int xoriginal = 100, yoriginal = 100;

    // inside the constructor is where we add our inputs to the game
    public GamePanel() {
        // the line below create an object of MouseInputs class, so that the object can
        // be passed through 2 methods relating to the MouseInputs class which are
        // addMouseListener and addMouseMotionListener

        MouseInputs mouseInputs = new MouseInputs();
        // place the KeyboardInputs object inside the addKeyListener method
        // --
        // Since the object implement the KeyListener, so object can use methods
        // included in KeyListener interface for the addKeylistener method

        addKeyListener(new KeyboardInputs(this));
        // for clicking, pressing, releasing the mouse
        // --
        // We have to init the variable (considered as object) at first, else we adding
        // 2 different mouse inputs (new MouseInputs())
        addMouseListener(mouseInputs);
        // for moving(the cursor on the screen), dragging(click and move the mouse)
        addMouseMotionListener(mouseInputs);

    }

    // changing the xDelta value for the changes of rectangle
    public void changeXDelta(int value) {
        this.xDelta += value;
        // the system is not told to repaint after changing value, thats why we need
        // this method
        repaint();
    }

    // changing the yDelta value for the changes of rectangle
    public void changeYDelta(int value) {
        this.yDelta += value;
        repaint();
    }

    // the paintComponent method is in JPaned, we never call it, it is called when
    // the game is started

    // JPaned itself can not draw, it needs a Graphics object to do it
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
        g.drawRect(xoriginal, yoriginal, 200, 50);
        // fill the retangle
        g.fillRect(xoriginal + xDelta, yoriginal + yDelta, 200, 50);

    }
    // test commit

}
