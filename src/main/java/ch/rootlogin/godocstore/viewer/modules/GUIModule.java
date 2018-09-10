package ch.rootlogin.godocstore.viewer.modules;

import ch.rootlogin.godocstore.viewer.models.Configuration;
import com.google.inject.AbstractModule;

public class GUIModule extends AbstractModule {
    private Configuration configuration = new Configuration();

    @Override
    protected void configure() {
        bind(Configuration.class).toInstance(configuration);
    }
}
