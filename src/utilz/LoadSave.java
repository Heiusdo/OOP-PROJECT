package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import main.Game;
import main.GamePanel;

public class LoadSave {
    public static final String PLAYER_ATLAS = "src/images/player_sprites.png";
    public static final String LEVEL_ATLAS = "src/images/outside_sprites.png";
    // public static final String LEVEL_ONE_DATA = "src/images/level_one_data.png";
    public static final String LEVEL_ONE_DATA = "src/images/level_one_data_long.png";
    public static final String MENU_BUTTONS = "src/images/button_atlas.png";
    public static final String MENU_BACKGROUND = "src/images/menu_background.png";
    public static final String PAUSED_BACKGROUND = "src/images/pause_menu.png";
    public static final String SOUND_BUTTONS = "src/images/sound_button.png";
    public static final String URM_BUTTONS = "src/images/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "src/images/volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "src/images/background_menu.png";

    public static BufferedImage GetSpritesAtlas(String filename) {
        BufferedImage image = null; // Initialize the image variable with null.
        try {
            // image = ImageIO.read(new File("src/images/player_sprites.png"));
            image = ImageIO.read(new File("" + filename));

        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public static int[][] GetLevelData() {
        BufferedImage img = GetSpritesAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;

    }

}
