package com.example.lg.tatab01;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lg.tatab01.Column.BoardColAsync;
import com.example.lg.tatab01.Task.BoardTaskAsync;

public class MainActivity extends AppCompatActivity {

    // cookie 저장할 sharedPreferences 선언
    SharedpreferencesCookie sharedpreferencesCookie = SharedpreferencesCookie.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("syj", "[MainActivity] onCreate() 시작!");

        URLVO urlvo = new URLVO("mBoardTaskList.do");

        // 전달할 파라미터 설정
        ContentValues contentValues = new ContentValues();
        contentValues.put("project_no", "17");
        contentValues.put("col_no", "4");

        ////////////////
        BoardTaskAsync boardTaskAsync = new BoardTaskAsync(urlvo.getUrl(), contentValues);
        boardTaskAsync.setContext(getApplicationContext());
        boardTaskAsync.execute();

        /*
        // SharedPrefernces 에 context 객체 설정
        sharedpreferencesCookie.setContext(getApplicationContext());

        // url 설정 ( URLVO( mapping 할 url 주소 ) )
        URLVO urlvo = new URLVO("yyj.do");

        // 첫번째 task 수행
        NetworkTask networkTask = new NetworkTask(urlvo.getUrl(), null);
        networkTask.execute();
        */
    }

}