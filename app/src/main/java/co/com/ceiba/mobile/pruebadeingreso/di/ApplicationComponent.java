package co.com.ceiba.mobile.pruebadeingreso.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.MyApp;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiService;
import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApp app);

    @ApplicationContext
    Context context();

    Application application();

    ApiService apiService();
}
