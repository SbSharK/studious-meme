
package com.ibm.mobileappbuilder.logistics20150911132537.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class DriversScreen1DSItem implements Parcelable, IdentifiableBean {

    @SerializedName("firstname") public String firstname;
    @SerializedName("lastname") public String lastname;
    @SerializedName("email") public String email;
    @SerializedName("address") public String address;
    @SerializedName("phone") public String phone;
    @SerializedName("picture") public String picture;
    @SerializedName("college") public String college;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

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
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(picture);
        dest.writeString(college);
        dest.writeString(id);
    }

    public static final Creator<DriversScreen1DSItem> CREATOR = new Creator<DriversScreen1DSItem>() {
        @Override
        public DriversScreen1DSItem createFromParcel(Parcel in) {
            DriversScreen1DSItem item = new DriversScreen1DSItem();

            item.firstname = in.readString();
            item.lastname = in.readString();
            item.email = in.readString();
            item.address = in.readString();
            item.phone = in.readString();
            item.picture = in.readString();
            item.college = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public DriversScreen1DSItem[] newArray(int size) {
            return new DriversScreen1DSItem[size];
        }
    };

}


