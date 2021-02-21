package stormhacks2021.MedicationReminderApp.model;

public class MedicationReminder implements Comparable<MedicationReminder> {
    private String medicationName;
    private MedicationDate medicationDateStart;
    private MedicationDate medicationDateEnd;
    private MedicationTime medicationTime;

    public MedicationReminder(String medicationName, MedicationDate medicationDateStart, MedicationDate medicationDateEnd, MedicationTime medicationTime) {
        this.medicationName = medicationName;
        this.medicationDateStart = medicationDateStart;
        this.medicationDateEnd = medicationDateEnd;
        this.medicationTime = medicationTime;
    }

    public String displayMedicationName() {
        return medicationName;
    }

    public String displayMedicationDateStart() {
        return medicationDateStart.displayDate();
    }

    public String displayMedicationDateEnd() {
        return medicationDateEnd.displayDate();
    }

    public String displayMedicationTime() {
        return medicationTime.displayTime();
    }

    public String toString(){
        String name = this.displayMedicationName();
        String dateStart = this.displayMedicationDateStart();
        String medTime = displayMedicationTime();

        return name + "\n" + dateStart + "\n" + medTime;

    }

    @Override
    public int compareTo(MedicationReminder that) {
        // returns negative if (this) comes before that
        int[] informationOfThis = this.medicationDateStart.getInfo();
        int[] informationOfThat = that.medicationDateStart.getInfo();
        if(informationOfThis[0] < informationOfThat[0]){
            return -1;
        } else if( informationOfThis[0] == informationOfThat[0]){
            if(informationOfThis[1] < informationOfThat[1]) {
                return -1;
            } else if (informationOfThis[1] == informationOfThat[1]){
                if(informationOfThis[2] < informationOfThat[2]) {
                    return -1;
                } else if (informationOfThis[2] == informationOfThat[2]){
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else{
            return 1;
        }
    }
}
