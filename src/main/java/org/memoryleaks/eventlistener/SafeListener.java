package org.memoryleaks.eventlistener;

public class SafeListener implements EventListener {

  private final EventProducer producer;
  private boolean active = true;

  public SafeListener(EventProducer producer) {
    this.producer = producer;
    producer.addListener(this);
  }

  @Override
  public void onEvent(String message) {
    if (active) {
      System.out.println("[SafeListener] Mesaj alındı: " + message);
    }
  }

  // Artık ihtiyaç kalmadığında çağrılacak metod
  public void stopListening() {
    this.active = false;
    producer.removeListener(this);
    System.out.println("[SafeListener] Dinleyici aboneliği iptal edildi.");
  }

}
