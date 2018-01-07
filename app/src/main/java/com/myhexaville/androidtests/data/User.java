package com.myhexaville.androidtests.data;

import android.os.Parcel;
import android.os.Parcelable;

public class User  implements Parcelable{
    private String firstName, lastName, country;
    private long birthDate;

    public User(String firstName, String lastName, long birthDate, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
    }

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        birthDate = in.readLong();
        country = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.wri
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeLong(birthDate);
        dest.writeString(country);
    }
}
