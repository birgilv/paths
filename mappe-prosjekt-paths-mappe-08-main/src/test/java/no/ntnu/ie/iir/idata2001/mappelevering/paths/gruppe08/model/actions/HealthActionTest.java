package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * A test class for the Health Action class. The health action can
 * represent a value of all integers.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Execute player</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 4/13/2023
 **/
class HealthActionTest {
    /**
     * Tests that an instance of Health Action is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        HealthAction healthAction = new HealthAction(5);
        assertEquals("5", healthAction.toString());
    }
    /**
     * Tests that an instance of Health Action is executed.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void healthActionsAreExecuted(){
        HealthAction healthAction = new HealthAction(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(10)
                .build();
        healthAction.execute(player);
        assertEquals(16,player.getHealth());
    }

}