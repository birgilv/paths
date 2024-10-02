package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Link;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Passage;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Story;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.StoryFileHandler;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;


/**
 * <p>Represents the <b>controller</b> for the Paths game window,
 * which is responsible for handling all events from
 * the {@link no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.window.PathsGameWindow}-
 * class.</p>
 *
 * @author mappe_08
 * @version 22/05/2023
 */

public class PathsGameController {
  private final PathsApp pathsApp;
  private StoryFileHandler storyFileHandler;

  /**
   * Creates an instance of the controller.
   *
   * @param pathsApp the corresponding view-instance.
   */
  public PathsGameController(PathsApp pathsApp) {
    this.pathsApp = pathsApp;
  }

  /**
   * Follows a link. This includes:
   * <ul>
   *     <li>Check if the "mini-game" option is selected. When true,
   *     check if mini-game is successful is also true.</li>
   *     <li>Update the scoreboard on the screen.</li>
   *     <li>Create new buttons for the new passage's links.</li>
   *     <li>Update the display to fit the new passage.</li>
   *     <li>Check if the user have won or lost the game.</li>
   * </ul>
   *
   * @param link The link to be followed.
   */
  public void followLink(Link link) {
    if (this.pathsApp.getPathsGameWindow().miniGameToggleSwitchActivate()) {
      if (diceRollingMiniGame()) {
        Passage newPassage = this.pathsApp.getGame().go(link);
        this.pathsApp.getPathsGameWindow().updateScoreBoard();
        this.pathsApp.getPathsGameWindow().createLinkButtons(newPassage);
        this.pathsApp.getPathsGameWindow().updatePassage(newPassage);
        this.checkIfGameIsWon();
        this.checkIfGameIsLost();
      }
    } else {
      Passage newPassage = this.pathsApp.getGame().go(link);
      this.pathsApp.getPathsGameWindow().updateScoreBoard();
      this.pathsApp.getPathsGameWindow().createLinkButtons(newPassage);
      this.pathsApp.getPathsGameWindow().updatePassage(newPassage);
      this.checkIfGameIsWon();
      this.checkIfGameIsLost();
    }

  }

  /**
   * Checks if the game is won. The game is won, only if all goals are fulfilled.
   * If true, a congratulation dialog is displayed.
   * When selecting the option "OK", the user saves the story to a local file.
   * The user also returns to main menu and clears the previous game.
   * The user also have an option to don't save the story, this is selected
   * when clicking the "DON'T SAVE" button.
   */
  private void checkIfGameIsWon() {
    this.storyFileHandler = new StoryFileHandler();
    boolean allGoalsAreFulFilled = this.goalsAreFulFilled();
    if (allGoalsAreFulFilled) {
      ButtonType dontSaveStoryButton = new ButtonType("DON'T SAVE");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.getButtonTypes().add(dontSaveStoryButton);
      alert.setTitle("Congratulations");
      alert.setHeaderText("You have won the game!");
      alert.setContentText("You have won the game " + this.pathsApp.getStory().getTitle() + "\n"
          + "Select OK to continue to main menu and save the game.");

      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent()) {
        if (result.get() == ButtonType.OK) {
          this.storyFileHandler.saveStory(this.pathsApp.getStory());
        }
        this.pathsApp.changeSceneToMainMenu();
        this.clearGame();
      }

    }
  }

  /**
   * Checks if the game is lost. The game is lost when one of the health-,
   * score- or gold-values is 0 or below.
   * If true, a game-over dialog is displayed. The user has two options:
   * <ul>
   *     <li>"OK", which clears the game and take them to the main manu.</li>
   *     <li>"RESTART", which restarts the game.</li>
   * </ul>
   */
  private void checkIfGameIsLost() {
    if (this.pathsApp.getPlayer().getHealth() <= 0 || this.pathsApp.getPlayer().getScore() <= 0
        || this.pathsApp.getPlayer().getGold() <= 0) {
      ButtonType restartButton = new ButtonType("RESTART");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.getButtonTypes().add(restartButton);
      alert.setTitle("Game over");
      alert.setHeaderText("You have lost the game");
      alert.setContentText("You have lost the game. \n"
          + "Select RESTART to start over or OK to continue to main menu");

      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent()) {
        if (result.get() == ButtonType.OK) {
          this.pathsApp.changeSceneToMainMenu();
          this.clearGame();
        } else {
          System.out.println("RESTART");
          restartGame();
        }
      }
    }
  }

  /**
   * Restarts the game. When restarting the user is taken back to the beginning.
   * This means they are now starting the game by the opening passage again.
   * The player is cleared, that means the health, score, gold and inventory
   * is set back to what they were in the beginning. Then the scoreboard is updated.
   */
  public void restartGame() {
    this.pathsApp.getPathsGameWindow().createLinkButtons(this.pathsApp.getGame().begin());
    this.pathsApp.getPathsGameWindow().updatePassage(this.pathsApp.getGame().begin());
    this.pathsApp.getGame().getPlayer().setName(this.pathsApp.getStartPlayer().getName());
    this.pathsApp.getGame().getPlayer().setHealth(this.pathsApp.getStartPlayer().getHealth());
    this.pathsApp.getGame().getPlayer().setScore(this.pathsApp.getStartPlayer().getScore());
    this.pathsApp.getGame().getPlayer().setGold(this.pathsApp.getStartPlayer().getGold());
    this.pathsApp.getPlayer().getInventory().clear();
    this.pathsApp.getPathsGameWindow().updateScoreBoard();
  }

  /**
   * Clears the game. Set all values in player, story and goals to either 'INVALID_[value]' or 0.
   */
  private void clearGame() {
    this.pathsApp.getGame().setPlayer(new Player.Builder()
        .setName("INVALID_NAME")
        .setHealth(0)
        .setScore(0)
        .setGold(0)
        .build());
    this.pathsApp.getGame()
        .setStory(new Story("INVALID_TITLE",
            new Passage("INVALID_TITLE", "INVALID_CONTENT")));
    this.pathsApp.getGame().setGoals(new ArrayList<>());
  }

  /**
   * Checks if all the selected goals are fulfilled.
   *
   * @return true, if all goals are fulfilled. False if not.
   */
  private boolean goalsAreFulFilled() {
    boolean allGoalsAreFulFilled = false;
    if (this.pathsApp.getGoal("HealthGoal")
        .isFulfilled(this.pathsApp.getPlayer())
        && this.pathsApp.getGoal("ScoreGoal")
            .isFulfilled(this.pathsApp.getPlayer())
        && this.pathsApp.getGoal("GoldGoal")
            .isFulfilled(this.pathsApp.getPlayer())
        && this.pathsApp.getGoal("InventoryGoal")
            .isFulfilled(this.pathsApp.getPlayer())) {
      allGoalsAreFulFilled = true;
    }
    return allGoalsAreFulFilled;
  }

  /**
   * Represents the random dice mini-game. A mini-game dialog is displayed to the user.
   * When mini-games are selected, the user must roll a 4 or higher on the dice to enter a passage.
   * The dialog explains the mini-game to the user and displays the option "ROLL".
   * If the user rolls a 4 or higher on the dice, the game continues.
   * If not, the user must roll again to achieve this requirement to get access to the passage.
   *
   * @return true, if the mini-game is completed. False if not.
   */
  private boolean diceRollingMiniGame() {
    boolean miniGameSuccess = false;
    int diceSides = 6;
    Random random = new Random();

    ButtonType rollButton = new ButtonType("Roll");
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.getButtonTypes().add(rollButton);
    alert.setTitle("Mini Game");
    alert.setHeaderText("To continue, you must succeed in the dice rolling mini game.");
    alert.setContentText("This is a dice-rolling game. "
        + "\nYou must roll 4 or higher on the dice to follow the desired link.\n"
        + "Use as many tires as you must to pass.");



    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent()) {
      if (result.get() == rollButton) {
        int roll = random.nextInt(diceSides) + 1;
        if (roll >= 4) {
          alert.setContentText("You've rolled an " + roll
              + "\nContinue to the next passage.");
          miniGameSuccess = true;
        } else {
          alert.setContentText("You've rolled an " + roll
              + ".\nThat's not enough to continue to the next passage."
              + "\nTry again.");
        }
        alert.getButtonTypes().remove(rollButton);
        alert.showAndWait();
      }
    }

    return miniGameSuccess;
  }

  /**
   * Exits the game. Displays an exit dialog, to make sure the user want to exit.
   * If selected "OK" the user
   * goes back to main menu and clears the game.
   */
  public void doExitGame() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Exit");
    alert.setHeaderText("Are you sure you want to exit the game?");
    alert.setContentText("You want to exit the game " + this.pathsApp.getStory().getTitle() + "\n"
        + "Remember to select SAVE in game window to save your story.");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent()) {
      if (result.get() == ButtonType.OK) {
        this.pathsApp.changeSceneToMainMenu();
        this.clearGame();
      }
    }
  }

  /**
   * Saves the game to a local file. Displays a save dialog to the user.
   */
  public void doSaveGame() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Save story");
    alert.setContentText(this.pathsApp.getStory().getTitle() + " is saved successfully.");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent()) {
      if (result.get() == ButtonType.OK) {
        storyFileHandler.saveStory(this.pathsApp.getStory());
      }
    }
  }
}
