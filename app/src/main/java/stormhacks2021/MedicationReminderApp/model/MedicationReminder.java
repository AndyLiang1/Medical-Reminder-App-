package stormhacks2021.MedicationReminderApp.model;

public class MedicationReminder {
    private String medicationName;
    private MedicationDate medicationDateStart;
    private MedicationDate medicationDateEnd;
    private MedicationTime medicationTime;

    public MedicationReminder(String  medicationName, MedicationDate medicationDateStart, MedicationDate medicationDateEnd, MedicationTime medicationTime) {
        this. medicationName = medicationName;
        this.medicationDateStart = medicationDateStart;
        this.medicationDateEnd = medicationDateEnd;
        this.medicationTime = medicationTime;
    }
}
