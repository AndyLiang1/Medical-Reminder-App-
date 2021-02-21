package stormhacks2021.MedicationReminderApp.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import stormhacks2021.MedicationReminderApp.R;
import stormhacks2021.MedicationReminderApp.model.MedicationDate;
import stormhacks2021.MedicationReminderApp.model.MedicationReminder;
import stormhacks2021.MedicationReminderApp.model.MedicationRemindersManager;
import stormhacks2021.MedicationReminderApp.model.MedicationTime;

public class AddReminderActivity extends AppCompatActivity {
    private MedicationRemindersManager remindersManager = MedicationRemindersManager.getInstance();

    private String medicationName;
    private MedicationDate startDate;
    private MedicationDate endDate;
    private MedicationTime medicationTime;

    private AlarmManager alarmMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        setupMedicationNameInput();
        setupMedicationStartDate();
        setupMedicationEndDate();
        setupPickTimeButton();

        setupSaveReminderButton();
        setupCancelReminderButton();
    }

    private void setupMedicationNameInput() {
        EditText medName = (EditText) findViewById(R.id.medicationNameInput);
        medName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                medicationName = medName.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setupMedicationStartDate() {
        EditText startDateInput = (EditText) findViewById(R.id.startDateInput);
        startDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                Calendar today = Calendar.getInstance();
                int todayYear = today.get(Calendar.YEAR);
                int todayMonth = today.get(Calendar.MONTH);
                int todayDay = today.get(Calendar.DAY_OF_MONTH);

                System.out.println(Integer.toString(todayYear) + " " + Integer.toString(todayMonth) + " " + Integer.toString(todayDay));

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String currentDate = DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(calendar.getTime());

                        startDateInput.setText(currentDate);
                        startDate = new MedicationDate(dayOfMonth, month+1, year);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(AddReminderActivity.this,
                        android.R.style.Theme_Material_Light_Dialog, listener, year, month, dayOfMonth);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
    }

    private void setupMedicationEndDate() {
        EditText endDateInput = (EditText) findViewById(R.id.endDateInput);
        endDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String currentDate = DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(calendar.getTime());
                        endDateInput.setText(currentDate);
                        endDate = new MedicationDate(dayOfMonth, month+1, year);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(AddReminderActivity.this,
                        android.R.style.Theme_Material_Light_Dialog, listener, year, month, dayOfMonth);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
    }

    private void setupPickTimeButton() {
        Button pickTimeButton = (Button) findViewById((R.id.timeBtn));
        ;
        TextView timeTextView = (TextView) findViewById(R.id.timeTV);

        final String[] timeString = new String[1];

        pickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR);
                int mins = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                        R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.setTimeZone(TimeZone.getDefault());

                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(calendar.getTime());
                        timeTextView.setText(time);
                        medicationTime = new MedicationTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));

                        timeString[0] = timeTextView.getText().toString();
                        long milliSec = calendar.get(Calendar.MILLISECOND);
                        alarmMgr = (AlarmManager) AddReminderActivity.this.getSystemService(Context.ALARM_SERVICE);

                        Intent intent = new Intent(AddReminderActivity.this, AlertReceiver.class);
                        PendingIntent alarmIntent = PendingIntent.getBroadcast(AddReminderActivity.this, 0, intent, 0);
                        alarmMgr.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                    }
                }, hours, mins, false);
                timePickerDialog.show();
            }
        });
    }

    private void setupSaveReminderButton() {
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicationReminder newReminder = new MedicationReminder(medicationName, startDate,
                        endDate, medicationTime);
                remindersManager.addReminder(newReminder);
                finish();
            }
        });
    }

    private void setupCancelReminderButton() {
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}