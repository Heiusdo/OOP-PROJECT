package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethod.CanMoveHere;
import static utilz.HelpMethod.GetEntityXPosNextToWall;
import static utilz.HelpMethod.GetEntityYPosUnderRoofOrAboveFloor;
import static utilz.HelpMethod.IsEntityOnFloor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
    private BufferedImage[][] animation;
    private int animationTick, animationIndex, animationSpeed = 25;
    private int playerAction = idle;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    // X Y DRAWOFFSET is used for minimize the hitbox orbit around the character
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    // for jumping/ gravity
    private float airSpeed = 0f;
    // gravity is a constant, the lower the gravity value is, the higher the
    // character can jump
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    private boolean moving = false, attacking = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        inithitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));

    }

    public void update() {
        updatePosition();

        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animation[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset),
                (int) (hitbox.y - yDrawOffset),
                width, height, null);
        drawhitbox(g);
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
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    // At the moment the setAnimation() method is called, the value of the moving
    // variable is true (or any other non-zero value). This is because the moving
    // variable is set to true in the keyPressed() method when the VK_RIGHT or
    // VK_LEFT keys are pressed.
    private void setAnimation() {
        int startAnimation = playerAction;
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = idle;
        }
        if (attacking) {
            playerAction = ATTACK_1;
        }
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    // the reason why this method exist is due to the animationtick and
    // animationindex, for example, the default animation is idle, when it is in
    // index 3, and I click mouse for attacking, it will continue at index 3 and
    // start the attack animation, but the attack animation ends at index 3, so
    // that's why sometimes clicking the mouse cant attack, and this method fix that
    // issue by reseting animationtick and animationindex
    private void resetAnimationTick() {
        animationIndex = 0;
        animationTick = 0;
    }

    private void updatePosition() {

        moving = false;
        if (jump)
            jump();
        if (!left && !right && !inAir)
            return;

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;
        if (!inAir)
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;

        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXposition(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXposition(xSpeed);
            }
        } else {
            updateXposition(xSpeed);
            moving = true;
        }
        // if (CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
        // this.x += xSpeed;
        // this.y += ySpeed;
        // moving = true;
        // }
        // if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width,
        // hitbox.height, lvlData)) {
        // hitbox.x += xSpeed;
        // hitbox.y += ySpeed;
        // moving = true;
        // }

    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;

    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;

    }

    private void updateXposition(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    private void loadAnimation() {
        BufferedImage image = LoadSave.GetSpritesAtlas(LoadSave.PLAYER_ATLAS);
        // the line below is used to create an array of BufferedImage with the size of
        // 9x6 (9 rows and 6 columns). Each element in this array represent a frame of
        // the animation.
        animation = new BufferedImage[9][6];
        for (int i = 0; i < animation.length; i++) {
            for (int j = 0; j < animation[i].length; j++) {
                // the image.getSubimage() is used to cut the image into pieces
                // The extracted subimage is then assigned to the i and jth position of the
                // animation array.
                // subimage(x, y, width, height)
                animation[i][j] = image.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
