package fragments;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.Activity.ContactInfoActivity;
import com.example.contacts.Activity.MainActivity;
import com.example.contacts.R;

import java.util.List;

import Adapters.ContactAdapter;
import Interface.OnItemClickListener;
import ViewModel.ContactViewModel;
import model.Contact;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactList extends Fragment implements OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String ClickedContactId;

    List<Contact> contactList;


    public ContactList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactList.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactList newInstance(String param1, String param2) {
        ContactList fragment = new ContactList();
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
    String signal=" ";
    String id="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        Bundle bundle = getArguments();

        if (bundle == null) {
            Log.d(TAG, "onCreateView: bundle is null");
        } else {
            Log.d(TAG, "onCreateView: bundle is not null");
            id = bundle.getString("id");
            signal = bundle.getString("signal");
            Log.d(TAG, "onCreateView: " + id + " " + signal);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            ContactAdapter contactAdapter = new ContactAdapter(contacts);

            if (bundle != null) {
                if ("delete".equals(signal)) {
                    contactViewModel.removeContact(id);
                    contactAdapter.removeContact(id, Integer.parseInt(id)-1);
                    Log.d(TAG, "onCreateView: " + id + " " + signal);
                }
            }

            contactAdapter.setOnItemClickListener(this);
            recyclerView.setAdapter(contactAdapter);
            contactList = contacts;
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
        });

        return view;
    }

    @Override
    public void onClick(View view, int position) {

        Log.d(TAG, "onClick: tej");
        ClickedContactId= contactList.get(position).getId();
        Intent intent = new Intent(getContext(), ContactInfoActivity.class);
        intent.putExtra("id", contactList.get(position).getId());
        intent.putExtra("name", contactList.get(position).getName()+" "+contactList.get(position).getLastName());
        Log.d(TAG, "onClick: "+contactList.get(position).getLastName());

        startActivity(intent);
    }



}