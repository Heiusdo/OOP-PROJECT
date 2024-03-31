package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import input.KeyboardInputs;// import a class from different package
import input.MouseInputs;
// import java.awt.Graphics;
// import java.awt.event.KeyListener;

import java.awt.*; // this is used for importing Graphics class ( java.awt.Graphics)
import java.awt.event.*; // this is used for importing KeyEvent class (java.awt.event.KeyEvent)
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    // init the variable (instance) of MouseInputs class
    private MouseInputs mouseInputs;
    // init the value for moving the rectangle
    private float xDelta = 100;
    private float yDelta = 100;

    private BufferedImage img;

    // inside the constructor is where we add our inputs to the game
    public GamePanel() {
        importImg();
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
        setPanelSize();

    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Idle Sword 01.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // set the size of the panel
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

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
        // g.drawImage(img, (int) 0, (int) 0, null);

    }

}
