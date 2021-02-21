package stormhacks2021.MedicationReminderApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class InsertActivity extends Activity {
    private TextView timeTV;
    private Button pickTime;
    private AlarmManager alarmMgr;

    private EditText medName;
    private EditText inputDate;
    private Button saveButton;
    private Button cancelButton;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        timeTV = (TextView) findViewById(R.id.timeTV);
        pickTime = (Button) findViewById((R.id.timeBtn));

        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        final String[] timeString = new String[1];

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR);
                int mins = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(InsertActivity.this,
                        R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());

                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        timeTV.setText(time);

                        timeString[0] = timeTV.getText().toString();
                        long milliSec = c.get(Calendar.MILLISECOND);
                        alarmMgr = (AlarmManager) InsertActivity.this.getSystemService(Context.ALARM_SERVICE);

                        Intent intent = new Intent(InsertActivity.this, AlertReceiver.class);
                        PendingIntent alarmIntent = PendingIntent.getBroadcast(InsertActivity.this, 0, intent, 0);
                        alarmMgr.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, c.getTimeInMillis(), alarmIntent);
                    }
                }, hours, mins, false);
                timePickerDialog.show();

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createButtonReminder(timeString[0]);
            }
        });

//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancelInsertion();
//            }
//        });

    }

    private void createButtonReminder(String time) {
        medName = (EditText) findViewById(R.id.putInName);
        inputDate = (EditText) findViewById(R.id.putInDate);
        String newLine = getString(R.string.newline);

        String medication = medName.getText().toString(); //get user inputs
        String setDate = inputDate.getText().toString();

        //creates a new button and sets the medication name, date, and time, each on a different line
        Button newButton = new Button(this);
        String buttonText = medication + newLine + setDate + newLine + time;
        newButton.setText(buttonText);

        newButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                  LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout layout = new LinearLayout(this); //trying to add button to the Reminder Layout
        layout.addView(newButton);

        Intent insertIntent = new Intent(activity, ReminderFragment.class);
        activity.startActivity(insertIntent);
        activity.finish();
    }




}