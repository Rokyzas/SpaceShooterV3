package SpaceShooterV3.gameObjects;

import java.awt.*;
import java.io.Serializable;

/**
 * A Shot is a SpaceCharacter that can be serialized
 */
public class Shot extends SpaceCharacter implements Serializable {

    /** This is the default constructor for the Shot class. It is calling the default constructor for the SpaceCharacter
    * class.
    */
     public Shot() {
        super();
    }

    public boolean goUp;

    /** This is the constructor for the Shot class. It is calling the constructor for the SpaceCharacter class and then
    * setting the y coordinate to 600 and the goUp variable to false.
    */
     public Shot(int x, int y, int w, int h, int s, String u) {
        super(x,y,w,h,s, u);
        setY(600);
        goUp=false;
    }

    /**
     * If the y coordinate of the object is less than 0, then set the y coordinate to 600 and set the goUp variable to
     * false. If the goUp variable is true, then subtract the speed from the y coordinate
     *
     * @param direcation The direction the player is moving in.
     */
    @Override
    public void move(int direcation) {

        if(getY()<0) {
            goUp = false;
            setY(600);
        }

        if(goUp) {
            setY(getY()-getSpeed());
        }
    }

    /**
     * The draw function draws the image of the object at the x and y coordinates of the object
     *
     * @param window the Graphics object that you will draw on
     */
    @Override
    public void draw(Graphics window) {
        window.drawImage(getImage(),getX(),getY(),getW(),getH(), null);
    }

    /**
     * If the key pressed is the left arrow key, set the moveLeft boolean to true. If the key pressed is the right arrow
     * key, set the moveRight boolean to true
     *
     * @param d The keycode of the key that was pressed.
     */
    public void SetLeftRight(int d) {
        if(d==37) {
            moveLeft = true;
        }

        if(d==39) {
            moveRight = true;
        }
    }

    /**
     * If the player is moving left, stop moving left.
     */
    public void stop() {
        moveLeft = false;
        moveRight = false;
    }
}
