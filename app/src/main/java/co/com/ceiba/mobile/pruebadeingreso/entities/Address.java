
package co.com.ceiba.mobile.pruebadeingreso.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Address implements Serializable
{

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("suite")
    @Expose
    private String suite;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("geo")
    @Expose
    private Geo geo;
    private final static long serialVersionUID = 2689875570917569547L;


}
