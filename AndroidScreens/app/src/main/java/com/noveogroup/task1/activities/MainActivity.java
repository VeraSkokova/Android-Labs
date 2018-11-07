package com.noveogroup.task1.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.noveogroup.task1.R;
import com.noveogroup.task1.date.DateConverter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static butterknife.OnTextChanged.Callback.AFTER_TEXT_CHANGED;


public class MainActivity extends AppCompatActivity {
    public static final String SURNAME_MESSAGE = "Surname";
    public static final String NAME_MESSAGE = "Name";
    public static final String AGE_MESSAGE = "Age";

    private final Calendar calendar = Calendar.getInstance();
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.surname_edit_text)
    EditText surnameEditText;
    @BindView(R.id.date_edit_text)
    EditText dateText;

    private boolean isDateSet;
    private int age = -1;

    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(dateText);
            isDateSet = true;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void sendMessage() {
        Intent intent = newIntent();
        if (intent != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.date_edit_text)
    void showDatePickerDialog() {
        new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnTextChanged(value = R.id.date_edit_text, callback = AFTER_TEXT_CHANGED)
    void afterDateTextChanged() {
        age = DateConverter.getInstance().countAge(calendar);
        if (!checkAge(age)) {
            showToast(R.string.age_error);
            dateText.setTextColor(Color.RED);
        } else {
            dateText.setTextColor(Color.GRAY);
        }
    }

    private void updateLabel(EditText editText) {
        editText.setText(DateConverter.getInstance().dateToString(calendar.getTime()));
    }

    private boolean checkData(String name, String surname) {
        return !(TextUtils.isEmpty(name) || (TextUtils.isEmpty(surname)) || (!isDateSet));
    }

    private boolean checkAge(int age) {
        return !(age < 0);
    }

    private void putData(Intent intent, String name, String surname, String age) {
        intent.putExtra(SURNAME_MESSAGE, surname);
        intent.putExtra(NAME_MESSAGE, name);
        intent.putExtra(AGE_MESSAGE, age);
    }

    private boolean handleData(Intent intent, String nameMessage, String surnameMessage, int age) {
        if ((checkData(nameMessage, surnameMessage)) && (checkAge(age))) {
            return true;
        } else if (!checkData(nameMessage, surnameMessage)) {
            showToast(R.string.info_text);
        } else {
            showToast(R.string.age_error);
        }
        return false;
    }

    private Intent newIntent() {
        Intent intent = new Intent(this, AnotherActivity.class);

        String surnameMessage = surnameEditText.getText().toString();
        String nameMessage = nameEditText.getText().toString();
        String ageMessage = Integer.toString(age);

        if (handleData(intent, nameMessage, surnameMessage, age)) {
            putData(intent, nameMessage, surnameMessage, ageMessage);
            return intent;
        } else {
            return null;
        }
    }

    private void showToast(int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }
}
