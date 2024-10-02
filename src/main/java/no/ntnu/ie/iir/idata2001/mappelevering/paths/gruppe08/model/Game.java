package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import java.util.List;

/**
 * Represent a game, which connects and is responsible for the player, story and goals.
 * The class is also responsible for collecting the opening passage and navigate along
 * the passages as well.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class Game {
  private Player player;
  private Story story;
  private List<Goal> goals;

  /**
   * Creates an instance of Game.
   *
   * @param player the player in the game.
   * @param story  the story in the game.
   * @param goals  the goals in the game.
   */
  public Game(Player player, Story story, List<Goal> goals) {
    this.setPlayer(player);
    this.setStory(story);
    this.setGoals(goals);
  }

  /**
   * Sets the player in the game. The player can not be null.
   *
   * @param player the player to be set.
   */
  public void setPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("The player can not be set to null");
    }
    this.player = player;
  }

  /**
   * Sets the story in the game. The game can not be null.
   *
   * @param story the story to be set.
   */
  public void setStory(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("The story can not be set to null");
    }
    this.story = story;
  }

  /**
   * Sets the goals in the game. The goals can not be null.
   *
   * @param goals the goals to be set.
   */
  public void setGoals(List<Goal> goals) {
    if (goals == null) {
      throw new IllegalArgumentException("The goals can not be set to null");
    }
    this.goals = goals;
  }


  /**
   * Returns the player in the game.
   *
   * @return the player in the game.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Returns the story in the game.
   *
   * @return the story in the game.
   */
  public Story getStory() {
    return story;
  }

  /**
   * Returns the list of goals in the game.
   *
   * @return the list of goals in the game.
   */
  public List<Goal> getGoals() {
    return goals;
  }

  /**
   * Returns the first passage in the story for this game.
   *
   * @return the opening passage in the story for this game.
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }

  /**
   * Returns the passage that is connected the given link. If the link is null,
   * an IllegalArgumentException is thrown.
   * If a link contains an action, the action is executed on the player.
   *
   * @param link the link to follow.
   * @return the passage that is connected to the link.
   */
  public Passage go(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("null in go");
    }
    link.getActions().forEach(action -> action.execute(this.player));
    return story.getPassage(link);
  }
}
