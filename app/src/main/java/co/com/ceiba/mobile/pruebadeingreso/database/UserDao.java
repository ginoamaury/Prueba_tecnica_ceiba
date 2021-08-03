package co.com.ceiba.mobile.pruebadeingreso.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.entities.UserDB;

@Dao
public interface UserDao {
    @Query("SELECT * FROM userdb")
    List<UserDB> getUsers();

    @Query("SELECT * FROM userdb WHERE id LIKE :id")
    UserDB getUser(String id);

    @Insert
    void addUser(UserDB user);

    @Delete
    void deleteUser(UserDB user);

    @Update
    void updateUser(UserDB favorite);
}
