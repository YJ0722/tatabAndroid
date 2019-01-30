package com.example.lg.tatab01.Task;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lg.tatab01.Column.TaskFragment;
import com.example.lg.tatab01.RequestHttpURLConnection;
import com.example.lg.tatab01.SharedpreferencesCookie;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by a on 2018-11-20.
 */

public class BoardTaskAsync extends AsyncTask<Void, Void, List> {

    private String url;
    private ContentValues values;
    private int task_no;

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    // intent 전달에 사용할 context 설정 필요 -> Aynsc 호출한 Activity에서 setActivity() 호출하여 설정
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    // cookie 저장할 sharedPreferences
    SharedpreferencesCookie sharedpreferencesCookie = SharedpreferencesCookie.getInstance();

    public BoardTaskAsync(String url, ContentValues values) {
        this.url = url;
        this.values = values;
    }

    @Override
    protected List<BoardTaskVO> doInBackground(Void... params) {
        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();

        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
        Log.d("syj", "[BoardTaskAsync] " + result);

        Gson gson = new Gson();

        // json 객체를 vo 객체 배열로 변환
        BoardTaskVO[] boardTaskVOArr = gson.fromJson(result, BoardTaskVO[].class);
        // vo 객체 배열을 list로 변환
        List<BoardTaskVO> boardTaskVOList = Arrays.asList(boardTaskVOArr);

        Log.d("syj", "[BoardTaskAsync] 파싱 결과");
        for(int i=0; i<boardTaskVOList.size(); i++) {
            Log.d("syj", "[" + i + "] : " + boardTaskVOList.get(i));
        }
        return boardTaskVOList;
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
    }
}
