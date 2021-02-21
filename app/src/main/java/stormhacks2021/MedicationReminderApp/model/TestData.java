package stormhacks2021.MedicationReminderApp.model;

public class TestData {
    private static MedicationRemindersManager remindersManager = MedicationRemindersManager.getInstance();

    public static void makeUpReminders() {
        MedicationReminder reminder = new MedicationReminder("medication A",
                new MedicationDate(20, 2, 2021),
                new MedicationDate(20, 3, 2021),
                new MedicationTime(12, 0));
        remindersManager.addReminder(reminder);

        MedicationReminder reminder2 = new MedicationReminder("medication A",
                new MedicationDate(20, 2, 2021),
                new MedicationDate(20, 3, 2021),
                new MedicationTime(12, 0));
        remindersManager.addReminder(reminder2);

        MedicationReminder reminder3 = new MedicationReminder("medication A",
                new MedicationDate(20, 2, 2021),
                new MedicationDate(20, 3, 2021),
                new MedicationTime(12, 0));
        remindersManager.addReminder(reminder3);
    }
}
