package ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import DBConfig.API;
import model.Phone;


public class PhoneViewModel extends ViewModel{

    private API api;

    public PhoneViewModel() {
        api = new API();
    }

    public LiveData<List<Phone>> getPhones(String id) {
        api.loadPhoneNumbersById(id);
        return api.getPhones();
    }
}
