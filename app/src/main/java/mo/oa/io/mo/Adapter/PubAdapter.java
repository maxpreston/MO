package mo.oa.io.mo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Entities.NoticeEntity;
import mo.oa.io.mo.Entities.PubEntity;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Widget.CircleImageView;

/**
 * Created by max-code on 2016/10/15.
 */

public class PubAdapter extends RecyclerView.Adapter {
    private List<NoticeEntity> list = new ArrayList<>();
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setItems(List<NoticeEntity> pubEntityList){
        this.list = pubEntityList;
        notifyItemRangeChanged(0,list.size());
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PubViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ggl_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final PubViewHolder pubViewHolder = (PubViewHolder) holder;
        pubViewHolder.detail.setText(list.get(position).getNoticecontent());
        pubViewHolder.title.setText(list.get(position).getNoticeTitle());
        pubViewHolder.littledetail.setText(list.get(position).getNoticecontent());
        pubViewHolder.pubman.setText(list.get(position).getUsername());
        pubViewHolder.time.setText(list.get(position).getNoticeTime());
        pubViewHolder.headicon.setImageResource(R.drawable.yysp);
        if(clickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.OnItemClickListener(pubViewHolder.getLayoutPosition(),pubViewHolder.itemView);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickListener.OnItemLongClickListener(pubViewHolder.getLayoutPosition(),pubViewHolder.itemView);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class PubViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.gg_pub_time)
        TextView time;
        @Bind(R.id.ggl_list_detail)
        TextView detail;
        @Bind(R.id.gg_list_content)
        TextView littledetail;
        @Bind(R.id.gg_title)
        TextView title;
        @Bind(R.id.ggl_list_facepic)
        ImageView facepic;
        @Bind(R.id.gg_arr_down)
        ImageView img;
        @Bind(R.id.gg_pub_img)
        CircleImageView headicon;
        @Bind(R.id.ggl_item_ifread)
        TextView ifread;
        @Bind(R.id.gg_pub_name)
        TextView pubman;

        public PubViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface ClickListener{
        public void OnItemClickListener(int position,View view);
        public void OnItemLongClickListener(int position,View view);
    }
}
