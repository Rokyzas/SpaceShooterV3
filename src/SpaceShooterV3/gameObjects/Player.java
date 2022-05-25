package SpaceShooterV3.gameObjects;

import java.awt.*;
import java.io.Serializable;

/**
 * The Player class is a subclass of SpaceCharacter that implements the Serializable interface
 */
public class Player extends SpaceCharacter implements Serializable {

    /** This is a constructor that takes in no parameters and passes no parameters to the superclass constructor.*/
    public Player() {
        super();
    }

    /** This is a constructor that takes in the x, y, width, height, speed, and image url of the player. It then passes
    * these values to the superclass constructor.
    */
     public Player (int x, int y, int w, int h, int s, String u) {
        super(x,y,w,h,s, u);
    }

    /**
     * If the player is moving left, subtract the player's speed from the player's x position. If the player is moving
     * right, add the player's speed to the player's x position
     *
     * @param direction The direction the player is moving in.
     */
    @Override
    public void move(int direction) {
        if(moveLeft==true) {
            setX(getX()-getSpeed());
        }

        if(moveRight == true) {
            setX(getX()+getSpeed());
        }
    }

    /**
     * The draw function draws the image at the x and y coordinates with the width and height of the image
     *
     * @param window the Graphics object that you will draw on
     */
    @Override
    public void draw(Graphics window) {
        window.drawImage(getImage(),getX(),getY(),getW(),getH(),null);
    }

    /**
     * If the key pressed is the left arrow key, set the moveLeft boolean to true. If the key pressed is the right arrow
     * key, set the moveRight boolean to true
     *
     * @param d The keycode of the key that was pressed.
     */
    public void setLeftRight(int d){
        if(d==37){
            moveLeft = true;
        }

        if(d==39){
            moveRight = true;
        }
    }

    /**
     * If the player is moving left, stop moving left.
     */
    public void stop(){
        moveLeft=false;
        moveRight=false;
    }
}

