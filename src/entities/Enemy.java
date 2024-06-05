package entities;

import static utilz.Constants.EnemyConstants.*;

public abstract class Enemy extends Entity {
    private int animationIndex, enemyState, enemyType;
    private int animationTick, aniSpeed = 25;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        inithitbox(x, y, width, height);

    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= aniSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(enemyType, enemyState)) {
                animationIndex = 0;
            }
        }
    }

    public void update() {
        updateAnimationTick();
    }

    public int getAniIndex() {
        return animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
