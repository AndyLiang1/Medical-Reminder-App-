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
        return String.format("%02d", medicationMonth) + " - " + String.format("%02d", medicationDay) +
                " - " + String.format("%02d", medicationYear);
    }

    public int[] getInfo(){
        int[] array = new int[3];
        array[0] = this.medicationYear;
        array[1] = this.medicationMonth;
        array[2] = this.medicationDay;
        return array;
    }


}
