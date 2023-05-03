package DBConfig;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestVolley {

    private static RequestVolley instance;
    private static Context ctx;
    private RequestQueue requestQueue;

    public RequestVolley(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized RequestVolley getInstance(Context context) {
        if (instance == null) {
            instance = new RequestVolley(context.getApplicationContext());
        }
        return instance;
    }

    public static synchronized RequestVolley getInstance() {
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req){

        requestQueue.add(req);

    }
}
