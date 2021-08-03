package co.com.ceiba.mobile.pruebadeingreso;

import android.app.Application;

import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationModule;
import co.com.ceiba.mobile.pruebadeingreso.di.DaggerApplicationComponent;

public class MyApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
