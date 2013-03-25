package com.worthsoln.service;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.User;

import java.util.List;

public interface MessageManager {

    Conversation getConversation(Long conversationId);

    /**
     * This will get the conversation but applied to the current user
     * So will include number of number of unread messages etc and other users will be set
     * @param conversationId Long
     * @param participantId Long
     * @return Conversation
     */
    Conversation getConversationForUser(Long conversationId, Long participantId);

    List<Conversation> getConversations(Long participantId);

    void deleteConversation(Long conversationId);

    void deleteConversation(Conversation conversation);

    List<Message> getMessages(Long conversationId);

    Message createMessage(String subject, String content, User sender, User recipient) throws Exception;

    Message replyToMessage(String content, Long conversationId, User sender) throws Exception;

    int getTotalNumberUnreadMessages(Long recipientId);

    void markMessagesAsReadForConversation(Long recipientId, Long conversationId);
}