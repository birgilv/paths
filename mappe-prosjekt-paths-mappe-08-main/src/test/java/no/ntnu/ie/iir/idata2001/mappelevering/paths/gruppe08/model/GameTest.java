package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

/**
 * A test class for the game class.
 * <p>
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of instance with null arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 */
class GameTest {
  Player player = new Player.Builder()
      .setName("PlayerName")
      .setHealth(6)
      .setScore(30)
      .setGold(10)
      .build();
  Story story = new Story("StoryTitle",
      new Passage("PassageTitle", "PassageContent"));
  List<Goal> goals = new ArrayList<>();

  /**
   * Tests that an instance of Game is correctly created when the
   * parameters given to the constructor are valid (a valid player, story and goals).
   *
   * <p>This is a <b>positive</b> test since it tests that the class
   * Person is working according to the expected behaviour or the
   * main role/purpose of the class. I.e. verifies functionality.</p>
   */
  @Test
  public void creationOfInstanceWithValidArguments() {
    Game game = new Game(player, story, goals);
    assertEquals(player, game.getPlayer());
    assertEquals(story, game.getStory());
    assertEquals(goals, game.getGoals());
  }

  /**
   * Test that the class Game is able to handle creation of an instance
   * where the player, story and goals provided is null.
   *
   * <p>Expected behaviour: throw an illegal argument exception to
   * indicate that something went wrong.</p>
   *
   * <p>This is a typical <b>negative</b> test, since it does not test the
   * expected functionality of the class, but tests its robustness against
   * improper use.</p>
   */
  @Test
  public void creationOfInstanceWithInvalidParametersAreNull() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
          Game game = new Game(null, story, goals);
        });
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
          Game game = new Game(player, null, goals);
        });
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
          Game game = new Game(player, story, null);
        });
  }
}