package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.MyApp;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.di.ActivityComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.ActivityContext;
import co.com.ceiba.mobile.pruebadeingreso.di.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.di.DaggerActivityComponent;
import co.com.ceiba.mobile.pruebadeingreso.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostAdapter;

public class PostActivity extends Activity implements IListPosts.View {

    private ActivityComponent activityComponent;

    @Inject
    IListPosts.Presenter presenter;
    @Inject
    @ActivityContext
    Context context;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.recyclerViewPostsResults)
    RecyclerView recyclerView;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        initializeDagger();

        User user = getUserBundle();
        if(user != null){
            setDataUser(user);
            showDialog(this);
            presenter.setView(this);
            presenter.getPosts(user.getId());
        }



    }

    private void showDialog (Context context){
        dialog =  ProgressDialog.show(context, "", "Loading. Please wait...", true);
    }

    private void setDataUser(User user){
        name.setText(user.getName());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
    }

    private User getUserBundle(){
        Bundle bundle = getIntent().getExtras();
        User user = null;
        if (bundle != null) {
            user = (User) bundle.getSerializable("user");
        }
        return user;
    }

    private void initializeDagger (){
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApp) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void showPosts(ArrayList<Post> posts) {
        LinearLayoutManager linear = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linear);
        PostAdapter adapter = new PostAdapter(posts);
        recyclerView.setAdapter(adapter);
        dialog.dismiss();
    }
}
