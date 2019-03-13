package com.komma.design.patterns.pubsub;

public class PublisherImpl implements Publisher {
  @Override
  public void publish(Message message, PubSubService service) {
    service.addMessageToQueue(message);
  }
}
