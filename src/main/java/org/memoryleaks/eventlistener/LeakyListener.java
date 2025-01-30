package org.memoryleaks.eventlistener;

public class LeakyListener implements EventListener{

  private final EventProducer producer;

  public LeakyListener(EventProducer producer) {
    this.producer = producer;
    // Abone oluyor
    producer.addListener(this);
  }

  @Override
  public void onEvent(String message) {
    // Olay mesajını alır ama hiçbir şey yapmasa bile producer listesinde tutulur.
    System.out.println("[LeakyListener] Mesaj alındı: " + message);
  }
}
