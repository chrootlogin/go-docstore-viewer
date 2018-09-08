package ch.rootlogin.godocstore.viewer;

import ch.rootlogin.godocstore.viewer.factories.ResourceURLStreamHandlerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            var fxmlLoader = new FXMLLoader();

            var root = (Parent) fxmlLoader.load(Helper.getInputStream("/views/main.fxml"));
            primaryStage.setTitle("Go-Docstore Viewer");

            var scene = new Scene(root, 800, 600);
            URL.setURLStreamHandlerFactory(new ResourceURLStreamHandlerFactory());
            scene.getStylesheets().add("internal:/style.css");
            primaryStage.setScene(scene);

            // Close app correctly.
            /*primaryStage.setOnCloseRequest(we -> {
                Platform.setImplicitExit(false);

                primaryStage.close();

                System.exit(0);
            });*/

            primaryStage.show();
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Cannot start GUI: " + e.getMessage());
            System.exit(255);
        }
    }

    public static void main(String[] args) {
            launch(args);
    }
}
