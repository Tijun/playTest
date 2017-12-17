package module;

import com.google.inject.AbstractModule;
import service.ApplicationService;
import service.impl.ApplicationServiceImpl;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ApplicationService.class).to(ApplicationServiceImpl.class);

    }
}
