package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the score goal for a player.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  /**
   * Creates an instance of score goal. Score must be grater than 0.
   *
   * @param minimumPoints the score goal.
   */
  public ScoreGoal(int minimumPoints) {
    if(minimumPoints <= 0){
      throw new IllegalArgumentException("The goal must be greater than 0.");
    }
    this.minimumPoints = minimumPoints;
  }

  /**
   * Returns a string representation of the minimum points.
   *
   * @return a string representation of the minimum points.
   */
  @Override
  public String toString() {
    return this.minimumPoints + "";
  }

  /**
   * Checks if goal is fulfilled.
   * If the player's score is equal or greater than the minimum points, the goal is fulfilled.
   * False, if not.
   *
   * @param player the player to be checked.
   * @return true, if goals are fulfilled. False, if not.
   */
  public boolean isFulfilled(Player player) {
    boolean goalIsFulfilled = false;
    if (player.getScore() >= this.minimumPoints) {
      goalIsFulfilled = true;
    }
    return goalIsFulfilled;
  }
}
