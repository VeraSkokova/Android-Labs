package com.noveogroup.university_android_task2.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class Person implements Parcelable {
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger();
    private final int id;
    private final String name;
    private final int age;
    private final Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = ID_GENERATOR.getAndIncrement();
    }

    protected Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
        gender = (Gender) in.readSerializable();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person name: " + name + ", age: " + String.valueOf(age) + ", gender is: " + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (age != person.age) return false;
        if (!(TextUtils.equals(name, person.getName()))) return false;
        return gender == person.gender;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeSerializable(gender);

    }
}
