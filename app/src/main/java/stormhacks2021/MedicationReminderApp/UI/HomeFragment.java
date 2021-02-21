package stormhacks2021.MedicationReminderApp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import stormhacks2021.MedicationReminderApp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Activity activity = getActivity();

        Button insertButton = (Button) view.findViewById(R.id.insertReminderButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Creating a new reminder...", Toast.LENGTH_SHORT).show();
                Intent insertIntent = new Intent(getActivity(), AddReminderActivity.class);
                activity.startActivity(insertIntent);
//                activity.finish();
            }
        });

        return view;
    }

}
