package com.myhexaville.androidtests.chat;

public class ChatPresenter implements ChatContract.Presenter {
    private ChatContract.View chatView;

    public ChatPresenter(ChatContract.View chatView) {
        this.chatView = chatView;
    }

    public void sendMessage(String message) {
        if (message != null && !message.isEmpty()) {
            chatView.addMessage(message);
        }
    }

    @Override
    public void messageInputTextChanged(String messageInput) {

    }
}
