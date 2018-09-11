package ch.rootlogin.godocstore.viewer;

import ch.rootlogin.godocstore.viewer.database.Database;
import ch.rootlogin.godocstore.viewer.factories.ResourceURLStreamHandlerFactory;
import ch.rootlogin.godocstore.viewer.modules.GUIModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.application.Platform;
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

    private Database db;
    private Injector injector;

    @Override
    public void init() throws Exception {
        db = Database.INSTANCE;

        injector = Guice.createInjector(new GUIModule(
                db
        ));

        URL.setURLStreamHandlerFactory(new ResourceURLStreamHandlerFactory());
    }

    @Override
    public void start(Stage primaryStage) {
        /*var customProperties = new HashMap<>();
        customProperties.put("database", db);
        Injector.setConfigurationSource(customProperties::get);

        var mainView = new MainView();
        var scene = new Scene(mainView.getView());

        primaryStage.setScene(scene);
        primaryStage.show();*/

        while(true) {
            if(db.Configuration().get("server.default") != null) {
                break;
            }

            addServer();

            if(Platform.isImplicitExit()) {
                return;
            }
        }


        try {
            var root = loadParent("/views/main.fxml");
            primaryStage.setTitle("Go-Docstore Viewer");

            var scene = new Scene(root, 800, 600);
            scene.getStylesheets().add("internal:/style.css");
            primaryStage.setScene(scene);

            // Close app correctly.
            primaryStage.setOnCloseRequest(we -> {
                Platform.exit();
            });

            primaryStage.show();
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Cannot start main view: ", e);
            System.exit(255);
        }
    }

    private void addServer() {
        var stage = new Stage();
        try {
            var parent = loadParent("/views/create-server.fxml");
            stage.setTitle("Add connection");

            Scene scene = new Scene(parent, 300, 250);
            scene.getStylesheets().add("internal:/style.css");
            stage.setScene(scene);
            stage.setOnCloseRequest(we -> {
                Platform.setImplicitExit(true);
                Platform.exit();
            });

            stage.showAndWait();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Can't load create server view:", ex);
            System.exit(255);
        }
    }

    @Override
    public void stop() {
        // close database connection
        logger.log(Level.INFO, "Closing database connection...");
        db.closeConnection();
    }

    private Parent loadParent(String path) throws IOException {
        var fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(injector::getInstance);

        return (Parent) fxmlLoader.load(
                Helper.getInputStream(path)
        );
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", "ch.rootlogin.godocstore.viewer.gui.preloader.ApplicationPreloader");
        Application.launch(Main.class, args);
    }
}
