package com.myhexaville.androidtests.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myhexaville.androidtests.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.Holder> {
    private List<String> list;

    public MessagesAdapter() {
    }

    public MessagesAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messages_list_item, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String message = list.get(position);
        holder.text.setText(message);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView text;

        Holder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }

}
