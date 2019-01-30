package com.example.lg.tatab01.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lg.tatab01.Column.BoardColAsync;
import com.example.lg.tatab01.Column.BoardColVO;
import com.example.lg.tatab01.ProjectList.ProjectAdapter;
import com.example.lg.tatab01.ProjectList.ProjectAsync;
import com.example.lg.tatab01.ProjectList.ProjectItem;
import com.example.lg.tatab01.ProjectList.ProjectVO;
import com.example.lg.tatab01.R;
import com.example.lg.tatab01.URLVO;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by LG on 2018-11-19.
 */

public class ProjectListActivity extends AppCompatActivity {

    private Activity activity;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private FirebaseAuth mAuth;
    private ProjectAdapter myAdapter;

    private ListView projectListView;
    private List<ProjectVO> projectVOList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d("syj", "[ProjectListActivity] onCreate 시작!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectlist);


        Intent intent = getIntent();
        projectVOList = (List<ProjectVO>) intent.getSerializableExtra("projectVOList");

        for(int i=0; i<projectVOList.size(); i++) {

            Log.d("syj", "[ProjectListActivity] [" + i + "] : " + projectVOList.get(i) );

        }

        // 위젯과 멤버변수 참조 획득
        projectListView = (ListView) findViewById(R.id.projectListView);

        // 아이템 추가 및 어뎁터 등록
        dataSetting();

        /*
        // TODO
        URLVO urlvo = new URLVO("mBoardColList.do");

        // 전달할 파라미터 설정
        ContentValues contentValues = new ContentValues();
        contentValues.put("project_no", "17");

        BoardColAsync boardColAsync = new BoardColAsync(urlvo.getUrl(), contentValues);
        boardColAsync.setContext(getApplicationContext());
//        boardColAsync.execute();
*/
        ////////////////
    }

    private void dataSetting() {

        final ProjectAdapter myAdapter = new ProjectAdapter();

        for(int i=0; i<projectVOList.size(); i++) {
            myAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.aircraft), projectVOList.get(i).getProject_no(), projectVOList.get(i).getProject_name());
        }

        // 리스트뷰에 어뎁터 등록
        projectListView.setAdapter(myAdapter);

        // 클릭시 ColActivity로 project_name, project_no intent
        projectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectItem item = myAdapter.getItem(position);
                Log.d("syj" , "[ProjectListActivity] 클릭 데이터 확인 : " + item.toString());


                // TODO
                // AsyncTask를 통해 HttpURLConnection 수행.
                // url 설정 ( URLVO( mapping 할 url 주소 ) )
                URLVO urlvo = new URLVO("mBoardColList.do");

                // 전달할 파라미터 설정
                ContentValues contentValues = new ContentValues();
//                contentValues.put("login_email", user.getEmail());
                contentValues.put("project_no", item.getProject_no());

                BoardColAsync boardColAsync = new BoardColAsync(urlvo.getUrl(), contentValues);
//                projectAsync.setActivityData(getApplicationContext(), user.getEmail());
                boardColAsync.setActivityData(getApplicationContext());
                boardColAsync.setProject_no(item.getProject_no());
                boardColAsync.execute();
                //

            }
        });
    }
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
//            activity.finish();
        } else {
            backPressedTime = tempTime;
//            Toast.makeText(this, "one more", Toast.LENGTH_SHORT).show();
        }
    }

}
