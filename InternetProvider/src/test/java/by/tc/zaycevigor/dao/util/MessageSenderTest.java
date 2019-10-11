package by.tc.zaycevigor.dao.util;

import org.junit.jupiter.api.Test;

class MessageSenderTest {
    private String email = "igor42638@gmail.com";


    @Test
    void send() throws InterruptedException {
        String text = "It's test message,don't answer it";
        MessageService service = MessageService.MESSAGE_SERVICE;
        service.sendMail("test", text, email);
    }
}