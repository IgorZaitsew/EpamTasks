package by.tc.zaycevigor.dao.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderTest {
    private String email = "igor42638@gmail.com";

    @Test
    void send() {
        String text = "It's test message,don't answer it";
        new MessageSender().run("Test", text, email);
    }
}