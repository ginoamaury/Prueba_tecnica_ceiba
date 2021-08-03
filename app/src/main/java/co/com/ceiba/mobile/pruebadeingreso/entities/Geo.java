
package co.com.ceiba.mobile.pruebadeingreso.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Geo implements Serializable
{

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    private final static long serialVersionUID = 195926229510695172L;

}
