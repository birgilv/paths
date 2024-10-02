package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Passage;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Story;
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
 *          <dd>- Creation of goal with valid arguments as int</dd>
 *          <dd>- Creation of goal with valid arguments as a list</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of goal with arguments are empty</dd>
 *          <dd>- Creation of goal with arguments are null</dd>
 *          <dd>- Creation of goal with negative goal value</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 **/
class GoalFactoryTest {
    private List<String> list = new ArrayList<>();

    /**
     * Tests that an instance of Goal is correctly created  with the Goal
     * Factory when the parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArgumentsAsInt(){
        HealthGoal healthGoal = new HealthGoal(5);
        assertEquals(healthGoal.toString(),GoalFactory.createGoal("Health Goal",5).toString());
    }

    /**
     * Tests that an instance of Goal is correctly created  with the Goal
     * Factory when the parameters given to the constructor are valid.
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArgumentsAsString(){
        list.add("item");
        InventoryGoal inventoryGoal = new InventoryGoal(list);
        assertEquals(inventoryGoal.toString(),GoalFactory.createGoal("Inventory Goal",list).toString());
    }

    /**
     * Test that the class Goal Factory is able to handle creation of a goal
     * where the type provided is invalid.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfGoalWithInvalidGoalType(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    GoalFactory.createGoal("Goal",5).toString();
                });
    }

    /**
     * Test that the class Goal Factory is able to handle creation of a goal
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
    public void creationOfGoalWithValueProvidedIsEmpty(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    GoalFactory.createGoal("Inventory Goal",list);
                });
    }

    /**
     * Test that the class Goal Factory is able to handle creation of a goal
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
    public void creationOfGoalWithValueProvidedIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    GoalFactory.createGoal("Inventory Goal",null);
                });
    }

    /**
     * Test that the class Goal Factory is able to handle creation of a goal
     * where the value provided lower than 0.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfGoalWithValueProvidedIsLowerThan0(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> {
                    GoalFactory.createGoal("Health Goal",-5);
                });
    }

}