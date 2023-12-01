package org.example.webserver.server;

import org.example.webserver.storage.UserStorage;


import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyServer {

    private UserStorage storage = new UserStorage();

    private final int port;

    public MyServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("New connection started...");
                // curl -vvv http://localhost:8080/user/all
                // curl -vvv -X POST http://localhost:8080/user -d @data.json

                DataInputStream reader = new DataInputStream(socket.getInputStream()); // позволяет работать с байтами в удобной форме

                String mainLine = reader.readLine();
                String methodName = mainLine.split(" ")[0];
                String endPoint = mainLine.split(" ")[1];
                //String protocolVersion = mainLine.split(" ")[2];
                String body = null;

                Map<String, Header> headers = new HashMap<>();

                String line;
                while ((line = reader.readLine()) != null){
                    if (line.isEmpty()) {
                        break;
                    }
                    System.out.println(line);
                    Header header = new Header(line);
                    headers.put(header.getName(), header);
                }

                if ("POST".equals(methodName) || "PUT".equals(methodName)) {
                    int contentLength = Integer.parseInt(headers.get("Content-Length").getValue());
                    byte[] arr = new byte[contentLength];
                    reader.read(arr);
                    body = new String(arr);
                }


                String result = "";
                RequestProcessor processor = new RequestProcessor(storage);
                if ("GET".equalsIgnoreCase(methodName)) {
                    result =processor.doGet(endPoint);
                } else if ("DELETE".equalsIgnoreCase(methodName)) {
                    result = processor.doDelete(endPoint);
                } else if ("POST".equalsIgnoreCase(methodName)) {
                    result = processor.doPost(endPoint, body);
                }

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("HTTP/1.1 200 OK");
                //writer.println("Content-Type: application/json");
                writer.println("Content-Type: text/html");
                writer.write("Content-Length: " + result.length() + "\r\n\r\n");
                writer.write(result);
                writer.flush();
                socket.close();
            }

        } catch (Exception ex) {
            throw new RuntimeException("Failed to start server: " + ex, ex);
        }

    }
}
