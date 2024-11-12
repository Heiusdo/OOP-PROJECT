package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Crabby extends Enemy {

    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        // 22 and 19 are the width and height of the hitbox of the crabby
        inithitbox(x, y, (int) (22 * Game.SCALE), (int) (19 * Game.SCALE));
    }

}