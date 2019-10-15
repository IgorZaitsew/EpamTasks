package by.tc.zaycevigor.dao.util;

import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

class MessageSenderTest {
    private String email = "igor42638@gmail.com";
class MedicalStaff{

    }
    class Doctor extends MedicalStaff{

    }

    @Test
    void send() throws InterruptedException {
        String text = "It's test message,don't answer it";
        MessageService service = MessageService.MESSAGE_SERVICE;
        service.sendMail("test", text, email);
    }
}