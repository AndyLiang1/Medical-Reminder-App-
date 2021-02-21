package stormhacks2021.MedicationReminderApp.model;

public class MedicationTime {
    private int medicationHour;
    private int medicationMinute;

    public MedicationTime(int hour, int minute) {
        this.medicationHour = hour;
        this.medicationMinute = minute;
    }

    public void setMedicationTime(int hour, int minute) {
        medicationHour = hour;
        medicationMinute = minute;
    }

    public String displayTime() {
        return medicationHour + ":" + medicationMinute;
    }
}
