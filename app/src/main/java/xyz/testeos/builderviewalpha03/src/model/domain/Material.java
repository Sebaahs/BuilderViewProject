package xyz.testeos.builderviewalpha03.src.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Material implements Parcelable {
    // model class para material


    private String material;
    private String unit;
    private float average;

    public String getMaterial() {
        return material;
    }

    public String getUnit() {
        return unit;
    }

    public Float getAverage() {
        return average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(material);
        dest.writeString(unit);
        dest.writeFloat(average);
    }

    private void readFromParcel(Parcel in) {
        material = in.readString();
        unit = in.readString();
        average = in.readFloat();
    }

    public Material (){

    }

    public Material (Parcel in){
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Material> CREATOR
            = new Parcelable.Creator<Material>() {
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        public Material[] newArray(int size) {
            return new Material[size];
        }
    };
}
