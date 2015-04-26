package stumeets.stumeets.ChatData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import stumeets.stumeets.ChatData.MessageData.Message;

/**
 * Created by ngoctranfire on 4/9/15.
 */

/**
 * This class is the object representation of a Chat, which includes the dateCreated. The date created
 * is a map, which is necessary for Firebase to represent the date of the object and take care of the translation
 * from system to system and display it to the user correctly.
 * The chat also has a unique chatId, which is used to uniquely identify the chat.
 * The chat has a list of chatMessages. That contains a list of message objects
 * The chat also contains a list of userIds in the chat, which are foreign keys to the chat Messages.
 */
public class Chat {
    private Map<String, String> dateCreated; //Firebase time object
    private String chatId;
    private ArrayList<Message> chatMessages;
    private HashSet<String> usersInChat;


    public Chat(String chatId, Map<String, String> dateCreated) {
        this.chatId = chatId;
        this.dateCreated = dateCreated;

        chatMessages = new ArrayList<Message>();
        usersInChat = new HashSet<>();
    }

    public Map<String, String> getDateCreated() {
        return dateCreated;
    }

    public String getChatId() {
        return chatId;
    }

    public Set<String> getUsersInChat() {
        return usersInChat;
    }

    public void addUsersToChat(String userId) {
        usersInChat.add(userId);
    }

    public ArrayList<Message> getChatMessages() {
        return chatMessages;
    }

    public void addMessageToChat(Message message) {
        chatMessages.add(message);
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof Chat && ((Chat) o).getChatId().equals(this.chatId);

    }

}
