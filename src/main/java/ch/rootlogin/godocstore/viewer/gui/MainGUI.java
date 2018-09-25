package ch.rootlogin.godocstore.viewer.gui;

import ch.rootlogin.godocstore.viewer.Helper;
import ch.rootlogin.godocstore.viewer.database.Database;
import ch.rootlogin.godocstore.viewer.factories.ResourceURLStreamHandlerFactory;
import ch.rootlogin.godocstore.viewer.modules.GUIModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainGUI extends Application {
    private final static Logger logger = Logger.getLogger(MainGUI.class.getName());

    private Database db;
    private Injector injector;

    //private GuiceContext guiceContext;

    /*@Inject
    private FXMLLoader fxmlLoader;*/

    @Override
    public void init() throws Exception {
        db = Database.INSTANCE;

        injector = Guice.createInjector(new GUIModule(db));

        //guiceContext = new GuiceContext(this, () -> Arrays.asList(new GUIModule(db)));

        URL.setURLStreamHandlerFactory(new ResourceURLStreamHandlerFactory());
    }

    @Override
    public void start(Stage primaryStage) {
        //guiceContext.init();

        /*var customProperties = new HashMap<>();
        customProperties.put("ch.rootlogin.godocstore.viewer.database", db);
        Injector.setConfigurationSource(customProperties::get);

        var mainView = new MainView();
        var scene = new Scene(mainView.getView());

        primaryStage.setScene(scene);
        primaryStage.show();*/

        /*while(true) {
            if(db.Configuration().get("server.default") != null) {
                break;
            }

            addServer();

            if(Platform.isImplicitExit()) {
                return;
            }
        }*/


        /*try {
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
        }*/

        try {
            // Load main view
            var root = loadParent("/views/main.fxml");
            primaryStage.setTitle("Go-Docstore Viewer");

            // Create scene
            var scene = new Scene(root, 800, 600);
            scene.getStylesheets().add("internal:/style.css");
            primaryStage.setScene(scene);

            // Set on close action.
            primaryStage.setOnCloseRequest(we -> {
                Platform.exit();
            });

            primaryStage.show();
        } catch(IOException ex) {
            logger.log(Level.SEVERE, "Cannot start main view: ", ex);
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
        // close ch.rootlogin.godocstore.viewer.database connection
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
}
