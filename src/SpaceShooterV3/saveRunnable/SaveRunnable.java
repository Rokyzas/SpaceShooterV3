package SpaceShooterV3.saveRunnable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is a Runnable that saves a file.
 */
public class SaveRunnable implements Runnable {

    gameData gameData;

    /** A constructor that takes in a gameData object and sets it to the gameData variable.*/
    public SaveRunnable(gameData gameData) {
        this.gameData = gameData;
    }

    /**
     * This function saves the player's data to the database.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        savePlayer();
    }

    /**
     * We create a file called SaveFile.bin, and then we write the gameData object to it.
     */
    private void savePlayer() {
        String fileName = "SaveFile.bin";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(gameData);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
