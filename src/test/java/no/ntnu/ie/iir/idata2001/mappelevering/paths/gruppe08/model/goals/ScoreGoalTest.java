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
 *          <dd>- Checks if score goal is fulfilled</dd>
 *          <dd>- Checks if score goal is not fulfilled</dd>
 *     <dt>Negative tests:</dt>
 *  *       <dd>- Creation of instance with invalid arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 **/
class ScoreGoalTest {

    /**
     * Tests that an instance of Score Goal is correctly created when the
     * parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        ScoreGoal scoreGoal = new ScoreGoal(5);
        assertEquals("5", scoreGoal.toString());
    }

    /**
     * Tests that an instance of Score Goal is fulfilled.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void scoreGoalsAreFulfilled(){
        ScoreGoal scoreGoal = new ScoreGoal(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(10)
                .build();
        assertEquals(true, scoreGoal.isFulfilled(player));
    }

    /**
     * Tests that an instance of Score Goal is not fulfilled.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void scoreGoalsAreNotFulfilled(){
        ScoreGoal scoreGoal = new ScoreGoal(10);
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(9)
                .setGold(9)
                .build();
        assertEquals(false, scoreGoal.isFulfilled(player));
    }

    /**
     * Test that the class Score Goal is able to handle creation of an instance
     * where the score provided is less than 0.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfScoreGoalWithIllegalArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    ScoreGoal scoreGoal = new ScoreGoal(-5);
                });
    }
}