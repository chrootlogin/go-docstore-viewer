package ch.rootlogin.godocstore.viewer.controllers;

import ch.rootlogin.godocstore.viewer.database.Database;
import com.google.inject.Inject;

import java.util.logging.Logger;

public class MainViewController {
    private final static Logger logger = Logger.getLogger(MainViewController.class.getName());

    @Inject
    private Database database;

    public void initialize() {}
}