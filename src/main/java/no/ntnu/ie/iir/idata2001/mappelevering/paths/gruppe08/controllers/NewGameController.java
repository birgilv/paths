package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers;

import java.util.List;
import java.util.Optional;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Game;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.dialog.GoalDialog;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.dialog.PlayerDialog;

/**
 * <p>Represents the <b>controller</b> for the New Game Window,
 * which is responsible for handling all events from
 * the {@link no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.window.NewGameWindow}-
 * class.</p>
 *
 * @author mappe_08
 * @version 22/05/2023
 */

public class NewGameController {
  private final PathsApp pathsApp;

  /**
   * Creates an instance of the controller.
   *
   * @param pathsApp the corresponding view-instance.
   */
  public NewGameController(PathsApp pathsApp) {
    this.pathsApp = pathsApp;
  }

  /**
   * Starts the game. This includes setting the game by retrieving the player,
   * story and goals from pathsApp.
   * This also includes changing scene to paths game, display opening passage and
   * create following link buttons.
   */
  public void doStartGame() {
    this.pathsApp.setGame(new Game(this.pathsApp.getPlayer(),
        this.pathsApp.getStory(), this.pathsApp.getGoals()));
    this.pathsApp.changeSceneToPathsGameWindow();
    this.pathsApp.getPathsGameWindow().updatePassage(this.pathsApp.getGame().begin());
    this.pathsApp.getPathsGameWindow().createLinkButtons(this.pathsApp.getGame().begin());
  }


  /**
   * Displays a Player Input dialog.
   */
  public void createPlayerDialog() {
    PlayerDialog playerDialog = new PlayerDialog();
    Optional<Player> result = playerDialog.showAndWait();

    if (result.isPresent()) {
      Player newPlayer = result.get();
      this.pathsApp.setPlayer(newPlayer);
      this.pathsApp.createStartPlayer();
    }
  }

  /**
   * Displays a Goals Input dialog.
   */
  public void createGoalsDialog() {
    GoalDialog goalDialog = new GoalDialog(this.pathsApp);
    Optional<List<Goal>> result = goalDialog.showAndWait();

    if (result.isPresent()) {

      List<Goal> newGoal = result.get();
      this.pathsApp.setGoals(newGoal);
    }
  }
}
