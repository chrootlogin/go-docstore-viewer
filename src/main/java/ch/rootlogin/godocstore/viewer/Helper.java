package ch.rootlogin.godocstore.viewer;

import java.io.InputStream;
import java.util.logging.Logger;

public class Helper {
    private final static Logger logger = Logger.getLogger(Helper.class.getName());

    public static InputStream getInputStream(String fileName) {
        try {
            InputStream fis = Helper.class.getResourceAsStream(fileName);
            if(fis == null){
                logger.warning("Can't open file: " + fileName);

                System.exit(127);
            }

            return fis;
        } catch (NullPointerException e) {
            logger.warning("Can't open file: " + e.getMessage());

            System.exit(127);
        }

        return null;
    }
}
