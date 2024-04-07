package main;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import input.KeyboardInputs;// import a class from different package
import input.MouseInputs;
// import java.awt.Graphics;
// import java.awt.event.KeyListener;

import java.awt.*; // this is used for importing Graphics class ( java.awt.Graphics)
// this is used for importing KeyEvent class (java.awt.event.KeyEvent)
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Direction.*;

public class GamePanel extends JPanel {

    // init the value for moving the rectangle
    private float xDelta = 100;
    private float yDelta = 100;

    private BufferedImage image;
    private BufferedImage[][] animation;
    // 15 is just an arbitrary value chosen for the purpose of this specific
    // animation. The animationSpeed variable determines how many ticks (or frames)
    // it takes for the animation to complete one cycle. A higher value for
    // animationSpeed will make the animation slower, while a lower value will make
    // it faster.
    // --
    // it means each image will take 15 ticks (or frames, in milliseconds unit) to
    // be shown on the screen,
    private int animationTick, animationIndex, animationSpeed = 30;
    private int playerAction = idle;
    // set = -1 so that the player will not move at first
    private int playerDirection = -1;
    private boolean moving;

    // inside the constructor is where we add our inputs to the game
    public GamePanel() {
        importImg();
        loadAnimation();
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

    private void loadAnimation() {
        // the line below is used to create an array of BufferedImage with the size of
        // 9x6 (9 rows and 6 columns). Each element in this array represent a frame of
        // the animation.
        animation = new BufferedImage[9][6];
        for (int i = 0; i < animation.length; i++) {
            for (int j = 0; j < animation[i].length; j++)
                // the image.getSubimage() is used to cut the image into pieces
                // The extracted subimage is then assigned to the i and jth position of the
                // animation array.
                // subimage(x, y, width, height)
                animation[i][j] = image.getSubimage(j * 64, i * 40, 64, 40);
        }

    }

    private void importImg() {

        try {
            image = ImageIO.read(new File("src/images/player_sprites.png"));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // set the size of the panel
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    // public void setRectPosi(int x, int y) {
    // this.xDelta = x;
    // this.yDelta = y;
    // repaint();
    // }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    // This method is responsible for updating the animation tick and the animation
    // index. The animation tick is a counter that increments every time this method
    // is called. When it reaches or exceeds the animation speed (which is 30, in
    // this case), it resets to 0 and increments the animation index. The animation
    // index is used to determine which frame of the animation to display. When it
    // reaches or exceeds the length of the animation array, it resets to 0.
    // --
    // it will keep drawing the same image of til the animationtick reach 30, then
    // animationIndex is incremented to 1
    // After this, animationTick is reset to 0, and the process repeats.
    // animationIndex continues to increment every 30 iterations of
    // updateAnimationTick(), allowing for smooth animation in the game.
    // --
    // at first animationtick and index are 0.
    // this method get invoked rapidly since the paintComponent get invoked
    // rapidly as well due to the repaint() method from the gameloop (fps, run()
    // method from Game class)

    // the comments above is for the old version of the game with 1D array,
    // the new version of the game is using 2D array.
    // the reason why we have to set animationIndex >= GetSpriteAmount(playerAction)
    // but not 6 ( the max column value of the array ), is because the
    // GetSpriteAmount method is used to get the amount of sprite in each action, so
    // it is more dynamic and flexible, and some action does not have enough 6
    // sprites.
    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction))
                animationIndex = 0;
        }
    }

    // At the moment the setAnimation() method is called, the value of the moving
    // variable is true (or any other non-zero value). This is because the moving
    // variable is set to true in the keyPressed() method when the VK_RIGHT or
    // VK_LEFT keys are pressed.
    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = idle;
        }
    }

    private void updatePosition() {
        if (moving)

            switch (playerDirection) {
                case RIGHT:
                    xDelta += 5;
                    break;
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();

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

        // the line below is used to draw image on the screen, (0,0) stands for the
        // position of image
        // g.drawImage(image, (int) 0, (int) 0, null);
        // the line below is used to draw image on the screen, (xDelta, yDelta) stands
        // for the position of image, and (100,100) stands for the size of the image
        // g.drawImage(image, (int) xDelta, (int) yDelta, 500, 500, null);
        g.drawImage(animation[playerAction][animationIndex], (int) xDelta, (int) yDelta, 258, 160, null);

    }

}
