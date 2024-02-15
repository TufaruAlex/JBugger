package com.example.demo.factory;

import com.example.demo.entity.Bug;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import com.example.demo.enums.NotificationEnum;
import com.example.demo.user_call.UserInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Component
public class NotificationFactory {
    public Notification createWelcomeNotification(User receiver) {
        return Notification.builder()
                .type(NotificationEnum.WELCOME_NEW_USER)
                .users(new HashSet<>() {{
                    add(receiver);
                }})
                .URL("")
                .msg(String.format("Welcome! Your data: %s.",
                        receiver.userInfoWithoutPassword()))
                .createdTime(LocalDateTime.now())
                .build();
    }
    public Notification createUserUpdatedNotification(User updatedReceiver, User updaterReceiver, UserInfo oldInfo) {
        return Notification.builder()
                .type(NotificationEnum.USER_UPDATED)
                .users(new HashSet<>() {{
                    add(updatedReceiver);
                    add(updaterReceiver);
                }})
                .URL("")
                .msg(String.format("Old data: %s, updated data: %s.",
                        oldInfo.userInfoWithPassword(),
                        updatedReceiver.userInfoWithPassword()))
                .createdTime(LocalDateTime.now())
                .build();
    }
    public Notification createUserDeletedNotification(Collection<User> receivers, User deletedUser) {
        return Notification.builder()
                .type(NotificationEnum.USER_DELETED)
                .users(new HashSet<>() {{
                    this.addAll(receivers);
                }})
                .URL("")
                .msg(String.format("User deleted. Its data: %s.",
                        deletedUser.userInfoWithPassword()))
                .createdTime(LocalDateTime.now())
                .build();
    }
    public Notification createBugStatusUpdateNotification(Bug updatedBug) {
        return Notification.builder()
                .type(NotificationEnum.BUG_STATUS_UPDATED)
                .users(new HashSet<>() {{
                    add(updatedBug.getCreatedByUser());
                    add(updatedBug.getAssignedToUser());
                }})
                .URL("")
                .msg(String.format("The status of the bug #%s was modified!",
                        updatedBug.getBugId().toString()))
                .createdTime(LocalDateTime.now())
                .build();
    }
    public Notification createBugUpdateNotification(Bug bug, Boolean bugWasAdded) {
        return Notification.builder()
        .type(NotificationEnum.BUG_UPDATED)
                .users(new HashSet<>() {{
                    add(bug.getCreatedByUser());
                    add(bug.getAssignedToUser());
                }})
                .URL("")
                .msg(String.format("Bug #%s was %s!",
                        bug.getBugId().toString(), bugWasAdded ? "added" : "updated"))
                .createdTime(LocalDateTime.now())
                .build();
    }
    public Notification createBugClosedNotification(Bug closedBug) {
        return Notification.builder()
                .type(NotificationEnum.BUG_CLOSED)
                .users(new HashSet<>() {{
                    add(closedBug.getCreatedByUser());
                    add(closedBug.getAssignedToUser());
                }})
                .URL("")
                .msg(String.format("Bug #%s was closed!",
                        closedBug.bugInfo()))
                .createdTime(LocalDateTime.now())
                .build();
    }
    public Notification createUserDeactivatedNotification(Collection<User> receivers, User deactivatedUser) {
        return Notification.builder()
                .type(NotificationEnum.USER_DEACTIVATED)
                .users(new HashSet<>() {{
                    this.addAll(receivers);
                }})
                .URL("")
                .msg(String.format("Deactivated user. Its data: %s.",
                        deactivatedUser.userInfoWithPassword()))
                .createdTime(LocalDateTime.now())
                .build();
    }
}
