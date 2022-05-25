package SpaceShooterV3.gameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;

/**
 * > This class is an abstract class that implements the Serializable interface
 */
public abstract class SpaceCharacter implements Serializable {
    int x;
    int y;
    int w;
    int h;
    private int speed;
    private transient Image image;
    public boolean moveLeft = false;
    public boolean moveRight = false;

    /* This is a constructor that takes in the x, y, w, h, s, and u parameters and sets the values of the variables to the
    * values of the parameters.
    */
    public SpaceCharacter() {
        x = 0;
        y = 0;
    }

    /** This is a constructor that takes in the x, y, w, h, s, and u parameters and sets the values of the variables to the
    * values of the parameters.
    */
     public SpaceCharacter(int x, int y, int w, int h, int s, String u) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        speed = s;
        try {
            image = ImageIO.read(getClass().getResource(u));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Move() moves the player in the specified direction.
     *
     * @param direction 0 = up, 1 = right, 2 = down, 3 = left
     */
    public abstract void move(int direction);

    /**
     * The draw function is an abstract function that takes a Graphics object as a parameter and returns nothing
     *
     * @param window the Graphics object that you will draw on
     */
    public abstract void draw(Graphics window);

    /**
     * This function sets the position of the object to the given x and y coordinates.
     *
     * @param x The x position of the object
     * @param y The y coordinate of the top left corner of the rectangle.
     */
    public void setPos(int x, int y) {
        x = getX();
        y = getY();
    }

    /**
     * This function returns the value of the x variable.
     *
     * @return The value of the x variable.
     */
    public int getX() {
        return x;
    }

    /**
     * This function sets the value of the x variable to the value of the x parameter.
     *
     * @param x The x coordinate of the top left corner of the rectangle.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This function returns the value of the y variable.
     *
     * @return The y value of the point.
     */
    public int getY() {
        return y;
    }

    /**
     * This function sets the y value of the object to the value of the parameter y.
     *
     * @param y The y coordinate of the point.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This function returns the value of the variable w.
     *
     * @return The width of the rectangle.
     */
    public int getW() {
        return w;
    }

    /**
     * This function sets the value of the variable w to the value of the parameter w.
     *
     * @param w The width of the image.
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * This function returns the value of the variable h.
     *
     * @return The height of the rectangle.
     */
    public int getH() {
        return h;
    }

    /**
     * This function sets the value of the variable h to the value of the parameter h.
     *
     * @param h The height of the image.
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * This function returns the speed of the car.
     *
     * @return The speed of the vehicle.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * This function sets the speed of the car to the value of the speed parameter.
     *
     * @param speed The speed of the car.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * This function returns the image.
     *
     * @return The image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * This function sets the image of the object to the image passed in as a parameter.
     *
     * @param image The image to be displayed.
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
