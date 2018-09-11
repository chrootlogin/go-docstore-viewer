package ch.rootlogin.godocstore.viewer.modules;

import ch.rootlogin.godocstore.viewer.database.Database;
import ch.rootlogin.godocstore.viewer.models.Configuration;
import com.google.inject.AbstractModule;

public class GUIModule extends AbstractModule {
    private Database database;

    public GUIModule(Database database) {
        this.database = database;
    }

    @Override
    protected void configure() {
        bind(Database.class).toInstance(database);
    }
}
