package DBConfig;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.contacts.Activity.MainActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.MailAddress;
import model.Phone;

public class API {

    private String url = "https://surefootedunsunginsurance.mednine.repl.co/person";

    private final MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
    private final MutableLiveData<Contact> contact = new MutableLiveData<>();

    private final MutableLiveData<List<Phone>> phones = new MutableLiveData<>();

    private final MutableLiveData<List<MailAddress>> mailAddresses = new MutableLiveData<>();


    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public LiveData<Contact> getContact() {
        return contact;
    }

    public LiveData<List<MailAddress>> getMailAddresses() {
        return mailAddresses;
    }

    public LiveData<List<Phone>> getPhones() {
        return phones;
    }

    public void loadContacts() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Contact> contactsList = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject contact = response.getJSONObject(i);
                        String id = contact.getString("id");
                        String firstname = contact.getString("firstname");
                        String lastname = contact.getString("lastname");
                        Contact contact1 = new Contact(id, firstname, lastname, null, null, null);
                        contactsList.add(contact1);
                    }
                    contacts.setValue(contactsList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestVolley.getInstance().addToRequestQueue(request);
    }
    // load contact by id
    public void loadContactById(String id) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + "/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("id");
                    String firstname = response.getString("firstname");
                    String lastname = response.getString("lastname");
                    Contact contact = new Contact(id, firstname, lastname, null, null, null);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestVolley.getInstance().addToRequestQueue(request);
    }

    //load MailAddresses by id of contact
    public void loadMailAddressesById(String id) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/" + id + "/mailAddress", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<MailAddress> mailAddressesList = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject mailAddress = response.getJSONObject(i);
                        String mailAddress1 = mailAddress.getString("address");
                        String type = mailAddress.getString("label");
                        MailAddress mailAddress2 = new MailAddress(mailAddress1,type);
                        mailAddressesList.add(mailAddress2);
                    }
                    mailAddresses.setValue(mailAddressesList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestVolley.getInstance().addToRequestQueue(request);
    }

    //load phoneNumbers by id of contact
    public void loadPhoneNumbersById(String id) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/" + id + "/phone", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Phone> Phonelist = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject Phone = response.getJSONObject(i);
                        String number = Phone.getString("number");
                        String type = Phone.getString("label");
                        Phone phone = new Phone(number,type);
                        Log.d(TAG, "onResponse: "+number+" "+type);
                        Phonelist.add(phone);
                    }
                    phones.setValue(Phonelist);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestVolley.getInstance().addToRequestQueue(request);
    }

    //remove contact by id

    public void removeContactById(String id) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url + "/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                contacts.getValue().removeIf(contact -> contact.getId().equals(id));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestVolley.getInstance().addToRequestQueue(request);
    }










}
