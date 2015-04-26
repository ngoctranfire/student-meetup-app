package stumeets.stumeets.GroupData;

import com.firebase.client.ServerValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import stumeets.stumeets.UserData.User;

/**
 * Created by ngoctranfire on 4/9/15.
 */

/**
 * This is the object to represent the group. It is the model that corresponds directly with the
 * groups in the Firebase Database.
 * The object contains a groupId to uniquely identify the specific group.
 * The object contains a groupName, which is the groupName.
 * The dateCreated is a firebase object that represents the date it was created.
 * The usersInGroup field is a Hashset(dictionary) containing all the userIDS in the group.
 * The groupChats field contains a list of all the different chats that have taken place in the group.
 *
 */
public class Group {
    private String groupId;
    private String groupName;
    private Map<String, String> dateCreated;
    private HashSet<String> usersInGroup;
    private HashSet<String> groupChats;

    public Group(String groupId, String groupName, Map<String, String> dateCreated) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.dateCreated = dateCreated;

        usersInGroup = new HashSet<String>();
        groupChats = new HashSet<String>();
    }
    public Map<String, String> getDateCreated() {
        return dateCreated;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<String> getUsersInGroup() {
        return usersInGroup;
    }

    public void addUserToGroup(String userId) {
        usersInGroup.add(userId);
    }

    public Set<String> getGroupChats() {
        return groupChats;
    }

    public void addChatToGroup(String chatId) {
        groupChats.add(chatId);
    }

    public String getGroupId() {
        return groupId;
    }


    @Override
    public boolean equals(Object o) {
        return o instanceof Group && ((Group) o).getGroupId().equals(this.groupId);
    }
}
