package com.example.lg.tatab01.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lg.tatab01.Comment.BoardTaskCommentVO;
import com.example.lg.tatab01.Comment.CommentAdapter;
import com.example.lg.tatab01.R;
import com.example.lg.tatab01.Task.BoardTaskVO;

import java.util.List;

/**
 * Created by LG on 2018-11-20.
 */

public class TaskPopup extends Activity {

    private ListView commentListView;
    private TextView taskTitle;
    private TextView taskContent;
    private TextView taskDday;
    private Button taskCloseBtn;

    BoardTaskVO  boardTaskVO;
    List<BoardTaskCommentVO> boardTaskCommentVOList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_taskpopup);

        Intent intent = getIntent();
        boardTaskVO = (BoardTaskVO) intent.getSerializableExtra("boardTaskVO");
        boardTaskCommentVOList = (List<BoardTaskCommentVO>) intent.getSerializableExtra("boardTaskCommentVOList");

        for(int i=0; i<boardTaskCommentVOList.size(); i++) {
            Log.d("syj", "[TaskPopup] comment[" + i + "] : " + boardTaskCommentVOList.get(i).toString());
        }

        Log.d("syj", "[TaskPopup] 클릭 아이템의 인텐트 전달 정보 확인 : " + boardTaskVO.toString());

        // 제목, 내용, 디데이 뷰에 텍스트 설정
        initView();
        setDataView(boardTaskVO.getTask_name(), boardTaskVO.getTask_content(), boardTaskVO.getD_day());

        commentListView = (ListView) findViewById(R.id.commentList);

        dataSetting();

        taskCloseBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 종료
                finish();
            }
        });
    }

    private void initView() {
        taskTitle = findViewById(R.id.taskTitle);
        taskContent = findViewById(R.id.taskContent);
        taskDday = findViewById(R.id.taskDday);
        taskCloseBtn = findViewById(R.id.taskCloseBtn);
    }

    private void setDataView(String title, String content, String dday) {
        taskTitle.setText(title);
        taskContent.setText(content);
        taskDday.setText(dday);
    }
    private void dataSetting() {

        CommentAdapter commentAdapter = new CommentAdapter();

        for (int i = 0; i < boardTaskCommentVOList.size(); i++) {
            commentAdapter.addItem(boardTaskCommentVOList.get(i).getLogin_name(), boardTaskCommentVOList.get(i).getTask_comment());
        }

        commentListView.setAdapter(commentAdapter);

    }


}