package com.myhexaville.androidtests.chat;

import java.util.List;

public class ChatPresenter implements ChatContract.Presenter {
    private ChatContract.View view;
    private List<String> listOfMessages;

    public ChatPresenter(ChatContract.View view, List<String> listOfMessages) {
        this.view = view;
        this.listOfMessages = listOfMessages;
    }

    public void sendMessage(String message) {
        if (message != null && !message.isEmpty()) {
            listOfMessages.add(message);
            view.notifyItemAdded(listOfMessages.size());
            view.clearMessageInput();
        }
    }

    @Override
    public void messageInputTextChanged(String messageInput) {
        if (messageInput == null || messageInput.isEmpty()) {
            view.disableSendButton();
        } else {
            view.enableSendButton();
        }
    }
}
