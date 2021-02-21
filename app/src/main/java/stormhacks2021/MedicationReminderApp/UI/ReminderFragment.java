package stormhacks2021.MedicationReminderApp.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import stormhacks2021.MedicationReminderApp.R;
import stormhacks2021.MedicationReminderApp.UI.util.ReminderAdapter;
import stormhacks2021.MedicationReminderApp.model.MedicationReminder;
import stormhacks2021.MedicationReminderApp.model.MedicationRemindersManager;

public class ReminderFragment extends Fragment {
    private static ReminderFragment reminderFragment;

    public static ReminderFragment startReminderFragment() {
        if (reminderFragment == null) {
            reminderFragment = new ReminderFragment();
        }
        return reminderFragment;
    }

    private MedicationRemindersManager remindersManager = MedicationRemindersManager.getInstance();
    private RecyclerView remindersRecyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    List<MedicationReminder> reminders = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reminders.clear();
        reminders.addAll(remindersManager.getRemindersList());

        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        remindersRecyclerView = view.findViewById(R.id.remindersRecyclerView);
        remindersRecyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
        remindersRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new ReminderAdapter(reminders);
        remindersRecyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }
}
