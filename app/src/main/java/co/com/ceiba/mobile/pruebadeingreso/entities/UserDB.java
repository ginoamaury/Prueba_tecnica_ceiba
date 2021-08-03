package co.com.ceiba.mobile.pruebadeingreso.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Data;

@Entity
@Data
public class UserDB {
    @PrimaryKey
    @NonNull
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String website;


    public UserDB(@NonNull Integer id, String name, String phone, String email, String username, String website) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.website = website;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }


}
