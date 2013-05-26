/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.test.service;

import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.User;
import org.patientview.service.MessageManager;
import org.patientview.test.helpers.SecurityHelpers;
import org.patientview.test.helpers.ServiceHelpers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MessageManagerTest extends BaseServiceTest {

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private MessageManager messageManager;

    private User user;

    @Before
    public void setupSystem() {
        // create an admin adminUser and specialty and log them in
        user = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        Specialty specialty = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        serviceHelpers.createSpecialtyUserRole(specialty, user, "unitadmin");

        securityHelpers.loginAsUser(user.getUsername(), specialty);
    }

    @Test
    public void testGetConversation() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation("Test subject", user, user2, true);

        assertTrue("Invalid id for message", conversation.getId() > 0);

        Conversation checkConversation = messageManager.getConversation(conversation.getId());
        assertNotNull("Conversation nout found", checkConversation);
    }

    @Test
    public void testDeleteConversation() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation("Test subject", user, user2, true);

        // now delete and try to pull back
        messageManager.deleteConversation(conversation);

        Conversation checkConversation = messageManager.getConversation(conversation.getId());

        assertNull("Conversation was found after being deleted", checkConversation);
    }

    @Test
    public void testDeleteConversationById() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation("Test subject", user, user2, true);

        // now delete and try to pull back
        messageManager.deleteConversation(conversation.getId());

        Conversation checkConversation = messageManager.getConversation(conversation.getId());

        assertNull("Conversation was found after being deleted", checkConversation);
    }

    @Test
    public void testCreateMessage() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();

        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        Message message = messageManager.createMessage(mockHttpSession.getServletContext(), "Test subject",
                "This is my first message", user, user2);

        assertTrue("Invalid id for message", message.getId() > 0);

        // now try and pull back conversations for both users - both should have 1 conversation
        List<Conversation> checkUser1Conversations = messageManager.getConversations(user.getId());
        assertEquals("Wrong number of conversations for user 1", checkUser1Conversations.size(), 1);

        List<Conversation> checkUser2Conversations = messageManager.getConversations(user2.getId());
        assertEquals("Wrong number of conversations for user 2", checkUser2Conversations.size(), 1);

        // now pull back the messages for a conversation to see if the message was actually saved
        List<Message> checkMessages = messageManager.getMessages(checkUser1Conversations.get(0).getId());
        assertEquals("Wrong number of messages for conversation", checkMessages.size(), 1);
    }

    @Test
    public void testReplyToMessage() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();

        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        // create a message that we can reply to
        Message message = messageManager.createMessage(mockHttpSession.getServletContext(), "Test subject",
                "This is my first message", user, user2);

        Message replyMessage = messageManager.replyToMessage(mockHttpSession.getServletContext(),
                "This is my first message",
                message.getConversation().getId(), user2);

        // the conversatino assigned to the message should be the same as the one created above
        assertEquals("Wrong conversation stored", message.getConversation(), replyMessage.getConversation());
    }

    @Test
    public void testMarkMessagesAsReadForConversation() throws Exception {
        /**
         * Send a message from user 1 to user 2
         *
         * Check how many unread messages they have
         *
         * Then mark all messages as read for user 2 in the conversation
         *
         * Then check how many unread messages they have again
         */
        MockHttpSession mockHttpSession = new MockHttpSession();

        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        messageManager.createMessage(mockHttpSession.getServletContext(), "Test subject", "This is my first message",
                user, user2);

        // now pull abck conversation for user 2
        List<Conversation> checkUser2Conversations = messageManager.getConversations(user2.getId());

        // check number of unread messages for only convo
        assertEquals("Wrong number of unread messages for conversation",
                checkUser2Conversations.get(0).getNumberUnread(), 1);

        // now mark as unread
        messageManager.markMessagesAsReadForConversation(user2.getId(), checkUser2Conversations.get(0).getId());

        // now pull back again
        List<Conversation> checkUser2ConversationsAgain = messageManager.getConversations(user2.getId());

        // check number of unread messages again
        assertEquals("Wrong number of unread messages for conversation",
                checkUser2ConversationsAgain.get(0).getNumberUnread(), 0);
    }

    @Test
    public void testGetTotalNumberUnreadMessages() throws Exception {
        /**
         * Create 3 users
         *
         * Conversation between user 1 and 2
         * Conversation between user 1 and 3
         *
         * Then send messages in conversation 1 from user 2 to 1
         *
         * and
         *
         * messages in conversation 1 from user 3 to 1
         *
         * User 1 should then have 2 unread messages across conversations
          */
        MockHttpSession mockHttpSession = new MockHttpSession();

        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        User user3 = serviceHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3");

        // first convo with message from 2 to 1
        messageManager.createMessage(mockHttpSession.getServletContext(), "Test subject", "This is my first message",
                user2, user);

        // second convo with message from 3 to 1
        messageManager.createMessage(mockHttpSession.getServletContext(), "Test subject", "This is my first message",
                user3, user);

        // now pull back and check unread messages for user 1
        int checkNumberUnreadMessages = messageManager.getTotalNumberUnreadMessages(user.getId());
        assertEquals("Wrong number of unread messages", checkNumberUnreadMessages, 2);
    }
}