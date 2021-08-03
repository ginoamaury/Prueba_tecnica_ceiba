package co.com.ceiba.mobile.pruebadeingreso.presenter;

import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.entities.Post;

public class ListPostsPresenter implements IListPosts.Presenter {

    private IListPosts.View view;

    @Inject
    IListPosts.Interactor interactor;

    @Inject
    public ListPostsPresenter() {
    }

    @Override
    public void setView(IListPosts.View view) {
        this.view = view;
        this.interactor.setPresenter(this);
    }

    @Override
    public void getPosts(int id) {
        interactor.getPosts(id);
    }

    @Override
    public void showPosts(ArrayList<Post> posts) {
        if (view != null) {
            view.showPosts(posts);
        } else {
            Log.i("showPostsPresenter", "View is null");
        }
    }

    public void setInteractor(IListPosts.Interactor interactor) {
        this.interactor = interactor;
    }
}
