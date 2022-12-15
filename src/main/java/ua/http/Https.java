package ua.http;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Https {
    private static final String CLIENT_USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String CLIENT_USER_ID = "https://jsonplaceholder.typicode.com/users/3";
    private static final String CLIENT_USER_USERNAME = "https://jsonplaceholder.typicode.com/users?username=Moriah.Stanton";
    private static final String CLIENT_USER_COMMENTS = "https://jsonplaceholder.typicode.com/posts/10/comments";
    private static final String CLIENT_USER_TODOS = "https://jsonplaceholder.typicode.com/users/1/todos";

    public static void main(String[] args) throws IOException, InterruptedException {
        User user = createUser();
        Http.sendPost(URI.create(CLIENT_USER_URL), user);
        Http.sendPut(URI.create(CLIENT_USER_URL + "/" + user.getId()), user);
        Http.delete(URI.create(CLIENT_USER_URL + "/" + user.getId()), user);
        Http.sendGetResults(URI.create(CLIENT_USER_URL));
        Http.sendGetId(URI.create(String.format(CLIENT_USER_ID, user.getEmail())));
        Http.sendGetUsername(URI.create(String.format(CLIENT_USER_USERNAME, user.getUsername())));
        Http.sendGetComments(URI.create(CLIENT_USER_COMMENTS));
        Http.sendGetTodos(URI.create(CLIENT_USER_TODOS));

    }

    private static User createUser() {
        User user = new User(1, "Tom", "Tomich", "truk@email.com",
                new Adress("Rivna", "Apt.122", "KIEV", "112-25", new Geo("11.1155", "112.22")),
                "112-258-5588", "patron.org", new Company("Sharashka", "catchPrase", "BS"));

        return user;
    }
}
