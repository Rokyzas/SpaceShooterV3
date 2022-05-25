package SpaceShooterV3.saveRunnable;

import java.io.*;

/**
 * > This class is a Runnable that loads a file
 */
public class LoadRunnable implements Runnable {

    public gameData gameData;

    public LoadRunnable() {
        this.gameData = gameData;
    }

    @Override

    /** Calling the loadGameData() method. */
    public void run() {
        loadGameData();
    }

    /**
     * It reads the file "SaveFile.bin" and tries to convert it into a gameData object
     */
    public void loadGameData() {
        String fileName = "SaveFile.bin";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object o = null;
            try {
                o= is.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (o instanceof gameData) {
                gameData = (gameData) o;
            }

            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
