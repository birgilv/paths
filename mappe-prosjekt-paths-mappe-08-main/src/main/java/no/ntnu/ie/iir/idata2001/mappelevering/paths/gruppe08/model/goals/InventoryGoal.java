package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import java.util.List;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;


/**
 * Represents the inventory goal for a player.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /**
   * Creates an instance of inventory goal.
   *
   * @param mandatoryItems the inventory goal.
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * Returns a string representation of the mandatory items.
   * If mandatory items is equal or greater than 0, create the items
   * as a string written in lowercase. If not, the string is set to 'Empty...'
   *
   * @return a string representation of the mandatory items.
   */
  @Override
  public String toString() {
    String allInventory = "";
    for (String inventory : mandatoryItems) {
      allInventory += inventory.toLowerCase() + "\n";
    }
    if (mandatoryItems.size() == 0) {
      allInventory = "Empty..";
    }
    return allInventory;
  }

  /**
   * Checks if goal is fulfilled. If the player's inventory contains all mandatory items, the goal
   * is fulfilled. False, if not.
   *
   * @param player the player to be checked.
   * @return true, if goals are fulfilled. False, if not.
   */
  public boolean isFulfilled(Player player) {
    Boolean goalIsFulfilled = false;
    if (player.getInventory().containsAll(this.mandatoryItems)) {
      goalIsFulfilled = true;
    }
    return goalIsFulfilled;
  }
}
