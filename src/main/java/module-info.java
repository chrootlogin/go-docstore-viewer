module ch.rootlogin.godocstore.viewer {
    requires java.sql;
    requires java.logging;

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    requires com.google.guice;
    requires org.json;
    requires unirest.java;

    opens ch.rootlogin.godocstore.viewer.gui to javafx.graphics;
    opens ch.rootlogin.godocstore.viewer.gui.preloader to javafx.graphics;
    opens ch.rootlogin.godocstore.viewer.gui.controllers to javafx.fxml, com.google.guice;
}