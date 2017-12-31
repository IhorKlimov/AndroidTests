package com.myhexaville.androidtests.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.Holder> {
    private List<String > list;

    public MessagesAdapter() {
    }

    public MessagesAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(null);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

}
