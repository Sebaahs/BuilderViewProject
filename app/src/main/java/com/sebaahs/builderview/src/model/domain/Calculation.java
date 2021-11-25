package com.sebaahs.builderview.src.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculation implements Parcelable {

    private String name;
    private float cost, amount;

    public Calculation(){    }

    public Calculation(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public float getCost() { return cost; }
    public float getAmount() { return amount; }

    public void setName(String name) {
        this.name = name;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    public void addAmount(float amount) {
        this.amount += amount;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(amount);
        dest.writeFloat(cost);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        amount = in.readFloat();
        cost = in.readFloat();
    }

    public Calculation (Parcel in){
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Calculation> CREATOR
            = new Parcelable.Creator<Calculation>() {
        public Calculation createFromParcel(Parcel in) {
            return new Calculation(in);
        }

        public Calculation[] newArray(int size) {
            return new Calculation[size];
        }
    };
}
