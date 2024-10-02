package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Health Goal class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Checks if health goal is fulfilled</dd>
 *          <dd>- Checks if health goal is not fulfilled</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of instance with invalid arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 **/
class HealthGoalTest {
    /**
     * Tests that an instance of Health Goal is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        HealthGoal healthGoal = new HealthGoal(5);
        assertEquals("5", healthGoal.toString());
    }

    /**
     * Tests that an instance of Health Goal is fulfilled.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void healthGoalsAreFulfilledIs(){
        HealthGoal healthGoal = new HealthGoal(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(10)
                .setScore(30)
                .setGold(10)
                .build();
        assertEquals(true, healthGoal.isFulfilled(player));
    }

    /**
     * Tests that an instance of Health Goal is not fulfilled.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void healthGoalsAreNotFulfilled(){
        HealthGoal healthGoal = new HealthGoal(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(9)
                .build();
        assertEquals(false, healthGoal.isFulfilled(player));
    }

    /**
     * Test that the class Health Goal is able to handle creation of an instance
     * where the health provided is less than 0.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfHealthGoalWithIllegalArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    HealthGoal healthGoal = new HealthGoal(-5);
                });
    }
}