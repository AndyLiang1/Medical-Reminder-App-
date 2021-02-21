package stormhacks2021.MedicationReminderApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private TextView timeTV;
    private Button pickTime;
    private AlarmManager alarmMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTV = (TextView) findViewById(R.id.timeTV);
        pickTime = (Button) findViewById((R.id.timeBtn));

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        timeTV.setText(time);


                        long milliSec = c.get(Calendar.MILLISECOND);

                        PendingIntent alarmIntent;

                        alarmMgr = (AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(MainActivity.this, AlertReceiver.class);
                        alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                        // replace with c.getTimeInMillis()
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                                120*1000, alarmIntent);
                    }
                },hours, mins, false);
                timePickerDialog.show();


            }
        });

//


    }
}