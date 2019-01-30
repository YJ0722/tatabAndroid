package com.example.lg.tatab01.ProjectList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;

import com.example.lg.tatab01.Activity.ProjectListActivity;
import com.example.lg.tatab01.NetworkTask2;
import com.example.lg.tatab01.RequestHttpURLConnection;
import com.example.lg.tatab01.SharedpreferencesCookie;
import com.example.lg.tatab01.URLVO;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by a on 2018-11-19.
 */

public class ProjectAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;

    // intent 전달에 사용할 context 설정 필요 -> Aynsc 호출한 Activity에서 setActivity() 호출하여 설정
    private Context context;
    private String email;

    public void setActivityData(Context context, String email) {
        this.context = context;
        this.email = email;
    }

    // cookie 저장할 sharedPreferences
    SharedpreferencesCookie sharedpreferencesCookie = SharedpreferencesCookie.getInstance();

    public ProjectAsync(String url, ContentValues values) {

        this.url = url;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... params) {
        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();

        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
        Log.d("syj", "[ProjectAsync] " + s);

        Gson gson = new Gson();

        // json 객체를 vo 객체 배열로 변환
        ProjectVO[] projectVOArr = gson.fromJson(s, ProjectVO[].class);
        // vo 객체 배열을 list로 변환
        List<ProjectVO> projectVOList = Arrays.asList(projectVOArr);

        Log.d("syj", "[ProjectAsync] 파싱 결과");
        for(int i=0; i<projectVOList.size(); i++) {
            Log.d("syj", "[" + i + "] : " + projectVOList.get(i));
        }

        Intent intent = new Intent(context, ProjectListActivity.class);
        intent.putExtra("mEmail", email);
        intent.putExtra("projectVOList", (Serializable) projectVOList);

        Log.d("syj", "[ProjectAsync] onPostExecute 종료!");

        context.startActivity(intent);

    }
}
