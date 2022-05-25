package SpaceShooterV3.gameObjects;

import java.awt.*;
import java.io.Serializable;

/**
 * Enemy is a SpaceCharacter that can be serialized.
 */
public class Enemy extends SpaceCharacter implements Serializable {

    public boolean isVisible;


    /** This is a constructor that takes in no parameters and passed in no parameters to super class.
    *
     */
     public Enemy() {
        super();
    }

    /** This is a constructor that takes in the x, y, width, height, speed, and image url. It then sets the isVisible to
    * true and moveRight to true.
    */
     public Enemy (int x, int y, int w, int h, int s, String u) {
        super(x,y,w,h,s,u);
        isVisible = true;
        moveRight = true;
    }

    /**
     * If the player is moving left, then move the player left. If the player is moving right, then move the player right
     *
     * @param direcation The direction the player is moving in.
     */
    @Override
    public void move(int direcation) {
        if(moveLeft==true) {
            setX(getX()-getSpeed());
        }

        if(moveRight == true) {
            setX(getX()-getSpeed());
        }
    }

    /**
     * The draw function draws the image at the x and y coordinates with the width and height of the image
     *
     * @param window the Graphics object that you will draw on
     */
    @Override
    public void draw(Graphics window) {
        window.drawImage(getImage(),getX(),getY(),getW(),getH(), null);
    }
}
