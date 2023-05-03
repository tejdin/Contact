package Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import Interface.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Contact;
import com.example.contacts.R;



public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private OnItemClickListener listener;
    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;

    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        holder.nameTextView.setText(contacts.get(position).getName()+" "+contacts.get(position).getLastName());
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void removeContact(String id, int position) {

        contacts.removeIf(contact -> contact.getId().equals(id)); // Supprimer le contact de la liste
        notifyItemRemoved(position); // Informer l'Adapter de la suppression
    }
    public void addContact(Contact contact) {
        contacts.add(contact); // Ajouter le contact à la liste
        notifyItemInserted(contacts.size() - 1); // Informer l'Adapter de l'ajout
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Déclarez ici les vues présentes dans votre layout, par exemple :
        TextView nameTextView;

        // TextView nameTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);


            // Initialisez ici les vues présentes dans votre layout, par exemple :
            nameTextView = itemView.findViewById(R.id.contact_name);
            itemView.setOnClickListener(this);



        }

        // Déclarez ici les méthodes permettant de définir les données pour chaque élément de la liste
        public void setName(String name) {
            nameTextView.setText(name);

        }



        @Override
        public void onClick(View view) {
                int position = getAdapterPosition();
                    listener.onClick(view, position);

        }
    }


}
