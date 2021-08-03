package co.com.ceiba.mobile.pruebadeingreso.di;

import android.app.Activity;
import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.intereactors.ListPostsInteractor;
import co.com.ceiba.mobile.pruebadeingreso.intereactors.ListUsersInteractor;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListUsers;
import co.com.ceiba.mobile.pruebadeingreso.presenter.ListPostsPresenter;
import co.com.ceiba.mobile.pruebadeingreso.presenter.ListUsersPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    IListUsers.Presenter provideUserPresenter(ListUsersPresenter presenter){
        return  presenter;
    }

    @Provides
    IListUsers.Interactor provideUserInteractor(ListUsersInteractor interactor){
        return  interactor;
    }

    @Provides
    @PerActivity
    IListPosts.Presenter providePostsPresenter(ListPostsPresenter presenter){
        return  presenter;
    }

    @Provides
    IListPosts.Interactor providePostsInteractor(ListPostsInteractor interactor){
        return  interactor;
    }

}
