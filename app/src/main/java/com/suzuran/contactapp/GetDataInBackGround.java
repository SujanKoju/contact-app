package com.suzuran.contactapp;

import android.os.AsyncTask;
import android.util.Log;

public class GetDataInBackGround extends AsyncTask<String, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("SUJAN", "-- BACKGROUND TASK ACTIVATED  -- IN PRE STATE ");

    }

    @Override
    protected String doInBackground(String... strings) {
        // TODO CALL API TO GET LIKE PROFILE OF USER AND SAVE IT IN SQLITE

        Log.d("SUJAN", "-- BACKGROUND TASK ACTIVATED  -- IN BACKGROUND " + strings[0]);

/*
        RequestQueue requestQueue = Volley.newRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/2",
                null, response -> {
        }, error -> {
        });
        requestQueue.add(jsonObjectRequest);*/
        return strings[0];
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("SUJAN", "-- BACKGROUND TASK ACTIVATED  -- IN POST STATE " + s);
    }
}
