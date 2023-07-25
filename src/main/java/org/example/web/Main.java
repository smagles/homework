package org.example.web;

import static org.example.web.task2.CommentFetcher.fetchComments;
import static org.example.web.task3.TaskApp.getOpenTasksForUser;
import static org.example.web.task1.UserApiService.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(createUser());
        System.out.println(updateUser(1));
        System.out.println(deleteUser(1));
        System.out.println(getUsers());
        System.out.println(getUserById(1));
        System.out.println(getUserByUsername("John"));
        System.out.println(getOpenTasksForUser(1));
        fetchComments(1);

    }
}