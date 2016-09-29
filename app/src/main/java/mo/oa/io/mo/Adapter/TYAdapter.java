package mo.oa.io.mo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Entities.MessageEntitys;
import mo.oa.io.mo.R;

/**
 * Created by max-code on 2016/9/25.
 */

public class TYAdapter<T> extends RecyclerView.Adapter {
    protected ClickListener clickListener;
    protected List<MessageEntitys> list;
    protected Context context;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MsgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.timetree_lv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        MsgViewHolder h = (MsgViewHolder) holder;
        h.msg_title.setText(list.get(position).msgVo.getMsgTitle());
        h.msg_detail.setText(list.get(position).msgVo.getMsgContent());
        h.msg_time.setText(list.get(position).msgVo.getMsgTime());

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener!=null){
                     clickListener.OnItemClickListener(holder.getLayoutPosition(),view);
                }
            }
        });
        //长按点击
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(clickListener!=null){
                    clickListener.OnItemLongClickListener(holder.getLayoutPosition(),view);
                }
                return true;
            }
        });
    }

    //设置list
    public void setItems(List<MessageEntitys> l){
        this.list = l;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list==null||list.size()==0?0:list.size();
    }

    interface ClickListener{
        public void OnItemClickListener(int position,View view);
        public void OnItemLongClickListener(int position,View view);
    }

    class MsgViewHolder extends RecyclerView.ViewHolder{

        //消息title
        @Bind(R.id.msg_title)
        TextView msg_title;
        //消息详情
        @Bind(R.id.msg_detail)
        TextView msg_detail;
        //消息时间
        @Bind(R.id.msg_timetext)
        TextView msg_time;
        //是否已读
        @Bind(R.id.msg_isread_flag)
        ImageView msg_isread;
        //用户头像
        @Bind(R.id.msg_user_icon)
        ImageView msg_user_icon;


        public MsgViewHolder(View itemView) {
            super(itemView);
            //绑定视图
            ButterKnife.bind(this,itemView);

        }
    }
}
