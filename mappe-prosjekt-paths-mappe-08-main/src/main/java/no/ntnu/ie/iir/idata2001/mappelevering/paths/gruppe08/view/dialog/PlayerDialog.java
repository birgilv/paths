package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.dialog;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;

/**
 * Represents the player dialog where the user adds player information to the game.
 * This class extends Dialog.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class PlayerDialog extends Dialog<Player> {

  /**
   * Creates an instance of goal dialog.
   */
  public PlayerDialog() {
    super();
    createContent();
  }

  /**
   * Creates the content of the dialog. This includes all input fields and labels.
   * When the button APPLY is selected the result from the dialog is converted.
   * The input values is converted to the corresponding player information
   * values. This values creates a new player using the builder.
   */
  private void createContent() {
    setTitle("Player information");
    setHeaderText("Fill in the values you want your player to start with");
    getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.APPLY);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    TextField name = new TextField();
    name.setPromptText("Player name");

    TextField healthTxt = new TextField();
    healthTxt.setPromptText("0-100");

    TextField scoreTxt = new TextField();
    scoreTxt.setPromptText("0-100");

    TextField goldTxt = new TextField();
    goldTxt.setPromptText("0-100");

    Button applyButton = (Button) getDialogPane().lookupButton(ButtonType.APPLY);
    applyButton.addEventFilter(ActionEvent.ACTION, event -> {
      try {
        int health = Integer.parseInt(healthTxt.getText());
        if (health > 100 || health <= 0) {
          throw new IllegalArgumentException("Health must be greater than 0, "
                  + "and 100 or less.");
        }
        int score = Integer.parseInt(scoreTxt.getText());
        if (score > 100 || score <= 0) {
          throw new IllegalArgumentException("Score must be greater than 0, "
                  + "and 100 or less.");
        }
        int gold = Integer.parseInt(goldTxt.getText());
        if (gold > 100 || gold <= 0) {
          throw new IllegalArgumentException("Gold must be greater than 0, "
                  + "and 100 or less.");
        }
      } catch (IllegalArgumentException invalidValue) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(invalidValue.getMessage());

        alert.showAndWait();
        event.consume();
      }
    });

    checkInput(healthTxt);
    checkInput(scoreTxt);
    checkInput(goldTxt);

    grid.add(new Label("Name:"), 0, 0);
    grid.add(name, 1, 0);
    grid.add(new Label("Health:"), 0, 1);
    grid.add(healthTxt, 1, 1);
    grid.add(new Label("Score:"), 0, 2);
    grid.add(scoreTxt, 1, 2);
    grid.add(new Label("Gold:"), 0, 3);
    grid.add(goldTxt, 1, 3);

    getDialogPane().setContent(grid);

    setResultConverter(
        (ButtonType button) -> {
          Player newPlayer = null;
          if (button == ButtonType.APPLY) {
            int health = Integer.parseInt(healthTxt.getText());
            int score = Integer.parseInt(scoreTxt.getText());
            int gold = Integer.parseInt(goldTxt.getText());

            newPlayer = new Player.Builder()
                .setName(name.getText())
                .setHealth(health)
                .setScore(score)
                .setGold(gold)
                .build();
          }

          return newPlayer;
        });
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
}
