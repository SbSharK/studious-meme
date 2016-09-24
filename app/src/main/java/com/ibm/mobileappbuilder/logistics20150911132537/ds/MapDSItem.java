
package com.ibm.mobileappbuilder.logistics20150911132537.ds;
import ibmmobileappbuilder.ds.restds.GeoPoint;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class MapDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("location") public GeoPoint location;
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
        dest.writeDoubleArray(location != null  && location.coordinates.length != 0 ? location.coordinates : null);
        dest.writeString(id);
    }

    public static final Creator<MapDSItem> CREATOR = new Creator<MapDSItem>() {
        @Override
        public MapDSItem createFromParcel(Parcel in) {
            MapDSItem item = new MapDSItem();

            double[] location_coords = in.createDoubleArray();
            if (location_coords != null)
                item.location = new GeoPoint(location_coords);
            item.id = in.readString();
            return item;
        }

        @Override
        public MapDSItem[] newArray(int size) {
            return new MapDSItem[size];
        }
    };

}


