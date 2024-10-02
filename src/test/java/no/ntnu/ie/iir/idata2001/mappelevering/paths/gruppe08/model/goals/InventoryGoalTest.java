package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Gold Goal class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Checks if inventory goal is fulfilled</dd>
 *          <dd>- Checks if inventory goal is not fulfilled</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 **/
class InventoryGoalTest {
    private List<String> mandatoryItems = new ArrayList<>();
    private List<String> newList = new ArrayList<>();
    /**
     * Tests that an instance of Inventory Goal is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        newList.add("item");
        InventoryGoal inventoryGoal = new InventoryGoal(newList);
        assertEquals("item\n", inventoryGoal.toString());
    }

    /**
     * Tests that an instance of Inventory Goal is fulfilled.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void inventoryGoalsAreFulfilled(){
        newList.add("item");
        InventoryGoal inventoryGoal = new InventoryGoal(newList);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .build();
        player.addToInventory("item");
        assertEquals(true, inventoryGoal.isFulfilled(player));
    }
    /**
     * Tests that an instance of Inventory Goal is not fulfilled.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void inventoryGoalsAreNotFulfilled(){
        newList.add("item");
        InventoryGoal inventoryGoal = new InventoryGoal(newList);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .build();
        player.addToInventory("anotherItem");
        assertEquals(false, inventoryGoal.isFulfilled(player));
    }
}