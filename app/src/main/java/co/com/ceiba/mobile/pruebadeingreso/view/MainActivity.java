package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListUsers;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter;


public class MainActivity extends Activity implements IListUsers.View {

    @Inject
    @ActivityContext
    Context context;

    @Inject
    IListUsers.Presenter presenter;

    @BindView(R.id.editTextSearch)
    EditText searchEditText;
    @BindView(R.id.recyclerViewSearchResults)
    RecyclerView recyclerView;
    @BindView(R.id.content)
    RelativeLayout contentLayout;

    private UserAdapter adapter;

    private ActivityComponent activityComponent;

    private View emptyView;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeDagger();
        showEmtyView();
        showDialog(this);
        presenter.setView(this);
        presenter.getUsers();
        searchUser();

    }

    private void searchUser(){
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.searchUser(editable.toString());
            }
        });
    }

    private void showEmtyView(){
        emptyView = getLayoutInflater().inflate(R.layout.empty_view, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        emptyView.setLayoutParams(params);
    }

    private void showDialog (Context context){
        dialog =  ProgressDialog.show(context, "", "Loading. Please wait...", true);
    }

    private void initializeDagger(){
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
    public void showUsers(ArrayList<User> users) {
        LinearLayoutManager linear = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linear);
        adapter = new UserAdapter(users);
        recyclerView.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void updateList(ArrayList<User> users) {
        contentLayout.removeView(emptyView);
        if (users != null) {
            adapter.updateList(users);
            if (users.isEmpty()) {
                contentLayout.addView(emptyView);
            }
        }
    }
}