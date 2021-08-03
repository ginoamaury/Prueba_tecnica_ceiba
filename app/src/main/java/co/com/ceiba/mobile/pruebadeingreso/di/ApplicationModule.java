package co.com.ceiba.mobile.pruebadeingreso.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiService;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }


    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    UserDto provideUserDto(UserDto userDto){
        return userDto;
    }

    @Provides
    @Singleton
    ApiService provideApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}
