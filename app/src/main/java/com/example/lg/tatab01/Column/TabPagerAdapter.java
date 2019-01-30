package com.example.lg.tatab01.Column;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lg.tatab01.Task.BoardTaskAsync;
import com.example.lg.tatab01.Task.BoardTaskVO;
import com.example.lg.tatab01.URLVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by LG on 2018-11-19.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private List<BoardColVO> boardColList;
    private List<BoardTaskVO> boardTaskList;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setBoardColList(List<BoardColVO> boardColList) {
        this.boardColList = boardColList;
    }

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {


        // TODO
        URLVO urlvo = new URLVO("mBoardTaskList.do");

        // 전달할 파라미터 설정
        ContentValues contentValues = new ContentValues();
        contentValues.put("project_no", boardColList.get(position).getProject_no());
        contentValues.put("col_no", boardColList.get(position).getCol_no());

        BoardTaskAsync boardTaskAsync = new BoardTaskAsync(urlvo.getUrl(), contentValues);
        boardTaskAsync.setContext(context);
        try {
            boardTaskList = boardTaskAsync.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        // 탭페이지에 띄울 프래그먼트 설정
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setContext(context);
        taskFragment.setBoardTaskList(boardTaskList);
        return taskFragment;

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
