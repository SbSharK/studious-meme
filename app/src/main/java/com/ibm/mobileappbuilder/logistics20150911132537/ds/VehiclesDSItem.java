
package com.ibm.mobileappbuilder.logistics20150911132537.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class VehiclesDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("nameofCar") public String nameofCar;
    @SerializedName("model") public String model;
    @SerializedName("carImage") public String carImage;
    @SerializedName("id") public String id;
    @SerializedName("carImageUri") public transient Uri carImageUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameofCar);
        dest.writeString(model);
        dest.writeString(carImage);
        dest.writeString(id);
    }

    public static final Creator<VehiclesDSItem> CREATOR = new Creator<VehiclesDSItem>() {
        @Override
        public VehiclesDSItem createFromParcel(Parcel in) {
            VehiclesDSItem item = new VehiclesDSItem();

            item.nameofCar = in.readString();
            item.model = in.readString();
            item.carImage = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public VehiclesDSItem[] newArray(int size) {
            return new VehiclesDSItem[size];
        }
    };

}


