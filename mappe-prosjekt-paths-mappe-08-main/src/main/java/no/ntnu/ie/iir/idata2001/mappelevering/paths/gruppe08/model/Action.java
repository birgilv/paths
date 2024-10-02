package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

/**
 * Represents an action to come in the state of a player. This includes changes in the
 * players' score, health, gold or inventory.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public interface Action {

  /**
   * Execute a player.
   *
   * @param player the player to be executed.
   */
  void execute(Player player);
}