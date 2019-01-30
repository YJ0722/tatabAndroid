package com.example.lg.tatab01.Column;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lg.tatab01.Activity.TaskPopup;
import com.example.lg.tatab01.Comment.BoardTaskCommentAsync;
import com.example.lg.tatab01.Comment.BoardTaskCommentVO;
import com.example.lg.tatab01.ProjectList.ProjectItem;
import com.example.lg.tatab01.R;
import com.example.lg.tatab01.Task.BoardTaskAsync;
import com.example.lg.tatab01.Task.BoardTaskVO;
import com.example.lg.tatab01.Task.TaskAdapter;
import com.example.lg.tatab01.Task.TaskItem;
import com.example.lg.tatab01.URLVO;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by LG on 2018-11-19.
 */

public class TaskFragment extends Fragment {

    private ListView taskListView;
    private List<BoardTaskVO> boardTaskList;
    private TaskAdapter myAdapter;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setBoardTaskList(List<BoardTaskVO> boardTaskList) {
        this.boardTaskList = boardTaskList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 위젯과 멤버변수 참조 획득
        View fragConstraint = inflater.inflate(R.layout.task_fragment, container, false);
        taskListView = fragConstraint.findViewById(R.id.taskListView);

        // 아이템 추가 및 어뎁터 등록
        dataSetting();

        return fragConstraint;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // TODO : TASK 리스트뷰 데이터 셋팅
    private void dataSetting() {


        for(int i=0; i<boardTaskList.size(); i++) {
            Log.d("syj", "[TaskFragment] 컬럼 목록 전달 확인: " + boardTaskList.get(i).toString());
        }

        myAdapter = new TaskAdapter();

        for (int i = 0; i <boardTaskList.size(); i++) {
            myAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.aircraft), boardTaskList.get(i).getTask_name());
        }

        // 리스트뷰에 어뎁터 등록
        taskListView.setAdapter(myAdapter);

        // 클릭시 task pop
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BoardTaskVO boardTaskVO = boardTaskList.get(position);
                Log.d("syj", "[TaskFragment] 클릭 아이템의 태스크 정보 확인 : " + boardTaskVO.toString());
                Log.d("syj" , "[TaskFragment] 클릭 데이터 확인 : " + boardTaskVO.toString());

                // TODO
                URLVO urlvo = new URLVO("mTaskCommentList.do");

                // 전달할 파라미터 설정
                ContentValues contentValues = new ContentValues();
                Log.d("syj", "[TaskFragment] 전달할 TASKNO " + boardTaskVO.getTask_no());
                contentValues.put("task_no", boardTaskVO.getTask_no());

                BoardTaskCommentAsync boardTaskCommentAsync = new BoardTaskCommentAsync(urlvo.getUrl(), contentValues);
                boardTaskCommentAsync.setTask_no(boardTaskVO.getTask_no());
                boardTaskCommentAsync.setContext(context);

                List<BoardTaskCommentVO> boardTaskCommentVOList = null;
                try {
                    boardTaskCommentVOList = boardTaskCommentAsync.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(boardTaskCommentVOList == null) {
                    Log.d("syj", "comment리스트 null");
                } else {
                    for(int i=0; i<boardTaskCommentVOList.size(); i++) {
                        Log.d("syj", "[TaskFragment] comment[" + i + "] : " + boardTaskCommentVOList.get(i).toString());
                    }
                }


                Intent intent = new Intent(getActivity(), TaskPopup.class);
                intent.putExtra("boardTaskVO", boardTaskVO);
                intent.putExtra("boardTaskCommentVOList", (Serializable) boardTaskCommentVOList);

                startActivity(intent);
            }
        });
    }
}
