package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.R;

import java.util.List;

import model.Phone;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {

    List<Phone> phones;

    public PhoneAdapter(List<Phone> phones) {
        this.phones = phones;
    }


    @NonNull
    @Override
    public PhoneAdapter.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_item, parent, false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.PhoneViewHolder holder, int position) {
        holder.phoneTextView.setText(phones.get(position).getNumber());
        holder.phoneLabelTextView.setText(phones.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {

        TextView phoneTextView; // Numéro de téléphone
        TextView phoneLabelTextView; // Libellé du numéro de téléphone

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneTextView = itemView.findViewById(R.id.phoneNumber);
            phoneLabelTextView = itemView.findViewById(R.id.labelTextView);

        }
    }
}
