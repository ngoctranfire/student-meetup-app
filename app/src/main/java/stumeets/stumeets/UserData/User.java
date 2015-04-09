package stumeets.stumeets.UserData;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import stumeets.stumeets.ChatData.Chat;
import stumeets.stumeets.GroupData.Group;

/**
 * Created by ngoctranfire on 4/9/15.
 */

public class User {
    public static final boolean MALE = true;
    public static final boolean FEMALE = false;

    private Map<String, String> dateCreated;
    private String userId;
    private boolean gender = MALE;

    private int birthYear = 0;
    private String fullName = "";
    private String picture = "";
    private String email = "";
    private int userType = 0;
    private String major = "";
    private String schoolId = "";

    private HashSet<String> userMatches;
    private HashSet<String> userChats;
    private HashSet<String> userGroups;
    private HashSet<String> userFriends;
    private HashSet<String> userLikes;




    public User(String userId, String email, Map<String, String> dateCreated) {
        this.userId = userId;
        this.email = email;
        this.dateCreated = dateCreated;

        this.userMatches = new HashSet<String>();
        this.userChats = new HashSet<String>();
        this.userGroups = new HashSet<String>();
        this.userFriends = new HashSet<String>();

    }

    public long getBirthYear() {
        return birthYear;
    }

    public String getFullName() {
        return fullName;
    }
    public boolean getGender() {
        return gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String resourceString) {
        picture = resourceString;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, String> getDateCreated() {
        return dateCreated;
    }

    public int getUserType() {
        return userType;
    }

    public Set<String> getUserChats() {
        return userChats;
    }

    public void addToUserChats(String chatId) {
        userChats.add(chatId);
    }

    public String getSchoolId() {
        return schoolId;
    }

    public String getMajor() {
        return major;
    }

    public Set<String> getUserGroups() {
        return userGroups;
    }
    public void addToUserGroups(String groupId) {
        userGroups.add(groupId);
    }

    public Set<String> getUserFriends() {
        return userFriends;
    }

    public void addToUserFriends(String userId) {
        userFriends.add(userId);
    }
    public Set<String> getUserMatches() {
        return userMatches;
    }

    public void addToMatchList(String userId) {

        userMatches.add(userId);
    }

    public HashSet<String> getUserLikes() {
        return userLikes;
    }

    public void addToUserLikes(String userId) {
        userLikes.add(userId);
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User && ((User) o).getUserId().equals(this.userId);
    }
}
