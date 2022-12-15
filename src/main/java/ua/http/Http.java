package ua.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Http {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public List<Todos> getTodos() {
        return todos;
    }

    public void setTodos(List<Todos> todos) {
        this.todos = todos;
    }

    private List<Todos> todos;

    public static void sendGetUsername(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            List<User> user = GSON.fromJson(response.body(), listType);
            System.out.println(user);
        } catch (ConnectException ex) {
            System.out.println(ex.getMessage());

        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }


    }

    public static void sendGetId(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(GSON.fromJson(response.body(), User.class));
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }

    }


    public static void sendPost(URI uri, User user) {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response;

        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(GSON.fromJson(response.body(), User.class));
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }


    }


    public static void sendPut(URI uri, User user) {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(uri)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        final HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            User o = GSON.fromJson(response.body(), new TypeToken<User>() {
            }.getType());
            System.out.println(o);
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }

    }


    public static void delete(URI uri, User user) {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> send;
        try {
            send = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(send);
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }

    }


    public static void sendGetResults(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
            }.getType());
            System.out.println(users);
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }

    }

    //void for task2
    public static void sendGetComments(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            List<Comments> comments = GSON.fromJson(response.body(), new TypeToken<List<Comments>>() {
            }.getType());
            Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
            String json = gson1.toJson(comments);
            System.out.println(json);
            try {
                FileWriter fileWriter = new FileWriter("user-X-post-Y-comments.json");
                fileWriter.write(json);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }
    }
//void for task3
    public static void sendGetTodos(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            List<Todos> todos = GSON.fromJson(response.body(), new TypeToken<List<Todos>>() {
            }.getType());
            List<Todos> list = todos.stream().filter(todos1 -> !todos1.isCompleted()).collect(Collectors.toList());
            Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
            String json = gson1.toJson(list);
            System.out.println(json);
        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка :" + e.getMessage());
        }
    }
}
