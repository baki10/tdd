package com.bakigoal;

import com.bakigoal.db.FriendsCollection;
import com.bakigoal.db.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipsMongoTest {

  @InjectMocks
  private FriendshipsMongo friendships;

  @Mock
  private FriendsCollection friends;

  @Before
  public void setupMock() {
    List<String> expected = Arrays.asList("Audrey", "Peter", "Michael", "Britney", "Paul");
    Person joe = spy(new Person("Joe"));

    doReturn(joe).when(friends).findByName("Joe");
    doReturn(expected).when(joe).getFriends();
  }

  @Test
  public void joeHas5Friends() {
    assertThat(
        friendships.getFriendsList("Joe"),
        containsInAnyOrder("Audrey", "Paul", "Peter", "Michael", "Britney")
    );
    assertThat(friendships.getFriendsList("Joe"), hasSize(5));
  }

}