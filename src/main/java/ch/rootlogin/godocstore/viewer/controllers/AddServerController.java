package ch.rootlogin.godocstore.viewer.controllers;

import ch.rootlogin.godocstore.viewer.database.Database;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

public class AddServerController {
    private final static Logger logger = Logger.getLogger(AddServerController.class.getName());

    @Inject
    private Database database;

    @FXML
    private TextField url;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public void initialize() {}
}