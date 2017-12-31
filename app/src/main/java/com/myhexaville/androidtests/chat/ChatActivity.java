package com.myhexaville.androidtests.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.myhexaville.androidtests.R;

import java.util.List;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private ChatContract.Presenter presenter;
    private MessagesAdapter adapter;
    private List<String> listOfMessages;
    private EditText messageInput;
    private ImageView sendButton;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);
        recyclerView = findViewById(R.id.list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.messageInputTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sendButton.setOnClickListener(v -> presenter.sendMessage(messageInput.getText().toString()));
    }

    @Override
    public void notifyItemAdded(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void clearMessageInput() {
        messageInput.setText("");
    }

    @Override
    public void enableSendButton() {
        if (!sendButton.isEnabled()) {
            sendButton.setEnabled(true);
            sendButton.setAlpha(1.0f);
        }
    }

    @Override
    public void disableSendButton() {
        if (sendButton.isEnabled()) {
            sendButton.setEnabled(false);
            sendButton.setAlpha(0.5f);
        }
    }

    public void setPresenter(ChatContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setAdapter(MessagesAdapter adapter) {
        this.adapter = adapter;
    }

    public void setListOfMessages(List<String> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }
}
