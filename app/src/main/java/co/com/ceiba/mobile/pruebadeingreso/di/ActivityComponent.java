package co.com.ceiba.mobile.pruebadeingreso.di;

import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(PostActivity activity);
}
