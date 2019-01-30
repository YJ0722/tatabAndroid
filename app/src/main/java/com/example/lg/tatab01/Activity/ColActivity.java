package com.example.lg.tatab01.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lg.tatab01.Column.BoardColVO;
import com.example.lg.tatab01.Column.TabPagerAdapter;
import com.example.lg.tatab01.ProjectList.ProjectItem;
import com.example.lg.tatab01.R;

import java.util.List;

/**
 * Created by LG on 2018-11-19.
 */

public class ColActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<BoardColVO> boardColList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_col);

        // adding toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // ProjectListActivity 에서 선택한 아이템의 정보 가져오기
        Intent intent = getIntent();
        int projectNo = intent.getIntExtra("project_no", 0);
        boardColList = (List<BoardColVO>) intent.getSerializableExtra("boardColList");

        Log.d("syj", "[ColActivity] : 전달 프로젝트 번호 확인 : " + String.valueOf(projectNo));
        for(int i=0; i<boardColList.size(); i++) {
            Log.d("syj", "[ColActivity] : " + String.valueOf(boardColList.get(i).toString()));
        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        // 컬럼 수 만큼 탭 이름 설정
        for(BoardColVO boardColVO: boardColList) {
            tabLayout.addTab(tabLayout.newTab().setText(boardColVO.getCol_name()));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // initalizing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        // creating tabpagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        // adapter에 컬럼 목록 전달
        pagerAdapter.setBoardColList(boardColList);
        // adatper에 context 전달
        pagerAdapter.setContext(getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // set tabselectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
