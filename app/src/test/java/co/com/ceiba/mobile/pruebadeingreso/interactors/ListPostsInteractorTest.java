package co.com.ceiba.mobile.pruebadeingreso.interactors;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.intereactors.ListPostsInteractor;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListPosts;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@RunWith(MockitoJUnitRunner.class)
public class ListPostsInteractorTest {

    @Mock
    IListPosts.Presenter presenter;

    private ArrayList<Post> posts;
    @InjectMocks
    private ListPostsInteractor interactor;

    @Before
    public void init() {
        this.posts = new ArrayList<>();
        this.interactor.setPresenter(presenter);
    }


    @Test
    public void onResponse() {
        Call<List<Post>> call = mock(Call.class);
        Response<List<Post>> response = Response.success(posts);
        this.interactor.onResponse(call, response);
        verify(this.presenter, times(1)).showPosts(any());
    }


}
