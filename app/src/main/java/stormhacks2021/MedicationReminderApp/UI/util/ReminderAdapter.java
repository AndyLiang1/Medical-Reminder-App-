package stormhacks2021.MedicationReminderApp.UI.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stormhacks2021.MedicationReminderApp.R;
import stormhacks2021.MedicationReminderApp.model.MedicationReminder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.RemindersViewHolder> {
    private List<MedicationReminder> reminders;

    public ReminderAdapter(List<MedicationReminder> reminders) {
        this.reminders = reminders;
    }

    public class RemindersViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public RemindersViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.firstTextView);
            textView2 = itemView.findViewById(R.id.secondTextView);
            textView3 = itemView.findViewById(R.id.thirdTextView);
        }

    }

    @NonNull
    @Override
    public RemindersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item_card_view, parent, false);
        RemindersViewHolder holder = new RemindersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersViewHolder holder, int position) {
        MedicationReminder currentReminder = reminders.get(position);

        holder.textView1.setText(currentReminder.displayMedicationName());
        holder.textView2.setText(currentReminder.displayMedicationDateStart() + " to\n" +
                currentReminder.displayMedicationDateEnd());
        holder.textView3.setText(currentReminder.displayMedicationTime());
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }
}
