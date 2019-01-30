package com.example.lg.tatab01;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by a on 2018-11-19.
 */

public class NetworkTask2 extends AsyncTask<Void, Void, String>  {

    private String url;
    private ContentValues values;

    // cookie 저장할 sharedPreferences
    SharedpreferencesCookie sharedpreferencesCookie = SharedpreferencesCookie.getInstance();

    public NetworkTask2(String url, ContentValues values) {

        this.url = url;
        this.values = values;
    }
    @Override
    protected String doInBackground(Void... params) {
        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();

        result = requestHttpURLConnection.request(url, null); // 해당 URL로 부터 결과물을 얻어온다.

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
        Log.d("syj2", "[NetworkTask2]" + s);
    }
}