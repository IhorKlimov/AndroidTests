package com.myhexaville.androidtests.chat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChatPresenterTest {

    @Mock
    private ChatContract.View chatView;
    private ChatContract.Presenter chatPresenter;
    @Mock
    private List<String> listOfMessages;
    private static final String REGULAR_MESSAGE = "This is regular message";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        chatPresenter = new ChatPresenter(chatView, listOfMessages);
        when(listOfMessages.size()).thenReturn(0);
    }

    @Test
    public void sendMessage_NullInput_NoMessageSent() {
        chatPresenter.sendMessage(null);
        verify(chatView, never()).notifyItemAdded(anyInt());
        verify(listOfMessages, never()).add(anyString());
    }

    @Test
    public void sendMessage_EmptyInput_NoMessageSent() {
        chatPresenter.sendMessage("");
        verify(chatView, never()).notifyItemAdded(anyInt());
        verify(listOfMessages, never()).add(anyString());
    }

    @Test
    public void sendMessage_NormalInput_MessageSent() {
        chatPresenter.sendMessage(REGULAR_MESSAGE);
        verify(listOfMessages).add(REGULAR_MESSAGE);
        verify(chatView).notifyItemAdded(0);
        verify(chatView).clearMessageInput();
    }

    @Test
    public void sendMessage_NormalInputListNotEmpty_MessageSent() {
        when(listOfMessages.size()).thenReturn(5);
        chatPresenter.sendMessage(REGULAR_MESSAGE);
        verify(listOfMessages).add(REGULAR_MESSAGE);
        verify(chatView).notifyItemAdded(5);
        verify(chatView).clearMessageInput();
    }

    @Test
    public void messageInputTextChanged_NullString_DisabledSendButton() {
        chatPresenter.messageInputTextChanged(null);
        verify(chatView).disableSendButton();
    }

    @Test
    public void messageInputTextChanged_EmptyString_DisabledSendButton() {
        chatPresenter.messageInputTextChanged("");
        verify(chatView).disableSendButton();
    }

    @Test
    public void messageInputTextChanged_NormalString_EnabledSendButton() {
        chatPresenter.messageInputTextChanged(REGULAR_MESSAGE);
        verify(chatView).enableSendButton();
    }
}
