package org.example.webserver.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.webserver.storage.UserInfo;
import org.example.webserver.storage.UserStorage;

import java.util.Collection;

public class RequestProcessor {

    private final UserStorage storage;
    private ObjectMapper objectMapper = new ObjectMapper();

    public RequestProcessor(UserStorage storage) {
        this.storage = storage;
    }

    public String doGet(String endPoint) {
        if ("/user/all".equalsIgnoreCase(endPoint)) {
            Collection<UserInfo> users = storage.getAll();
            try {
                return objectMapper.writeValueAsString(users);
            } catch (JsonProcessingException e) {
                return "ERROR";
            }
        } else if ("/author".equalsIgnoreCase(endPoint)) {
            String html = "<html><body>Author: Anton</body></html>";
            return html;
        }

        return "result";
    }


    public String doDelete(String endPoint) {
        return "";
    }

    // curl -vvv -X POST http://localhost:8080/user -d @data.json
    public String doPost(String endPoint, String body) {
        if ("/user".equalsIgnoreCase(endPoint)) {
            UserInfo info = null;
            try {
                info = objectMapper.readValue(body, UserInfo.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            storage.add(info);
            return "";
        }
        return "server response";
    }
}
