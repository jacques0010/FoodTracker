package com.calorieCounter.main.Events;

import com.calorieCounter.main.Events.EventInterfaces.NotificationEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 10/16/2017.
 */
public class NotificationEvent {
    private List<NotificationEventListener> listeners= new ArrayList<>();

    public void addListener(NotificationEventListener listener){
        listeners.add(listener);
    }

    public void fire(){

    }
}
