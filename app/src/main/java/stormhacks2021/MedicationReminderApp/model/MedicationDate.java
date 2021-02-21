package stormhacks2021.MedicationReminderApp.model;

public class MedicationDate {
    private int medicationDay;
    private int medicationMonth;
    private int medicationYear;

    public MedicationDate(int day, int month, int year) {
        this.medicationDay = day;
        this.medicationMonth = month;
        this.medicationYear = year;
    }

    public void setMedicationDate(int day, int month, int year) {
        medicationDay = day;
        medicationMonth = month;
        medicationYear = year;
    }

    public String displayDate() {
        return medicationDay + " - " + medicationMonth + " - " + medicationYear;
    }
}
