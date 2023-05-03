package ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import DBConfig.API;
import model.MailAddress;

public class MailViewModel extends ViewModel {

    private API api;

    public MailViewModel() {

        api = new API();
    }

    public LiveData<List<MailAddress>> getMails(String id) {
        api.loadMailAddressesById(id);
        return api.getMailAddresses();
    }



}




