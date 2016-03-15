package com.ostmodern.videodemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ostmodern.videodemo.data.Video;

/**
 * Created by philip.arnold on 15/03/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder>{
    LayoutInflater inflater;
    Video[] array;
    Context context;

    public MainAdapter(Context context, Video[] array) {
        inflater = LayoutInflater.from(context);
        this.array = array;
        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Video video = array[position];
        holder.title.setText(video.getTitle());
        holder.summary.setText(video.getSummary());
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView summary;

        public Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            summary = (TextView) itemView.findViewById(R.id.summary);
        }
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

}
