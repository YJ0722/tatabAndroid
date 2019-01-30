package com.example.lg.tatab01.Column;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lg.tatab01.Activity.ColActivity;
import com.example.lg.tatab01.RequestHttpURLConnection;
import com.example.lg.tatab01.SharedpreferencesCookie;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by a on 2018-11-20.
 */

public class BoardColAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;

    private int project_no;

    public void setProject_no(int project_no) {
        this.project_no = project_no;
    }

    // intent 전달에 사용할 context 설정 필요 -> Aynsc 호출한 Activity에서 setActivity() 호출하여 설정
    private Context context;

    public void setActivityData(Context context) {
        this.context = context;
    }

    // cookie 저장할 sharedPreferences
    SharedpreferencesCookie sharedpreferencesCookie = SharedpreferencesCookie.getInstance();

    public BoardColAsync(String url, ContentValues values) {
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
        Log.d("syj", "[BoardColAsync] " + s);

        Gson gson = new Gson();

        // json 객체를 vo 객체 배열로 변환
        BoardColVO[] boardColVOArr = gson.fromJson(s, BoardColVO[].class);
        // vo 객체 배열을 list로 변환
        List<BoardColVO> boardColVOList = Arrays.asList(boardColVOArr);

        Log.d("syj", "[BoardColAsync] 파싱 결과");
        for(int i=0; i<boardColVOList.size(); i++) {
            Log.d("syj", "[" + i + "] : " + boardColVOList.get(i));
        }


        // 선택한 프로젝트 아이템 정보 TaskActivity에 전달
        Intent intent = new Intent(context, ColActivity.class);
        intent.putExtra("project_no", project_no);
        intent.putExtra("boardColList", (Serializable) boardColVOList);

        Log.d("syj", "[BoardColAsync] onPostExecute 종료!");
        context.startActivity(intent);

    }


}
