package stormhacks2021.MedicationReminderApp.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM...", Toast.LENGTH_LONG).show();
        Log.i("AlertReceiver", "Alarm rang");
    }
}
