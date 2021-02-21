package stormhacks2021.MedicationReminderApp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import stormhacks2021.MedicationReminderApp.R;
import stormhacks2021.MedicationReminderApp.model.MedicationReminder;
import stormhacks2021.MedicationReminderApp.model.MedicationRemindersManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    private static HomeFragment homeFragment;

    public static HomeFragment startHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Activity activity = getActivity();

        Button insertButton = (Button) view.findViewById(R.id.insertReminderButton);
        TextView textView = (TextView) view.findViewById((R.id.upcomingReminder));
        //textView.setText("No reminders yet");

        // Steallllin the reminders list hehe
        MedicationRemindersManager remindersManager = MedicationRemindersManager.getInstance();
        RecyclerView remindersRecyclerView;
        RecyclerView.Adapter recyclerViewAdapter;
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        List<MedicationReminder> reminders = new ArrayList<>();

        reminders.clear();
        reminders.addAll(remindersManager.getRemindersList());
        if (reminders.size() == 0) {
            textView.setText("No reminders yet");
        } else {
            // get earliest date start, get earliest time
            List<MedicationReminder> remindersClone = new ArrayList<>(reminders);
            Collections.sort(remindersClone);
            textView.setText(remindersClone.get(0).toString());
            //textView.setText(reminders.get(0).displayMedicationTime());

        }


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Creating a new reminder...", Toast.LENGTH_SHORT).show();
                Intent insertIntent = new Intent(getActivity(), AddReminderActivity.class);
                activity.startActivity(insertIntent);
//                activity.finish();
            }
        });

        return view;
    }

}
