package by.zaycev;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Hungarian rhapsody";
    }
}
