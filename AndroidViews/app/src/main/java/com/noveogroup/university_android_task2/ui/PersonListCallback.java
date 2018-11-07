package com.noveogroup.university_android_task2.ui;

import android.support.v7.util.DiffUtil;

import com.noveogroup.university_android_task2.data.model.Person;

import java.util.List;

public class PersonListCallback extends DiffUtil.Callback {
    private final List<Person> oldList;
    private final List<Person> newList;

    public PersonListCallback(List<Person> oldList, List<Person> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
