package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals.GoldGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the Gold Action class. The gold action can
 * represent a value of all integers.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Execute player</dd>
 * </dl>
 *
 *
 * @author mappe_08
 * @version 4/13/2023
 **/
class GoldActionTest {
    /**
     * Tests that an instance of GoldAction is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        GoldAction goldAction = new GoldAction(5);
        assertEquals("5", goldAction.toString());
    }
    /**
     * Tests that an instance of Gold Action is executed.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void goldActionsAreExecuted(){
        GoldAction goldAction = new GoldAction(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(10)
                .build();
        goldAction.execute(player);
        assertEquals(20,player.getGold());
    }
}