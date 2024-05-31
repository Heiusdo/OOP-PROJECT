package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;

import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

public class PauseOverLay {

    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    public PauseOverLay() {
        loadBackground();
        createSoundButtons();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpritesAtlas(LoadSave.PAUSED_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update() {
        // update logic here
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        musicButton.draw(g);
        sfxButton.draw(g);
    }

    public void mouseDragged() {
        // handle mouse dragged
    }

    public void mousePressed(MouseEvent e) {
        // handle mouse pressed
    }

    public void mouseReleased(MouseEvent e) {
        // handle mouse released
    }

    public void mouseMoved(MouseEvent e) {
        // handle mouse moved
    }
}
