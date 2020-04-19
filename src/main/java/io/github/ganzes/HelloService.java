package io.github.ganzes;

import java.util.Optional;

class HelloService {
    static final String FALLBACK_NAME = "World";

    String prepareGreeting(String name){
        return "Hello " + Optional.ofNullable(name).orElse(FALLBACK_NAME + "!");
    }
}
