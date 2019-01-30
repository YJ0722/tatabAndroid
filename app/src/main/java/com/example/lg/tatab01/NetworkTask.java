package com.example.lg.tatab01;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by a on 2018-11-19.
 */

public class NetworkTask extends AsyncTask<Void, Void, String> {

    // url 주소
    private String url;
    // 파라미터 값
    private ContentValues values;

    public NetworkTask(String url, ContentValues values) {

        this.url = url;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... params) {

        // 요청 결과를 저장할 변수.
        String result;

        // RequesetHttpURLConnection 에 sharedpreferencesCookie 설정
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();

        // 해당 URL로 부터 결과물을 얻어온다.
        result = requestHttpURLConnection.request(url, values);

        // 결과 반환 --> onPostExecute의 파라미터로 들어간다
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        // 파라미터 s는 doInbackground의 return 값과 동일
        super.onPostExecute(s);

        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
        Log.d("syj", "[MainActivity]" +  s);

        // AsyncTask를 통해 HttpURLConnection 수행.
        // url 설정 ( URLVO( mapping 할 url 주소 ) )
        URLVO urlvo = new URLVO("boardProjectList.do");

        NetworkTask2 networkTask2 = new NetworkTask2(urlvo.getUrl(), null);
        networkTask2.execute();
    }
}