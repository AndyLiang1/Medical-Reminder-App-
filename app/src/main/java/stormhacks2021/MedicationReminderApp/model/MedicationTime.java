package stormhacks2021.MedicationReminderApp.model;

public class MedicationTime {
    private int medicationHour;
    private int medicationMinute;
    private boolean is24Hr = true;

    public MedicationTime(int hour, int minute) {
        this.medicationHour = hour;
        this.medicationMinute = minute;

        if (hour > 12) {
            is24Hr = true;
            this.medicationHour = hour - 12;
        }

        if (medicationHour == 0) {
            this.medicationHour = 12;
        }
    }

    public void setMedicationTime(int hour, int minute) {
        medicationHour = hour - 12;
        medicationMinute = minute;
    }

    public String displayTime() {
        String result = String.format("%02d", medicationHour) + ":" + String.format("%02d", medicationMinute);

        if (is24Hr) {
            result = result + " PM";

        } else {
            result = result + " AM";
        }

        return result;
    }
}
