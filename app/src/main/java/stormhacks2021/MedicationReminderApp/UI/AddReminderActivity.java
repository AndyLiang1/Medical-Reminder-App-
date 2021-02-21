package stormhacks2021.MedicationReminderApp.UI;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
        medicationName = medName.getText().toString();
    }

    private void setupMedicationStartDate() {
        EditText startDateInput = (EditText) findViewById(R.id.startDateInput);
        startDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new StartDatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                Calendar calendar = Calendar.getInstance();
                MedicationDate date = new MedicationDate(calendar.get(Calendar.DAY_OF_MONTH),
                        Calendar.MONTH, Calendar.YEAR);
                startDate = date;
            }
        });
    }

    private void setupMedicationEndDate() {
        EditText endDateInput = (EditText) findViewById(R.id.endDateInput);
        endDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new EndDatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                Calendar calendar = Calendar.getInstance();
                MedicationDate date = new MedicationDate(calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
                endDate = date;
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

//    private void createButtonReminder(String time) {
//        String newLine = getString(R.string.newline);
//
//        //creates a new button and sets the medication name, date, and time, each on a different line
//        Button newButton = new Button(this);
////        String buttonText = medicationName + newLine + setDate + newLine + time;
////        newButton.setText(buttonText);
//
//        newButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//
//        LinearLayout layout = new LinearLayout(this); //trying to add button to the Reminder Layout
//        layout.addView(newButton);
//
////        Intent insertIntent = new Intent(InsertActivity.this, HomeFragment.class);
////        startActivity(insertIntent);
//        finish();
//    }


}