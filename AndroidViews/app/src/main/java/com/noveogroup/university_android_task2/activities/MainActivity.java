package com.noveogroup.university_android_task2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.noveogroup.university_android_task2.R;
import com.noveogroup.university_android_task2.data.PersonProvider;
import com.noveogroup.university_android_task2.data.Sorter;
import com.noveogroup.university_android_task2.data.model.Person;
import com.noveogroup.university_android_task2.helpers.UserListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.sorting_fields)
    RadioGroup sortingFieldRadioGroup;

    @BindView(R.id.sorting_type)
    RadioGroup sortingTypeRadioGroup;

    private Sorter.SortingType sortingField = null;
    private Boolean ascendingSort = null;

    private RecyclerView recyclerView;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.user_list);
        adapter = new UserListAdapter(PersonProvider.getInstance().getPersonsList());
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setItemTouchHelper();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("size", adapter.getPersons().size());
        for (int i = 0; i < adapter.getPersons().size(); i++) {
            outState.putParcelable(Integer.toString(i), adapter.getPersons().get(i));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<Person> persons = new ArrayList<>();
        int arraySize = savedInstanceState.getInt("size");
        for (int i = 0; i < arraySize; i++) {
            persons.add((Person) savedInstanceState.getParcelable(Integer.toString(i)));
        }
        adapter = new UserListAdapter(persons);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    void addUser() {
        PersonProvider personProvider = PersonProvider.getInstance();
        Person person = personProvider.getPerson();
        adapter.getPersons().add(person);
        if (canBeSorted()) {
            List<Person> tempList = getCopyOfList(adapter.getPersons());
            adapter.updateList(Sorter.sort(tempList, ascendingSort, sortingField));
        } else {
            adapter.notifyItemInserted(adapter.getPersons().size() - 1);
        }
    }

    private void setItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                adapter.getPersons().remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @OnClick({R.id.sort_by_age, R.id.sort_by_gender})
    void setSortingField(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        List<Person> tempList = getCopyOfList(adapter.getPersons());

        if (radioButton.getId() == R.id.sort_by_age) {
            if (checked) {
                sortingField = Sorter.SortingType.AGE;
            }
        }
        if (radioButton.getId() == R.id.sort_by_gender) {
            if (checked) {
                sortingField = Sorter.SortingType.GENDER;
            }
        }
        if (ascendingSort != null) {
            adapter.updateList(Sorter.sort(tempList, ascendingSort, sortingField));
        } else {
            showToast("Choose sorting type");
        }
    }

    @OnClick({R.id.ascending_sort, R.id.descending_sort})
    void setSortingType(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        List<Person> tempList = getCopyOfList(adapter.getPersons());

        if (radioButton.getId() == R.id.ascending_sort) {
            if (checked) {
                ascendingSort = true;
            }
        }
        if (radioButton.getId() == R.id.descending_sort) {
            if (checked) {
                ascendingSort = false;
            }
        }
        if (sortingField != null) {
            adapter.updateList(Sorter.sort(tempList, ascendingSort, sortingField));
        } else {
            showToast("Choose sorting field");
        }
    }

    private List<Person> getCopyOfList(List<Person> persons) {
        List<Person> newPersons = new ArrayList<>();
        newPersons.addAll(persons);
        return newPersons;
    }

    private boolean canBeSorted() {
        return ascendingSort != null && sortingField != null;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
