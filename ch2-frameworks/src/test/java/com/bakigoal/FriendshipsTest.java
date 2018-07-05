package com.bakigoal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

public class FriendshipsTest {

  private Friendships friendships;

  @BeforeClass
  public static void beforeClass() {
    // This method will be executed once on initialization time
  }

  @Before
  public void before() {
    friendships = new Friendships();
    friendships.makeFriends("Joe", "Audrey");
    friendships.makeFriends("Joe", "Peter");
    friendships.makeFriends("Joe", "Michael");
    friendships.makeFriends("Joe", "Britney");
    friendships.makeFriends("Joe", "Paul");
  }

  @Test
  public void alexDoesNotHaveFriends() {
    Assert.assertTrue("Alex does not have friends",
        friendships.getFriendsList("Alex").isEmpty());
  }

  @Test
  public void joeHas5Friends() {
    Assert.assertEquals("Joe has 5 friends", 5,
        friendships.getFriendsList("Joe").size());
  }

  @Test
  public void joeHas5FriendsHamcrest() {
    assertThat(friendships.getFriendsList("Joe"), hasSize(5));
  }

  @Test
  public void joeIsFriendWithEveryone() {
    List<String> friendsOfJoe = Arrays.asList("Audrey", "Peter", "Michael", "Britney", "Paul");
    Assert.assertTrue(friendships.getFriendsList("Joe")
        .containsAll(friendsOfJoe));
  }

  @Test
  public void joeIsFriendWithEveryoneHamcrest() {
    assertThat(
        friendships.getFriendsList("Joe"),
        containsInAnyOrder("Audrey", "Peter", "Michael", "Britney", "Paul")
    );
  }
}