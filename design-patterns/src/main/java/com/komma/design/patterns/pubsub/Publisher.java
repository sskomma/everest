package com.komma.design.patterns.pubsub;

/**
 * A publisher, that publishes messages.
 */
public interface Publisher {

  void publish(Message message, PubSubService service);
}
