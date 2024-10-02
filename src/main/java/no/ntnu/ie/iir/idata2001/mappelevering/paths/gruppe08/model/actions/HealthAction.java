package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Action;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the change in a players' health.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class HealthAction implements Action {
  private final int health;

  /**
   * Creates an instance of Health action.
   *
   * @param health the health in the health action.
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * Returns a string representation of the health.
   *
   * @return a string representation of the health.
   */
  @Override
  public String toString() {
    return this.health + "";
  }

  /**
   * Execute a health action for a player. Adds the health to the player.
   *
   * @param player the player to be executed.
   */
  public void execute(Player player) {
    player.addHealth(health);
  }
}
