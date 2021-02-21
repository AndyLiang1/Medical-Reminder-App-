package stormhacks2021.MedicationReminderApp.UI.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public RemindersViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.fistTextView);
            textView2 = itemView.findViewById(R.id.secondTextView);
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
        holder.textView2.setText(currentReminder.displayMedicationDateStart() + " to " +
                currentReminder.displayMedicationDateEnd());
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }
}
