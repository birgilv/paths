package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;

/**
 * Represents the gold goal for a player.
 *
 * @author mappe_08
 * @version 22/05/2023
 */

public class GoldGoal implements Goal {
  private PathsApp pathsApp;
  private final int minimumGoal;

  /**
   * Creates an instance of gold goal. Gold must be grater than 0.
   *
   * @param minimumGoal the gold goal.
   */
  public GoldGoal(int minimumGoal) {
    if (minimumGoal <= 0) {
      throw new IllegalArgumentException("The goal must be greater than 0.");
    }
    this.minimumGoal = minimumGoal;
  }

  /**
   * Returns a string representation of the minimum gold.
   *
   * @return a string representation of the minimum gold.
   */
  @Override
  public String toString() {
    return this.minimumGoal + "";
  }

  /**
   * Checks if goal is fulfilled.
   * If the player's gold is equal or greater than the minimum gold, the goal is fulfilled.
   * False, if not.
   *
   * @param player the player to be checked.
   * @return true, if goals are fulfilled. False, if not.
   */
  public boolean isFulfilled(Player player) {
    boolean goalIsFulfilled = false;
    if (player.getGold() >= this.minimumGoal) {
      goalIsFulfilled = true;
    }
    return goalIsFulfilled;
  }
}
