package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Action;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the change in a players' score.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class ScoreAction implements Action {

  private final int points;

  /**
   * Creates an instance of Score action.
   *
   * @param points the points in the score action.
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * Returns a string representation of the points.
   *
   * @return a string representation of the points.
   */
  @Override
  public String toString() {
    return this.points + "";
  }

  /**
   * Execute a score action for a player. Adds the points to the score to the player.
   *
   * @param player the player to be executed.
   */
  public void execute(Player player) {
    player.addScore(points);
  }
}
