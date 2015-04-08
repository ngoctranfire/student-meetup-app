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
public class Chat {
    private Map<String, String> dateCreated;
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
