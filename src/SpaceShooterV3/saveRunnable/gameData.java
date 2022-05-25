package SpaceShooterV3.saveRunnable;

import SpaceShooterV3.RunGraphics;
import SpaceShooterV3.gameObjects.Enemy;
import SpaceShooterV3.gameObjects.Player;
import SpaceShooterV3.gameObjects.Shot;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * It's a class that holds all the data that we want to save
 */
public class gameData implements Serializable {
    public Player player;
    public ArrayList<Enemy> enemies1 = new ArrayList<>();
    public Shot shot;
    public int level;
    public int score;

    /** It's a constructor that takes in a showGraphics object and sets the values of the gameData object to the values of
    * the showGraphics object.
     */
    public gameData(RunGraphics.showGraphics showGraphics) {
        this.player = showGraphics.player;
        this.enemies1.addAll(showGraphics.enemies1);
        this.shot = showGraphics.shot;
        this.level = showGraphics.getLevel();
        this.score = showGraphics.getScore();
    }

}
