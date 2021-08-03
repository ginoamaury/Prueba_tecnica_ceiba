package co.com.ceiba.mobile.pruebadeingreso.intereactors;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPostsInteractor implements IListPosts.Interactor, Callback<List<Post>> {

    private IListPosts.Presenter presenter;
    private List<Post> posts;
    private ApiService apiService;

    @Inject
    public ListPostsInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void setPresenter(IListPosts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPosts(int id) {
        Call<List<Post>> call = apiService.getQuery(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful()){
            posts = response.body();
            if(posts != null) {
                presenter.showPosts((ArrayList<Post>) posts);
            } else {
                Log.i("onResponseShowPosts", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        Log.e("onFailureShowPosts", "Response failed");
    }
}
