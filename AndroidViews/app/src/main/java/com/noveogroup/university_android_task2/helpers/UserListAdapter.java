package com.noveogroup.university_android_task2.helpers;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noveogroup.university_android_task2.R;
import com.noveogroup.university_android_task2.data.model.Person;
import com.noveogroup.university_android_task2.ui.PersonListCallback;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private final List<Person> persons;

    public UserListAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_string, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(persons.get(position).toString());
    }

    public void updateList(List<Person> persons) {
        final PersonListCallback personListCallback = new PersonListCallback(this.persons, persons);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(personListCallback);

        this.persons.clear();
        this.persons.addAll(persons);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public List<Person> getPersons() {
        return persons;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.user_info);
        }
    }
}
