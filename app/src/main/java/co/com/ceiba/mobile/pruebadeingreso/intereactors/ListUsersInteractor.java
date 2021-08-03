package co.com.ceiba.mobile.pruebadeingreso.intereactors;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.entities.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.presenter.IListUsers;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersInteractor implements IListUsers.Interactor, Callback<List<User>> {

    private IListUsers.Presenter presenter;
    private List<User> users;
    private UserDto userDto;
    private ApiService apiService;

    @Inject
    public ListUsersInteractor(UserDto userDto, ApiService apiService) {
        this.userDto = userDto;
        this.users = new ArrayList<>();
        this.apiService = apiService;
    }

    @Override
    public void setPresenter(IListUsers.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getUsers() {
        if (userDto != null) {
            if (userDto.getUsers().isEmpty()) {
                Call<List<User>> call = apiService.getUsers();
                call.enqueue(this);
            } else {
                getLocalUsers();
                presenter.showUsers((ArrayList<User>)users);
            }
        } else {
            Log.i("getUsersInteractor", "UserDto is null");
        }
    }

    @Override
    public void searchUser(String text) {
        ArrayList<User> filterList = new ArrayList<>();
        if( users != null) {
            for (User user : users) {
                if (user.getName().toLowerCase().contains(text.toLowerCase())) {
                    filterList.add(user);
                }
            }
        }
        presenter.updateList(filterList);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()){
            users = response.body();
            if(users != null) {
                presenter.showUsers((ArrayList<User>)users);
                saveUsers();
            } else {
                Log.i("onResponseShowUsers", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {

    }

    private void getLocalUsers(){
        if(userDto != null) {
            for (UserDB user: userDto.getUsers()) {
                User newUser = new User();
                newUser.setName(user.getName());
                newUser.setUsername(user.getUsername());
                newUser.setEmail(user.getEmail());
                newUser.setPhone(user.getPhone());
                newUser.setWebsite(user.getWebsite());
                newUser.setId(user.getId());
                users.add(newUser);
            }
        } else {
            Log.i("getLocalUsers", "UsersDto is null");
        }

    }

    private void saveUsers(){
        if( users != null) {
            for (User user: users) {
                UserDB newUser = new UserDB(
                        user.getId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getWebsite());
                userDto.addUser(newUser);
            }
        } else {
            Log.i("saveUsers", "Users is null");
        }

    }
}
