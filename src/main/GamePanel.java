package main;

import javax.swing.JPanel;

import input.KeyboardInputs;// import a class from different package
import input.MouseInputs;
// import java.awt.Graphics;
// import java.awt.event.KeyListener;

import java.awt.*; // this is used for importing Graphics class ( java.awt.Graphics)
import java.awt.event.*; // this is used for importing KeyEvent class (java.awt.event.KeyEvent)
import java.util.Random;

public class GamePanel extends JPanel {
    // init the variable (instance) of MouseInputs class
    private MouseInputs mouseInputs;
    // init the value for moving the rectangle
    private float xDelta = 100;
    private float yDelta = 100;
    private float xUpdate = 0.03f;
    private float yUpdate = 0.03f;
    private Color color = new Color(24, 3, 203);
    private Random random;
    // private int xoriginal = 100, yoriginal = 100;
    private int frame;
    private long lastCheck;

    // inside the constructor is where we add our inputs to the game
    public GamePanel() {

        random = new Random();
        // the line below create an object of MouseInputs class, so that the object can
        // be passed through 2 methods relating to the MouseInputs class which are
        // addMouseListener and addMouseMotionListener
        // --

        MouseInputs mouseInputs = new MouseInputs(this);
        // create KeyboardInputs object to pass in addKeyListener() which is used for
        // tracking the keyboard
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        // place the KeyboardInputs object inside the addKeyListener method
        // --
        // Since the object implement the KeyListener, so object can use methods
        // included in KeyListener interface for the addKeylistener method
        // --
        addKeyListener(keyboardInputs);// (GamePanel gamePanel)
        // for clicking, pressing, releasing the mouse
        // --
        // We have to init the variable (considered as object) at first, else we adding
        // 2 different mouse inputs (new MouseInputs())
        // ---
        // whenever the event of the mouse object fits the addMouseListener method, the
        // methods that fit the addMouseListener() will be automatically called by the
        // Java runtime system
        addMouseListener(mouseInputs);
        // for moving(the cursor on the screen), dragging(click and move the mouse)
        addMouseMotionListener(mouseInputs);

    }

    // changing the xDelta value for changing rectangle through keyboard

    public void changeXDelta(int value) {
        this.xDelta += value;
        // the system is not told to repaint after changing value, thats why we need
        // this method
        // it is used to call the paintComponent method.
        repaint();
    }

    // changing the yDelta value for changing rectangle through keyboard
    public void changeYDelta(int value) {
        this.yDelta += value;
        repaint();
    }

    public void setRectPosi(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
    // the paintComponent method is in JPanel, we never call it, it is called when
    // the game is started

    // JPanel itself can not draw, it needs a Graphics object to do it
    // JFrame + JPanel = GameWindow
    // Graphics allow us to draw to the Panel
    // basically, when we need to draw something, we need paintComponent method from
    // JPanel class with
    // object "g" from Graphics class
    // ----------- Graphics g in this case is a object parameter
    // object parameter exists when the parameter type is a class.
    // --
    // The paintComponent method is a part of the JPanel class which gets
    // automatically called by the Swing framework under certain situations, such as
    // when the window is resized or made visible
    public void paintComponent(Graphics g) {
        // this line below is calling JComponent's paintComponent, JComponent is the
        // superclass of JPanel
        // It looks like this, " public class JPanel extends JComponent implements
        // Accessible "
        super.paintComponent(g);

        UpdateRect();
        g.setColor(color);

        // 'g' can use built-in functions of Graphics class, cuz it's a Graphics
        // instance
        // 1st = x posi, 2nd = y posi, 3rd = width, 4th = height
        // g.drawRect(xoriginal, yoriginal, 200, 50);
        // fill the retangle
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
        frame++;
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
    // test commit

    private void UpdateRect() {
        // the xDelta will be updated by xUpdate, so it will keep increasing every time
        // the paintComponent method is called.
        xDelta += xUpdate;
        // set it >400 cuz the rectangle is 400 width, since the width is 400, larger
        // than 400 will make the rectangle move out of the screen
        // the same with <0
        if (xDelta > 400 || xDelta < 0) {
            // when reach 401, xDelta = xDelta - 0.003, keep decreasing til it reach 0, when
            // reach 0, xDelta = xDelta + 0.003
            xUpdate *= -1;
            color = getRndColor();
        }

        yDelta += yUpdate;
        if (yDelta > 400 || yDelta < 0) {
            yUpdate *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        // random.nextInt(256) will return a random number from 0 to 255
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

}
