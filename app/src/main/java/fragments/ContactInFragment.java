package fragments;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.contacts.Activity.MainActivity;
import com.example.contacts.R;

import Adapters.MailAdressAdapter;
import Adapters.PhoneAdapter;
import ViewModel.MailViewModel;
import ViewModel.PhoneViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ActivityResultLauncher<Intent> activityResultLauncher;



    public ContactInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactInFragment newInstance(String param1, String param2) {
        ContactInFragment fragment = new ContactInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("LastName");




        View view = inflater.inflate(R.layout.fragment_contact_in, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.emailRecyclerView);
        RecyclerView recyclerView1 = view.findViewById(R.id.phoneRecyclerView);
        TextView textView = view.findViewById(R.id.nameTextView);
        textView.setText(name);

        PhoneViewModel phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        phoneViewModel.getPhones(id).observe(getViewLifecycleOwner(), phoneModels -> {
            PhoneAdapter PhoneAdapter = new PhoneAdapter(phoneModels);
            recyclerView1.setAdapter(PhoneAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView1.setLayoutManager(linearLayoutManager);

        });







            MailViewModel mailViewModel = new ViewModelProvider(this).get(MailViewModel.class);
            mailViewModel.getMails(id).observe(getViewLifecycleOwner(), mailModels -> {
            MailAdressAdapter mailAdapter = new MailAdressAdapter(mailModels);
            recyclerView.setAdapter(mailAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);

        });

        Button buttonDelete = view.findViewById(R.id.deleteButton);
        buttonDelete.setOnClickListener(v -> {
            Intent intent1 = new Intent(getContext(), MainActivity.class);
            intent1.putExtra("id", id);
            intent1.putExtra("signal", "delete");
            Log.d(TAG, "onCreateView: "+id);
            startActivity(intent1);
        });



        return view;
    }
}