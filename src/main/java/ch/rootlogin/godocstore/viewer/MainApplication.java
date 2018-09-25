package ch.rootlogin.godocstore.viewer;

import ch.rootlogin.godocstore.viewer.gui.MainGUI;
import javafx.application.Application;

import java.util.logging.Logger;

/**
 * MainApplication Application Class
 */
public class MainApplication {
    /**
     * Logger facility
     */
    private final static Logger logger = Logger.getLogger(MainApplication.class.getName());

    private MainApplication(String[] args) {
        System.setProperty("javafx.preloader", "ch.rootlogin.godocstore.viewer.gui.preloader.ApplicationPreloader");
        Application.launch(MainGUI.class, args);
    }

    /**
     * Boots the application
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        new MainApplication(args);
    }
}
