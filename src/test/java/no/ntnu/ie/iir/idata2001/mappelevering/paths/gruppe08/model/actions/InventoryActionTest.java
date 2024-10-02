package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals.GoalFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Gold Action class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Execute player</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of action with arguments are empty</dd>
 *          <dd>- Creation of action with arguments are null</dd>
 *          <dd>- Creation of action with arguments are blank</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 4/13/2023
 */
class InventoryActionTest {
    /**
     * Tests that an instance of Inventory Action is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        InventoryAction inventoryAction = new InventoryAction("item");
        assertEquals("item", inventoryAction.toString());
    }
    /**
     * Tests that an instance of Inventory Action is executed.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void inventoryActionsAreExecuted(){
        InventoryAction inventoryAction = new InventoryAction("item");
        Player player = new Player.Builder()
                .setName("PlayerName")
                .build();
        inventoryAction.execute(player);
        assertEquals("item\n",player.getInventoryAsString());
    }

    /**
     * Test that the class Inventory is able to handle creation of an action
     * where the value provided is empty.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfInventoryActionWithValueProvidedIsEmpty(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    InventoryAction inventoryAction = new InventoryAction("");
                });
    }

    /**
     * Test that the class Inventory is able to handle creation of an action
     * where the value provided is null.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfInventoryActionWithValueProvidedIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    InventoryAction inventoryAction = new InventoryAction(null);
                });
    }

    /**
     * Test that the class Inventory is able to handle creation of an action
     * where the value provided is blank.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfInventoryActionWithValueProvidedIsBlank(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    InventoryAction inventoryAction = new InventoryAction("     ");
                });
    }
}