package org.example.web.task2;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.FileWriter;
import java.io.IOException;

public class CommentFetcher {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Gson gson = new Gson();

    public static void fetchComments(int userId) {
        OkHttpClient client = new OkHttpClient();

        String postsUrl = BASE_URL + "/users/" + userId + "/posts";
        Request postsRequest = new Request.Builder().url(postsUrl).build();

        try (Response postsResponse = client.newCall(postsRequest).execute()) {
            ResponseBody postsBody = postsResponse.body();
            if (postsBody != null) {
                String postsJson = postsBody.string();

                int lastPostId = getLastPostId(postsJson);

                String commentsUrl = BASE_URL + "/posts/" + lastPostId + "/comments";
                Request commentsRequest = new Request.Builder().url(commentsUrl).build();

                try (Response commentsResponse = client.newCall(commentsRequest).execute()) {
                    ResponseBody commentsBody = commentsResponse.body();
                    if (commentsBody != null) {
                        String commentsJson = commentsBody.string();

                        writeCommentsToFile(lastPostId,userId, commentsJson);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getLastPostId(String postsJson) {
        PostEntity[] posts = gson.fromJson(postsJson, PostEntity[].class);
        int highestId = -1;
        for (PostEntity post : posts) {
            if (post.getId() > highestId) {
                highestId = post.getId();
            }
        }
        return highestId;
    }

    private static void writeCommentsToFile(int userId, int postId, String commentsJson) {
        String fileName = "user-" + userId + "-post-" + postId + "-comments.json";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(commentsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

