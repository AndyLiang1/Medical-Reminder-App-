package stormhacks2021.MedicationReminderApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showUpcoming();
        insert();
    }

    /**
     * This method is to listen to the insertReminder button and switch to the Insert
     * New Reminder activity.
     *
     */
    public void insert() {

        Button insertReminder = (Button) findViewById(R.id.insertReminderButton);
        insertReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to insert page activity
                Toast.makeText(MainActivity.this, "Creating a new reminder",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method changes and shows the text of the upcomingText according to the upcoming reminder.
     */
    public void showUpcoming() {
        TextView upcomingText = (TextView) findViewById(R.id.upcomingReminder);
        upcomingText.setText((CharSequence) "Reminder Section"); //need to get the next reminder from the reminders activity

    }
}