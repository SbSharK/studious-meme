
package com.ibm.mobileappbuilder.logistics20150911132537.ds;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ClientsDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("clientID") public Long clientID;
    @SerializedName("name") public String name;
    @SerializedName("phone") public String phone;
    @SerializedName("email") public String email;
    @SerializedName("address") public String address;
    @SerializedName("zIPCode") public Long zIPCode;
    @SerializedName("city") public String city;
    @SerializedName("country") public String country;
    @SerializedName("id") public String id;

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
        dest.writeValue(clientID);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeValue(zIPCode);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(id);
    }

    public static final Creator<ClientsDSItem> CREATOR = new Creator<ClientsDSItem>() {
        @Override
        public ClientsDSItem createFromParcel(Parcel in) {
            ClientsDSItem item = new ClientsDSItem();

            item.clientID = (Long) in.readValue(null);
            item.name = in.readString();
            item.phone = in.readString();
            item.email = in.readString();
            item.address = in.readString();
            item.zIPCode = (Long) in.readValue(null);
            item.city = in.readString();
            item.country = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public ClientsDSItem[] newArray(int size) {
            return new ClientsDSItem[size];
        }
    };

}


