package co.com.ceiba.mobile.pruebadeingreso.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import co.com.ceiba.mobile.pruebadeingreso.entities.UserDB;

@Database(entities = {UserDB.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
