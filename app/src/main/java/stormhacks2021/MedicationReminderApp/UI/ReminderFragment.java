package stormhacks2021.MedicationReminderApp.UI;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import stormhacks2021.MedicationReminderApp.R;
import stormhacks2021.MedicationReminderApp.model.MedicationRemindersManager;

public class ReminderFragment extends Fragment {

    private MedicationRemindersManager remindersManager = MedicationRemindersManager.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        Button reminderButton = (Button) view.findViewById(R.id.reminder_button);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(getActivity(), TestActivity.class);
                startActivity(testIntent);
            }
        });
        return view;
    }
}
