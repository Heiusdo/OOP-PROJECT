package utilz;

import main.Game;

public class HelpMethod {
    // CanMoveHere: This method checks if a character can move to a specific
    // location in the game world. It takes four floating point numbers (x, y,
    // width, height) and a 2D integer array (lvlData) as parameters.

    // The method checks if the location at (x, y) and the surrounding locations
    // (considering the width and height of the character) are not solid (i.e., the
    // character can move through them). If none of the locations are solid, the
    // method returns true, indicating that the character can move to the specified
    // location. Otherwise, it returns false.
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        // topleft
        if (!IsSolid(x, y, lvlData))
            // bottom right
            if (!IsSolid(x + width, y + height, lvlData))
                // top right
                if (!IsSolid(x + width, y, lvlData))
                    // bottom left
                    if (!IsSolid(x, y + height, lvlData))
                        return true;
        return false;
    }

    // IsSolid: This method checks if a specific location in the game world is solid
    // (i.e., the character cannot move through it). It takes three floating point
    // numbers (x, y, lvlData) as parameters.
    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }
}
