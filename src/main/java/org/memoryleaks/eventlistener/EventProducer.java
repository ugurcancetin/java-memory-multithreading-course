package org.memoryleaks.eventlistener;

import java.util.ArrayList;
import java.util.List;

public class EventProducer {

  private final List<EventListener> listeners = new ArrayList<>();

  public void addListener(EventListener listener) {
    listeners.add(listener);
  }

  public void removeListener(EventListener listener) {
    listeners.remove(listener);
  }

  public void fireEvent(String message) {
    System.out.println("[Producer] Olay tetiklendi: " + message);
    for (EventListener listener : listeners) {
      listener.onEvent(message);
    }
  }
}
