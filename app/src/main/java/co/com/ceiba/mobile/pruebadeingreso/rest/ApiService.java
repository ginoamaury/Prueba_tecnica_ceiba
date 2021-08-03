package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(Endpoints.GET_USERS)
    Call<List<User>> getUsers();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Post>> getPosts();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Post>> getQuery(@Query(Endpoints.USER_ID) int query);
}
