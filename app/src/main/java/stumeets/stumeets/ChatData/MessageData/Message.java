package stumeets.stumeets.ChatData.MessageData;

import java.util.Map;

/**
 * Created by ngoctranfire on 4/9/15.
 */
public class Message {

    private String messageId ;
    private String userId1;
    private String groupOrUserId2;
    private Map<String, String> messageTime;
    private String messageText;

    public Message(String userId1, String userId2, Map<String, String> messageTime, String messageText) {

        this.userId1 = userId1;
        this.groupOrUserId2 = userId2;
        this.messageTime = messageTime;
        this.messageText = messageText;

    }

    public String getUserId1() {
        return userId1;
    }
    public String getGroupOrUserId2() {
        return groupOrUserId2;
    }
    public Map<String,String> getMessageTime() {
        return messageTime;
    }
    public String getMessageText() {
        return messageText;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public boolean equals(Object otherMessage) {
        return otherMessage instanceof Message && ((Message) otherMessage).getMessageId().equals(this.messageId);
    }

}
