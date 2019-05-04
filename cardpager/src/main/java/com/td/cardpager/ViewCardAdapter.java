package com.td.cardpager;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


/**
 * Created by office on 2018/3/21.
 */

public class ViewCardAdapter extends RecyclerView.Adapter<ViewCardAdapter.RecyclerHolder> {
    private Context context;
    private FrameLayout.LayoutParams layoutParams;
    private int width = 0, height = 0;
    private TimeInterpolator timeInterpolator;
    private RequestOptions options;
    private int index = 0;
    private long time = 0;
    private int column = 0;
    //    private int[] images_two = {R.mipmap.icon_two, R.mipmap.icon_two_img2, R.mipmap.icon_two_img};
    private float scale = 0;
    private boolean canOut = true;
    private int layoutId;
    private onRecyclerItemClickerListener onRecyclerItemClickerListener;
    private List<String> lists;

    public ViewCardAdapter(Context context, int layoutId, List<String> lists, int width, int height, int column) {
        this.context = context;
        this.width = width;
        this.height = height;
        this.column = column;
        this.layoutId = layoutId;
        this.lists = lists;
        RoundedCorners roundedCorners = new RoundedCorners(Utils.dp2px(context, Config.corner));
        options = RequestOptions.bitmapTransform(roundedCorners).override(300, 200);
        layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.setMargins(Utils.dp2px(context, 5), 0, Utils.dp2px(context, 5), 0);
        timeInterpolator = new OvershootInterpolator();
        this.index = index;
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int i) {
        holder.fl_parent.setLayoutParams(layoutParams);
        holder.textView.setText(lists.get(i % lists.size()));
//            Glide.with(context).load(lists.get(i % lists.size()).getImage().get(0).getFileUrl()).apply(options).into(holder.iv_img);
        Utils.setFocusHighLight(holder.fl_parent, timeInterpolator, true);
        holder.fl_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerItemClickerListener != null) {
                    onRecyclerItemClickerListener.onRecyclerItemClick(v, i % lists.size(), lists);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        ScrollTextView textView;
        ImageView iv_img;
        FrameLayout fl_parent;

        private RecyclerHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_text);
            iv_img = itemView.findViewById(R.id.iv_img);
            fl_parent = itemView.findViewById(R.id.fl_parent);
        }
    }

    /**
     * 点击监听回调接口
     */
    public interface onRecyclerItemClickerListener {
        void onRecyclerItemClick(View view, int position, List<String> list);
    }

    /**
     * 增加点击监听
     */
    public void setItemListener(onRecyclerItemClickerListener mListener) {
        this.onRecyclerItemClickerListener = mListener;
    }
}
