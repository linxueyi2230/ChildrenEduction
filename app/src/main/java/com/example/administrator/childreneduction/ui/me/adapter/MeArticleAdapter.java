package com.example.administrator.childreneduction.ui.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.childreneduction.R;
import com.example.administrator.childreneduction.bmob.ArticleTable;
import com.example.administrator.childreneduction.ui.listener.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MeArticleAdapter extends RecyclerView.Adapter<MeArticleAdapter.CViewHolder> implements View.OnClickListener {
    private Context mContext;
    private OnClickListener mOnClickListener;
    private List<ArticleTable> mList;

    public MeArticleAdapter(Context mContext){
        this.mContext=mContext;
//        this.mList=list;
        mList=new ArrayList<>();
    }

    /**
     * 加载更多
     * @param list
     */
    public void setAddData(List<ArticleTable> list){
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void refresh(List<ArticleTable> list){
        if (mList!=null){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public List<ArticleTable> getList() {
        return mList;
    }

    @Override
    public CViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.adapter_articlecoll, parent, false);
        inflate.setOnClickListener(this);
        return new CViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CViewHolder holder, int position) {
        holder.itemView.setTag(position);
        ArticleTable ua_table = mList.get(position);
        holder.mTvAdapterArticollTile.setText(ua_table.getA_title());
        holder.mTvAdapterArticollUser.setText(ua_table.getU_name());
        holder.mTvAdapterArticollTime.setText(ua_table.getCreatedAt());
        holder.mTvAdaArtLabel.setText("标签："+ua_table.getA_label());
        if (ua_table.getU_url()!=null){
            Glide.with(mContext)
                    .load(ua_table.getU_url())
                    .into(holder.mTvAdaArtcollHead);
        }
    }

    @Override
    public int getItemCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener!=null){
            mOnClickListener.setOnClickListener(v, (Integer) v.getTag());
        }
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.mOnClickListener=onClickListener;
    }

    class CViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvAdapterArticollUser;
        private TextView mTvAdapterArticollTime;
        private TextView mTvAdapterArticollTile;
        private CircleImageView mTvAdaArtcollHead;
        private TextView mTvAdaArtLabel;

        public CViewHolder(View itemView) {
            super(itemView);
            mTvAdapterArticollUser = (TextView) itemView.findViewById(R.id.tv_adapter_articoll_user);
            mTvAdapterArticollTime = (TextView) itemView.findViewById(R.id.tv_adapter_articoll_time);
            mTvAdapterArticollTile = (TextView) itemView.findViewById(R.id.tv_adapter_articoll_tile);
            mTvAdaArtcollHead = (CircleImageView) itemView.findViewById(R.id.tv_ada_artcoll_head);
            mTvAdaArtLabel = (TextView) itemView.findViewById(R.id.tv_ada_art_label);

        }
    }
}
