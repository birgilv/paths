package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Passage;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * A test class for the Gold Goal class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Checks if gold goal is fulfilled</dd>
 *          <dd>- Checks if gold goal is not fulfilled</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of instance with invalid arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
**/

class GoldGoalTest {
    /**
     * Tests that an instance of Gold Goal is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        GoldGoal goldGoal = new GoldGoal(5);
        assertEquals("5", goldGoal.toString());
    }

    /**
     * Tests that an instance of Gold Goal is fulfilled.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void goldGoalsAreFulfilled(){
        GoldGoal goldGoal = new GoldGoal(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(10)
                .build();
        assertEquals(true, goldGoal.isFulfilled(player));
    }

    /**
     * Tests that an instance of Gold Goal is not fulfilled.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void goldGoalsAreNotFulfilled(){
        GoldGoal goldGoal = new GoldGoal(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(9)
                .build();
        assertEquals(false, goldGoal.isFulfilled(player));
    }

    /**
     * Test that the class Gold Goal is able to handle creation of an instance
     * where the gold provided is less than 0.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfGoldGoalWithIllegalArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    GoldGoal goldGoal = new GoldGoal(-5);
                });
    }
}