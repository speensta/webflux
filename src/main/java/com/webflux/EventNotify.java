package com.webflux;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class EventNotify {

    private List events = new ArrayList();
    private boolean change = false;

    public void add(String event) {
        this.events.add(event);
        this.change = true;
    }

}
