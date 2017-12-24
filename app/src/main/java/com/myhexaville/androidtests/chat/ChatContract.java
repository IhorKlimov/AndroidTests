package com.myhexaville.androidtests.chat;

public interface ChatContract {
    interface View {
        void addMessage(String message);

        void clearMessageInput();

        void enableSendButton();

        void disableSendButton();
    }

    interface Presenter {
        void sendMessage(String message);

        void messageInputTextChanged(String messageInput);
    }

}
