package org.example.web.task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class UserApiService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private static final Gson gson = new Gson();

    public static UserEntity createUser() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String requestBody = "{\"name\":\"Ragnar Lothbrok\",\"username\":\"frenchConquerent\",\"email\":\"rlothbrok@example.com\"," + "\"address\":{\"street\":\"123 Street\",\"suite\":\"Apt 4B\",\"city\":\"City\",\"zipcode\":\"12345\"," + "\"geo\":{\"lat\":\"40.7128\",\"lng\":\"-74.0060\"}}}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                return getUserEntity(connection);
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException("Something goes wrong");
        }
        return null;
    }

    public static UserEntity updateUser(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String requestBody = "{\"id\":5,\"name\":\"Bjorn Ironside\",\"username\":\"ironside\",\"email\":\"ragnarsson@example.com\",\"address\":{\"street\":\"123 Main St\",\"suite\":\"Apt 4\",\"city\":\"New York\",\"zipcode\":\"10001\"}}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                return getUserEntity(connection);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean deleteUser(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<UserEntity> getUsers() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Type userListType = new TypeToken<List<UserEntity>>() {
                    }.getType();
                    return gson.fromJson(response.toString(), userListType);
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Optional<UserEntity> getUserById(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    UserEntity user = gson.fromJson(response.toString(), UserEntity.class);
                    return Optional.ofNullable(user);
                }
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static Optional<UserEntity> getUserByUsername(String username) {
        try {
            URL url = new URL(BASE_URL + "?username=" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Type userListType = new TypeToken<List<UserEntity>>() {
                    }.getType();
                    List<UserEntity> users = gson.fromJson(response.toString(), userListType);
                    if (!users.isEmpty()) {
                        return Optional.of(users.get(0));
                    }
                }
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private static UserEntity getUserEntity(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return gson.fromJson(response.toString(), UserEntity.class);
        }
    }

}