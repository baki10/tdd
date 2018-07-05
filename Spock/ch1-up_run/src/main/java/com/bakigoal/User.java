package com.bakigoal;

import java.util.ArrayList;
import java.util.List;

public class User {

  private String name;
  private List<User> following;

  public User(String name) {
    this.name = name;
    following = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void follow(User other) {
    following.add(other);
  }

  public List<User> getFollowing() {
    return following;
  }

  public boolean follows(User other) {
    return following.contains(other);
  }

  @Override
  public String toString() {
    return "User{" + name + '}';
  }
}
