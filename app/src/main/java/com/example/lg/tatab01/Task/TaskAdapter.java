package com.example.lg.tatab01.Task;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lg.tatab01.R;

import java.util.ArrayList;

/**
 * Created by LG on 2018-11-19.
 */

public class TaskAdapter extends BaseAdapter {

    private ArrayList<TaskItem> projectItems = new ArrayList<>();

    @Override
    public int getCount() {
        return projectItems.size();
    }

    @Override
    public TaskItem getItem(int position) {
        return projectItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.projectlist, parent, false);
        }

        /* 'projectlist'에 정의된 위젯에 대한 참조 획득 */
        ImageView project_img = (ImageView) convertView.findViewById(R.id.project_img) ;
        TextView project_name = (TextView) convertView.findViewById(R.id.project_name) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 projectItem 재활용 */
        TaskItem projectItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        project_img.setImageDrawable(projectItem.getIcon());
        project_name.setText(projectItem.getName());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Drawable img, String name) {

        TaskItem projectItem = new TaskItem();

        /* MyItem에 아이템을 setting한다. */
        projectItem.setIcon(img);
        projectItem.setName(name);

        /* mItems에 MyItem을 추가한다. */
        projectItems.add(projectItem);

    }
}
