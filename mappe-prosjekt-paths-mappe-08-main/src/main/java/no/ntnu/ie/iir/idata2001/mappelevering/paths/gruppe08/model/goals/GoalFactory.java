package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals;

import java.util.List;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;

/**
 * Factory class for creating goal objects.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class GoalFactory {

  /**
   * Creates a goal object with specified goal type.
   *
   * @param goalType the type of the goal.
   * @return the created goal.
   */
  public static Goal createGoal(String goalType) {
    switch (goalType) {
      case "Gold Goal":
        return new GoldGoal(0);
      case "Health Goal":
        return new HealthGoal(0);
      case "Inventory Goal":
        return new InventoryGoal(null);
      case "Score Goal":
        return new ScoreGoal(0);
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * Creates a goal object with specified goal type and goal value as int.
   * This includes health-, gold- and score goals.
   *
   * @param goalType  the type of the goal.
   * @param goalValue the value of the goal.
   * @return the created goal.
   */
  public static Goal createGoal(String goalType, int goalValue) {
    switch (goalType) {
      case "Gold Goal":
        if(goalValue < 0){
          throw new IllegalArgumentException();
        }
        return new GoldGoal(goalValue);
      case "Health Goal":
        if(goalValue < 0){
          throw new IllegalArgumentException();
        }
        return new HealthGoal(goalValue);
      case "Inventory Goal":

        return new InventoryGoal(null);
      case "Score Goal":
        if(goalValue <= 0){
          throw new IllegalArgumentException();
        }
        return new ScoreGoal(goalValue);
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * Creates a goal object with specified goal type and goal value as a list of strings.
   * This includes inventory goals.
   *
   * @param goalType  the type of the goal.
   * @param goalValue the value of the goal.
   * @return the created goal.
   */
  public static Goal createGoal(String goalType, List<String> goalValue) {
    switch (goalType) {
      case "Gold Goal":
        return new GoldGoal(0);
      case "Health Goal":
        return new HealthGoal(0);
      case "Inventory Goal":
        if(goalValue == null || goalValue.isEmpty()){
          throw new IllegalArgumentException();
        }
        return new InventoryGoal(goalValue);
      case "Score Goal":
        return new ScoreGoal(0);
      default:
        throw new IllegalArgumentException();
    }
  }
}
