package SpaceShooterV3;

import SpaceShooterV3.gameObjects.Enemy;
import SpaceShooterV3.gameObjects.Player;
import SpaceShooterV3.gameObjects.Shot;
import SpaceShooterV3.saveRunnable.LoadRunnable;
import SpaceShooterV3.saveRunnable.SaveRunnable;
import SpaceShooterV3.saveRunnable.gameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is a serializable class that implements the Runnable interface
 */
public class RunGraphics implements Serializable{

    private JFrame frame;
    static showGraphics showGraphics;

    int fW = 600;
    int fH = 600;


    /** The above code is creating a new JFrame with the title "SpaceShooterV3". It is setting the size of the frame to the
    * fW and fH variables. It is setting the default close operation to exit the program when the frame is closed. It is
    * setting the preferred size of the frame to the size of the frame. It is adding the showGraphics class to the frame.
     * It is packing the frame. It is making the frame visible.
     */
    public RunGraphics() {
        frame = new JFrame("SpaceShooterV3");
        frame.setSize(fW, fH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        showGraphics = new showGraphics(frame.getSize());
        frame.add(showGraphics);
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * The main function is the entry point of the program. It is called when the program is run
     */
    public static void main(String... argv) {
        new RunGraphics();
    }


    /**
     * This class is a JPanel that implements Serializable, Runnable, MouseListener, KeyListener, and ActionListener
     */
    public static class showGraphics extends JPanel implements Serializable, Runnable, MouseListener, KeyListener, ActionListener {

        private Thread animator;

        int xAxis = 30;
        int yAxis = 30;

        int level = 1;
        int score = 0;

        int columns;
        int rows;

        boolean gameOn;
        Boolean load = false;
        public Player player;
        public ArrayList<Enemy> enemies1 = new ArrayList<>();
        public Shot shot;
        SpaceShooterV3.saveRunnable.gameData gameData;

        JButton saveButton;
        JButton loadButton;
        JButton levelUpButton;
        JButton levelDownButton;

        /**
         * Creates panel, adds buttons
         * @param dimension
         */
        public showGraphics(Dimension dimension) {
            reset();

            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            addKeyListener(this);
            setFocusable(true);
            if (animator == null) {
                animator = new Thread(this);
                animator.start();
            }

            setDoubleBuffered(true);

            saveButton = new JButton();
            saveButton.setBounds(100, 300, 200, 100);
            saveButton.addActionListener(this);
            saveButton.setText("SAVE");
            saveButton.setFocusable(false);

            loadButton = new JButton();
            loadButton.setBounds(400, 300, 200, 100);
            loadButton.addActionListener(this);
            loadButton.setText("LOAD");
            loadButton.setFocusable(false);

            levelUpButton = new JButton();
            levelUpButton.setBounds(400, 300, 200, 100);
            levelUpButton.addActionListener(this);
            levelUpButton.setText("+");
            levelUpButton.setFocusable(false);

            levelDownButton = new JButton();
            levelDownButton.setBounds(400, 300, 200, 100);
            levelDownButton.addActionListener(this);
            levelDownButton.setText("-");
            levelDownButton.setFocusable(false);

            this.add(saveButton);
            this.add(loadButton);
            this.add(levelDownButton);
            this.add(levelUpButton);
        }

        /**
         * The function resets the game by clearing the enemies array, setting the number of rows and columns, setting the
         * player and shot speeds, creating a new player and shot, and creating a new array of enemies
         */
        public void reset() {

            enemies1.clear();

            int playerSpeed = 15;
            int speed = 5;
            int enemySpeed = 5;
            int x = 10;
            int y = 35;

            if (level < 5) {
                rows = level;
                columns = level+1;
                enemySpeed = level;
            }
            else if (level < 10) {
                rows = level-4;
                columns = level+3;
                enemySpeed = level - 3;
                playerSpeed += 11 + level;
            }
            else {
                rows = 5;
                columns = 15;
                enemySpeed = level-7;
                playerSpeed += 11 + level;
                speed = level -2;
            }


            player = new Player(200, 500, 57, 35, speed, "player.png");
            shot = new Shot(200, 500, 5, 20, playerSpeed, "shot.png");


            gameOn = false;
            for (int r = 1; r <= rows; r++) {
                for (int c = 1; c <= columns; c++) {
                    enemies1.add(new Enemy(x, y, 30, 20, enemySpeed, "enemy.png"));
                    x += 35;
                }
                x = 10;
                y += 25;
            }
        }

        /**
         * The function loads the game data from the file and sets the game variables to the values in the file
         */
        public void load() {

            enemies1.clear();

            gameOn = false;

            level = gameData.level;
            score = gameData.score;

            int playerSpeed = 15;
            int enemySpeed = 5;
            int speed = 5;


            if (level < 5) {
                rows = level;
                columns = level+1;
                enemySpeed = level;
            }
            else if (level < 10) {
                rows = level-4;
                columns = level+3;
                enemySpeed = level - 3;
                playerSpeed += 11 + level;
            }
            else {
                rows = 5;
                columns = 15;
                enemySpeed = level-7;
                playerSpeed += 11 + level;
                speed = level -2;
            }


            player = new Player(gameData.player.getX(), gameData.player.getY(), gameData.player.getW(), gameData.player.getH(), speed, "player.png");
            shot = new Shot(gameData.shot.getX(), gameData.shot.getY(), 5, 20, playerSpeed, "shot.png");
            shot.goUp = gameData.shot.goUp;
            int x = 10;
            int y = 35;


            for (int rc = 0; rc < rows*columns; rc++) {
                enemies1.add(new Enemy(gameData.enemies1.get(rc).getX(), gameData.enemies1.get(rc).getY(), 30, 20, enemySpeed, "enemy.png"));
                enemies1.get(rc).isVisible = gameData.enemies1.get(rc).isVisible;
                enemies1.get(rc).moveRight = gameData.enemies1.get(rc).moveRight;
                enemies1.get(rc).moveLeft = gameData.enemies1.get(rc).moveLeft;
            }
        }

        /**
         * The function paints the background, the player, the shot, and the enemies. It also moves the player and the shot
         *
         * @param g Graphics object
         */
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            Dimension d = getSize();

            g2.setColor(Color.white);
            g2.fillRect(0, 0, d.width, d.height);

            super.paintComponent(g);
            g.setFont(g.getFont().deriveFont(30F));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(score),10, 30);
            g.drawString(String.valueOf("Level "+level),460, 30);


            if (gameOn == true) {
                moveEnemy();
                player.move(0);
                shot.move(0);
            }
            else {
                g.setFont(g.getFont().deriveFont(20F));
                g.drawString("Press SPACE to start", 200, 300);
            }

            player.draw(g2);
            shot.draw(g2);
            hitDetect();

            boolean isLevelPassed = true;
            for (int rc = 0; rc < rows*columns; rc++) {
                if (enemies1.get(rc).isVisible) {
                    enemies1.get(rc).draw(g2);
                    isLevelPassed = false;
                }
            }
            if (isLevelPassed) {
                level += 1;
                reset();
            }
        }

        /**
         * If the shot is within the bounds of the enemy, then the enemy is destroyed and the shot is reset
         */
        public void hitDetect() {
            for (int rc = 0; rc < rows*columns; rc++) {
                if (enemies1.get(rc).isVisible == true && shot.getX() + shot.getW() >= enemies1.get(rc).getX() &&
                        shot.getX() <= enemies1.get(rc).getX() + enemies1.get(rc).getW() &&
                        shot.getY() + shot.getH() >= (enemies1.get(rc).getY()) &&
                        shot.getY() <= enemies1.get(rc).getY() + enemies1.get(rc).getH()) {

                    enemies1.get(rc).isVisible = false;
                    shot.setY(-30);
                    score += 10;
                }
            }
        }

        /**
         * If the enemy is moving left, move it left. If the enemy is moving right, move it right. If the enemy is at the
         * right edge of the screen, move all enemies down and left. If the enemy is at the left edge of the screen, move
         * all enemies down and right
         */
        public void moveEnemy() {
            for (int rc = 0; rc < rows*columns; rc++) {
                if (enemies1.get(rc).moveLeft) {
                    enemies1.get(rc).setX(enemies1.get(rc).getX() - enemies1.get(rc).getSpeed());
                }
                if (enemies1.get(rc).moveRight) {
                    enemies1.get(rc).setX(enemies1.get(rc).getX() + enemies1.get(rc).getSpeed());
                }
            }

            for (int rc = 0; rc < rows*columns; rc++) {
                if (enemies1.get(rc).getX() > 550 && enemies1.get(rc).isVisible) {
                    moveLeftRight(1);
                    break;
                }

                if (enemies1.get(rc).getX() < 0 && enemies1.get(rc).isVisible) {
                    moveLeftRight(2);
                    break;
                }
            }
        }

        /**
         * If the direction is 1, move left, if the direction is 2, move right
         *
         * @param d 1 for left, 2 for right
         */
        public void moveLeftRight(int d) {
            for (int rc = 0; rc < rows*columns; rc++) {
                if (d == 1) {
                    enemies1.get(rc).moveLeft = true;
                    enemies1.get(rc).moveRight = false;
                } else {
                    enemies1.get(rc).moveLeft = false;
                    enemies1.get(rc).moveRight = true;
                }

                enemies1.get(rc).setY(enemies1.get(rc).getY() + 10);

                if(enemies1.get(rc).getY() > 480 && enemies1.get(rc).isVisible) {
                    gameOn = false;
                    level = 0;
                    score = 0;
                }
            }
        }

        /**
         * This function retur`ns the level of the current node
         *
         * @return The level of the player.
         */
        public int getLevel() {
            return level;
        }

        /**
         * This function returns the score of the player
         *
         * @return The score of the player.
         */
        public int getScore() {
            return score;
        }

        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        /**
         * When the mouse is pressed, get the x and y coordinates of the mouse and store them in the variables x and y.
         *
         * @param e The MouseEvent object that contains information about the mouse event.
         */
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }

        /**
         * Invoked when a key has been typed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key typed event.
         *
         * @param e the event to be processed
         */
        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * If the space bar is pressed, the game will either reset or load the game depending on the value of the boolean
         * variable load
         *
         * @param e the event that triggered the method
         */
        public void keyPressed(KeyEvent e) {
            int k = e.getKeyCode();
            if(k==32 && gameOn == false) {
                if (load) {
                    load();
                    load = false;
                    shot.setY(gameData.shot.getY());
                }
                else {
                    reset();
                }
                gameOn = true;
            }

            player.setLeftRight(k);
            if (k == 32 && !shot.goUp) {
                shot.goUp = true;
                shot.setX(player.getX() + (player.getW() / 2));
            }
        }

        /**
         * If the key is released, stop the player
         *
         * @param e The KeyEvent object that contains information about the key that was pressed.
         */
        public void keyReleased(KeyEvent e) {
            int k = e.getKeyCode();
            player.stop();
        }

        /**
         * The function runs the game loop, which is the main loop of the game
         */
        @Override
        public void run() {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            int animationDelay = 37;
            long time = System.currentTimeMillis();
            while (true) {
                repaint();
                try {
                    time += animationDelay;
                    Thread.sleep(Math.max(0, time - System.currentTimeMillis()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * The save button saves the game data to a file, and the load button loads the game data from a file
         *
         * @param e The event that triggered the action.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            gameData = new gameData(this);
            if(e.getSource() == saveButton) {
                SaveRunnable saveRunnable = new SaveRunnable(gameData);
                SwingUtilities.invokeLater(saveRunnable);
            }

            if(e.getSource() == loadButton) {
                LoadRunnable loadRunnable = new LoadRunnable();
                SwingUtilities.invokeLater(loadRunnable);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gameData = loadRunnable.gameData;
                    }
                });
                load();
                load = true;
            }


            if(e.getSource() == levelDownButton) {
                level--;
                reset();
            }

            if(e.getSource() == levelUpButton) {
                level++;
                reset();
            }
        }
    }
}