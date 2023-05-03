package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.R;

import java.util.List;

import model.MailAddress;

public class MailAdressAdapter extends RecyclerView.Adapter<MailAdressAdapter.ViewHolder> {

    List<MailAddress> mailAddresses;

    public MailAdressAdapter(List<MailAddress> mailAddresses) {
        this.mailAddresses = mailAddresses;
    }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mailAdress.setText(mailAddresses.get(position).getMail());
            holder.mailAdresslable.setText(mailAddresses.get(position).getType());
        }

        @Override
        public int getItemCount() {
            return mailAddresses.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mailAdress;
            TextView mailAdresslable;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mailAdress = itemView.findViewById(R.id.emailAddress);
                mailAdresslable = itemView.findViewById(R.id.labelTextView);

            }
        }



}
