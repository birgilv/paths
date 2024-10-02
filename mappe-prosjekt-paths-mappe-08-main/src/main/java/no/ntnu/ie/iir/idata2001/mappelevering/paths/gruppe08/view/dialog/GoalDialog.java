package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.dialog;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.goals.GoalFactory;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;

/**
 * Represents the goal dialog where the user adds goals to the game. This class extends Dialog.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class GoalDialog extends Dialog<List<Goal>> {
  private final PathsApp pathsApp;
  private List<String> inventoryInGame;
  private TextField goldTxt;
  private TextField healthTxt;
  private TextField inventoryTxt;
  private TextField scoreTxt;

  /**
   * Creates an instance of goal dialog.
   */
  public GoalDialog(PathsApp pathsApp) {
    super();
    this.pathsApp = pathsApp;
    createContent();
  }

  /**
   * Creates the content of the dialog. This includes all input fields, labels and levels as well.
   * At the top, a row of buttons is display.
   * They represent the different level for the game, which is easy, medium and hard. By
   * selecting one of these buttons, the goals will be automatically set to the selected level.
   * When the button APPLY is selected the result from the dialog is converted.
   * The input values is converted
   * to corresponding goals, and added to the list of goals.
   */
  private void createContent() {
    this.inventoryInGame =
        this.pathsApp.getStoryFileHandlerController().getStoryFileHandler().getInventoryInStory();
    setTitle("Goal information");
    setHeaderText("What are the goals for this game?\n"
        + "Remember it's fun to challenge yourself with reachable goals!\n"
        + "Inventory in this game:" + listToString(this.inventoryInGame));
    getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.APPLY);
    Button applyButton = (Button) getDialogPane().lookupButton(ButtonType.APPLY);
    applyButton.addEventFilter(ActionEvent.ACTION, event -> {
      try {
        createInventoryGoal();
      } catch (IllegalArgumentException invalidItem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(invalidItem.getMessage());
        alert.setContentText("Those items does not exist in the game, and therefor make the "
            + "game impossible to win."
            + "\n\nItems in the game: "
            + listToString(inventoryInGame)
        );
        alert.showAndWait();
        event.consume();
      }
    });

    Button easyButton = new Button("easy");
    easyButton.setOnAction(actionEvent -> this.setGoalsToEasyLevel());
    Button mediumButton = new Button("medium");
    mediumButton.setOnAction(actionEvent -> this.setGoalsToMediumLevel());
    Button hardButton = new Button("hard");
    hardButton.setOnAction(actionEvent -> setGoalsToHardLevel());

    HBox levelButtons = new HBox(20);
    levelButtons.getChildren().addAll(easyButton, mediumButton, hardButton);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    this.goldTxt = new TextField();
    this.goldTxt.setPromptText("0-100");

    this.healthTxt = new TextField();
    this.healthTxt.setPromptText("0-100");

    this.inventoryTxt = new TextField();
    this.inventoryTxt.setPromptText("Axe,Bucket,Hammer,..");

    this.scoreTxt = new TextField();
    this.scoreTxt.setPromptText("0-100");

    checkInput(this.goldTxt);
    checkInput(this.scoreTxt);
    checkInput(this.goldTxt);

    grid.add(new Label("Gold:"), 0, 0);
    grid.add(this.goldTxt, 1, 0);
    grid.add(new Label("Health:"), 0, 1);
    grid.add(this.healthTxt, 1, 1);
    grid.add(new Label("Inventory:"), 0, 2);
    grid.add(this.inventoryTxt, 1, 2);
    grid.add(new Label("Score:"), 0, 3);
    grid.add(this.scoreTxt, 1, 3);

    VBox wholeSetUp = new VBox(20);
    wholeSetUp.getChildren().addAll(levelButtons, grid);

    getDialogPane().setContent(wholeSetUp);

    setResultConverter(
        (ButtonType button) -> {
          List<Goal> goals = new ArrayList<>();
          if (button == ButtonType.APPLY) {
            int gold;
            int health;
            int score;

            try {
              gold = Integer.parseInt(goldTxt.getText());
              if (gold > 100) {
                gold = 100;
              }
              goals.add(GoalFactory.createGoal("Gold Goal", gold));
            } catch (NumberFormatException e) {
              gold = 0;
            }

            try {
              health = Integer.parseInt(healthTxt.getText());
              if (health > 100) {
                health = 100;
              }
              goals.add(GoalFactory.createGoal("Health Goal", health));
            } catch (NumberFormatException e) {
              health = 0;
            }

            try {
              score = Integer.parseInt(scoreTxt.getText());
              if (score > 100) {
                score = 100;
              }
              goals.add(GoalFactory.createGoal("Score Goal", score));

            } catch (NumberFormatException e) {
              score = 0;
            }

            String[] inventoryStrings = inventoryTxt.getText().toLowerCase().split(",");
            ArrayList<String> inventoryList = new ArrayList<>();
            List<String> invalidItems = new ArrayList<>();
            for (int i = 0; i < inventoryStrings.length; i++) {
              if (!inventoryStrings[i].isBlank()) {
                if (!checkInventory(inventoryStrings[i]).isBlank()) {
                  invalidItems.add(checkInventory(inventoryStrings[i]));
                } else {
                  inventoryList.add(inventoryStrings[i].strip());
                }
              }
            }

            goals.add(createInventoryGoal());
            return goals;
          }
          return null;
        });
  }

  /**
   * Creates an inventory goal with the valid items. If inventory goal contains an
   * invalid item an IllegalArgumentException is thrown.
   *
   * @return the created inventory goal.
   */
  private Goal createInventoryGoal() {
    String[] inventoryStrings = inventoryTxt.getText().toLowerCase().split(",");
    ArrayList<String> inventoryList = new ArrayList<>();
    for (int i = 0; i < inventoryStrings.length; i++) {
      if (!inventoryStrings[i].isBlank()) {
        if (!checkInventory(inventoryStrings[i]).isBlank()) {
          throw new IllegalArgumentException("Invalid item: " + inventoryStrings[i]);
        } else {
          inventoryList.add(inventoryStrings[i].strip());
        }
      }
    }
    return GoalFactory.createGoal("Inventory Goal", inventoryList);
  }

  /**
   * Checks if the input is greater than 0 and smaller than 100.
   * If not, the value is set to the old value.
   *
   * @param textField The text field to be checked.
   */
  private void checkInput(TextField textField) {
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException e) {
        textField.setText(oldValue);
      }
    });
  }

  /**
   * Checks if the in game inventory contains an item.
   * If the item does not exist in the game inventory, the
   * invalid item will be returned.
   *
   * @param item the item to check for.
   * @return the invalid item.
   */
  private String checkInventory(String item) {
    String invalidItem = "";
    if (!this.inventoryInGame.contains(item)) {
      invalidItem = item;
    }
    return invalidItem;
  }

  /**
   * Returns a string representation of a list.
   *
   * @param listOfStrings the list to get a string representation of.
   * @return a string representation of the list.
   */
  private String listToString(List<String> listOfStrings) {
    String listToString = "\t";
    for (String string : listOfStrings) {
      listToString += string.toLowerCase() + "\t";
    }
    if (listOfStrings.size() <= 0) {
      listToString = "Empty..";
    }
    return listToString;
  }

  /**
   * Represent the easy level.
   */
  private void setGoalsToEasyLevel() {
    this.goldTxt.setText("15");
    this.healthTxt.setText("3");
    this.scoreTxt.setText("30");
  }

  /**
   * Represent the medium level.
   */
  private void setGoalsToMediumLevel() {
    this.goldTxt.setText("30");
    this.healthTxt.setText("6");
    this.scoreTxt.setText("60");
  }

  /**
   * Represent the hard level.
   */
  private void setGoalsToHardLevel() {
    this.goldTxt.setText("45");
    this.healthTxt.setText("9");
    this.scoreTxt.setText("90");
  }
}
