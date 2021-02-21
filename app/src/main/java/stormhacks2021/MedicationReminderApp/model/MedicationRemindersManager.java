package stormhacks2021.MedicationReminderApp.model;

import java.util.ArrayList;
import java.util.List;

public class MedicationRemindersManager {
    private List<MedicationReminder> remindersList = new ArrayList<>();
    private static MedicationRemindersManager instance;

    private MedicationRemindersManager() {
        // private and do nothing to ensure this is a singleton
    }

    public static MedicationRemindersManager getInstance() {
        if (instance == null) {
            instance = new MedicationRemindersManager();
        }
        return instance;
    }

    public List<MedicationReminder> getRemindersList() {
        return remindersList;
    }

    public void addReminder(MedicationReminder medicationReminder) {
        remindersList.add(medicationReminder);
    }

    public MedicationReminder retrieveReminder(int position) {
        return remindersList.get(position);
    }

    public int getNumberOfReminders() {
        return remindersList.size();
    }


}
