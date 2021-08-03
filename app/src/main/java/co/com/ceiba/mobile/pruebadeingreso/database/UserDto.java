package co.com.ceiba.mobile.pruebadeingreso.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext;
import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.entities.UserDB;

public class UserDto {

    private static UserDto userDto;
    private UserDao userDao;

    @Inject
    public UserDto(@ApplicationContext Context context) {
        Context appContext = context.getApplicationContext();
        DataBase database = Room.databaseBuilder(appContext, DataBase.class, "users")
                .allowMainThreadQueries().build();
        userDao = database.getUserDao();
    }

    public List<UserDB> getUsers() {
        return userDao.getUsers();
    }

    public UserDB getUser(String id) {
        return userDao.getUser(id);
    }

    public void addUser(UserDB user) {
        userDao.addUser(user);
    }

    public void updateUser(UserDB user) {
        userDao.updateUser(user);
    }

    public void deleteUser(UserDB user) {
        userDao.deleteUser(user);
    }


}
