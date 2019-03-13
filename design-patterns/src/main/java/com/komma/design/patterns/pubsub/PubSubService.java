package com.komma.design.patterns.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PubSubService {

  private Map<String, Set<Subscriber>> subscribersByTopic;

  /**
   * Idea is to run a cron job, that invokes broadcast method. Until that we collect messages into lists.
   * We may use priority queue, to pick up messages of a topic sooner than later.
   */
  private PriorityQueue<String> messageQueue;
  private Map<String, List<Message>> messagesByTopic;

  public PubSubService() {
    subscribersByTopic = new HashMap<>();
    // Can be initialized with a comparator to prioritize topics.
    messageQueue = new PriorityQueue<>();
    messagesByTopic = new HashMap<>();
  }

  public void addMessageToQueue(Message message) {
    String topic = message.getTopic();
    List<Message> messages;
    if(messagesByTopic.containsKey(topic)) {
      messages = messagesByTopic.get(topic);
    } else {
      messageQueue.offer(topic);
      messages = new ArrayList<>();
      messagesByTopic.put(topic, messages);
    }
    messages.add(message);
  }

  public void addSubscriber(String topic, Subscriber subscriber) {
    Set<Subscriber> subscribers = subscribersByTopic.getOrDefault(topic, new HashSet<>());
    subscribers.add(subscriber);
    subscribersByTopic.putIfAbsent(topic, subscribers);
  }

  public void broadcast() {
    while (!messageQueue.isEmpty()) {

    }
  }
}
