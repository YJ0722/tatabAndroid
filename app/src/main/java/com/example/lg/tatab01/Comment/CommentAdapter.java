package com.example.lg.tatab01.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lg.tatab01.R;

import java.util.ArrayList;

/**
 * Created by LG on 2018-11-19.
 */

public class CommentAdapter extends BaseAdapter {

    private ArrayList<CommentItem> commentItems = new ArrayList<>();

    @Override
    public int getCount() {
        return commentItems.size();
    }

    @Override
    public CommentItem getItem(int position) {
        return commentItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'task_info_list' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_info_list, parent, false);
        }

        /* 'projectlist'에 정의된 위젯에 대한 참조 획득 */
        TextView comment_name = (TextView) convertView.findViewById(R.id.comment_name);
        TextView comment = (TextView) convertView.findViewById(R.id.comment) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 commentItem 재활용 */
        CommentItem commentItem  = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        comment_name.setText(commentItem.getComment_name());
        comment.setText(commentItem.getComment());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String comment_name, String comment) {

        CommentItem commentItem = new CommentItem();

        /* MyItem에 아이템을 setting한다. */
        commentItem.setComment_name(comment_name);
        commentItem.setComment(comment);

        /* mItems에 MyItem을 추가한다. */
        commentItems.add(commentItem);

    }
}