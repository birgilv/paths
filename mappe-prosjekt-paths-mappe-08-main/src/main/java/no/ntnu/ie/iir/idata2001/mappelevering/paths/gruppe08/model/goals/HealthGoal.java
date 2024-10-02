package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the health goal for a player.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  /**
   * Creates an instance of health goal. Health must be grater than 0.
   *
   * @param minimumHealth the health goal.
   */
  public HealthGoal(int minimumHealth) {
    if(minimumHealth <= 0){
      throw new IllegalArgumentException("The goal must be greater than 0.");
    }
    this.minimumHealth = minimumHealth;
  }

  /**
   * Returns a string representation of the minimum health.
   *
   * @return a string representation of the minimum health.
   */
  @Override
  public String toString() {
    return this.minimumHealth + "";
  }

  /**
   * Checks if goal is fulfilled.
   * If the player's health is equal or greater than the minimum health, the goal is fulfilled.
   * False, if not.
   *
   * @param player the player to be checked.
   * @return true, if goals are fulfilled. False, if not.
   */
  public boolean isFulfilled(Player player) {
    boolean goalIsFulfilled = false;
    if (player.getHealth() >= this.minimumHealth) {
      goalIsFulfilled = true;
    }
    return goalIsFulfilled;
  }
}
