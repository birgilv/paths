package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Action;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the change in a players' inventory.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class InventoryAction implements Action {
  private final String item;

  /**
   * Creates an instance of Inventory action.
   *
   * @param item the item in the inventory action.
   */
  public InventoryAction(String item) {
    if(item == null || item.isBlank()){
      throw new IllegalArgumentException();
    }
    this.item = item.toLowerCase();
  }

  /**
   * Returns a string representation of the item.
   *
   * @return a string representation of the item.
   */
  @Override
  public String toString() {
    return this.item + "";
  }

  /**
   * Execute an inventory action for a player. Adds the item to the inventory to the player.
   *
   * @param player the player to be executed.
   */
  public void execute(Player player) {
    player.addToInventory(item.toLowerCase());
  }
}
