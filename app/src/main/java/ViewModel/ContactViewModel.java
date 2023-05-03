package ViewModel;
import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import DBConfig.API;
import model.Contact;

public class

ContactViewModel extends ViewModel {

    private API api;

    public ContactViewModel() {
        api = new API();
    }

    public LiveData<List<Contact>> getContacts() {
        api.loadContacts();
        return api.getContacts();
    }

    public void removeContact(String id) {
        Log.d(TAG, "removeContact: "+id);

        api.removeContactById(id);
    }






}
