package org.example.webserver.server;

public class Header {
    private String name;
    private String value;

    public Header(String header) {
        String[] arr = header.split(":");
        name = arr[0].trim();
        value = arr[1].trim();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Header{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
