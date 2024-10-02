package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Action;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the change in a players' gold.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class GoldAction implements Action {
  private final int gold;

  /**
   * Creates an instance of Gold action.
   *
   * @param gold the gold in the gold action.
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  /**
   * Returns a string representation of the gold.
   *
   * @return a string representation of the gold.
   */
  @Override
  public String toString() {
    return this.gold + "";
  }

  /**
   * Execute a gold action for a player. Adds the gold to the player.
   *
   * @param player the player to be executed.
   */
  public void execute(Player player) {
    player.addGold(gold);
  }
}
