package com.bakigoal.db;

import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.List;

public class Person {

  @Id
  private String name;

  private List<String> friends;

  public Person() {
  }

  public Person(String name) {
    this.name = name;
    friends = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public List<String> getFriends() {
    return friends;
  }

  public void addFriend(String friend) {
    if (!friends.contains(friend)) {
      friends.add(friend);
    }
  }
}
