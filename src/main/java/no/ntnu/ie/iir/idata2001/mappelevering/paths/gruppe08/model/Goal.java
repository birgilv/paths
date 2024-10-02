package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

/**
 * Represents a goal the player must fulfill in order to win the game.
 * This includes goals for score, health, gold and inventory.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public interface Goal {

  /**
   * Checks if the goal is fulfilled.
   *
   * @param player the player to check for.
   * @return true, if the goal is fulfilled. False, if not.
   */
  boolean isFulfilled(Player player);
}
