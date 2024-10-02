package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.window;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.NewGameController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.PathsController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.StoryFileHandlerController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;


/**
 * This class is responsible for the new game window.
 * This window is responsible for register a new player with
 * player and goals information.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class NewGameWindow extends BorderPane {
  private final StoryFileHandlerController storyFileHandlerController;
  private final PathsController mainController;
  private final NewGameController newGameController;
  private final PathsApp pathsApp;
  private Button startButton;
  private Button playerInfoButton;
  private Button goalsInfoButton;

  /**
   * Crates an instance of the new game window.
   *
   * @param storyFileHandlerController     the StoryFileHandlerController
   * @param pathsApp the corresponding view-instance
   */
  public NewGameWindow(StoryFileHandlerController storyFileHandlerController, PathsApp pathsApp) {
    super();
    this.pathsApp = pathsApp;
    this.storyFileHandlerController = storyFileHandlerController;
    this.newGameController = new NewGameController(this.pathsApp);
    this.mainController = new PathsController(this.pathsApp);
    this.buildNewGameWindow();
    getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
  }

  /**
   * Responsible for building the new game window with gui elements.
   */
  public void buildNewGameWindow() {
    Button changeChosenFileButton = new Button("Change story");
    changeChosenFileButton.setId("changeChosenFileButton");
    changeChosenFileButton.setOnAction(event -> {
      this.storyFileHandlerController.getStoryFileHandler().getInventoryInStory().clear();
      this.storyFileHandlerController.doOpenFile();
      this.pathsApp.setStoryTitleLabel();
    });
    Label chosenStoryTxt = new Label("You've chosen the story");
    Label newGameTxt = new Label(this.pathsApp.setStoryTitleLabel());
    newGameTxt.setId("newGameLabel");
    VBox storyInfo = new VBox(20);
    storyInfo.getChildren().addAll(chosenStoryTxt, newGameTxt, changeChosenFileButton);
    this.setTop(storyInfo);
    storyInfo.setAlignment(Pos.CENTER);
    BorderPane.setMargin(storyInfo, new Insets(10, 10, 10, 10));

    this.playerInfoButton = new Button("ADD PLAYER INFORMATION");
    this.playerInfoButton.setId("playerInfoButton");
    this.playerInfoButton.setOnAction(event -> {
      this.newGameController.createPlayerDialog();
      activateStartButton();
      updateToGreenPlayerInfoButton();
    });

    this.goalsInfoButton = new Button("ADD GOALS");
    this.goalsInfoButton.setId("goalsInfoButton");
    this.goalsInfoButton.setOnAction(event -> {
      this.newGameController.createGoalsDialog();
      activateStartButton();
      updateToGreenGoalsInfoButton();
    });
    VBox addInfo = new VBox(20);
    addInfo.getChildren().addAll(this.playerInfoButton, this.goalsInfoButton);
    this.setCenter(addInfo);
    addInfo.setAlignment(Pos.CENTER);

    this.startButton = new Button("Start Game");
    this.startButton.setDisable(true);
    this.startButton.setOnAction(event -> this.newGameController.doStartGame());
    this.startButton.setId("startButton");
    this.setBottom(this.startButton);

    Button bottomLeftButton = new Button("HOW DOES THE GAME WORK");
    bottomLeftButton.setId("medium-button");
    bottomLeftButton.setOnAction(event -> mainController.helpDialog());

    HBox bottomLeftContainer = new HBox(bottomLeftButton);
    bottomLeftContainer.setAlignment(Pos.BOTTOM_LEFT);
    bottomLeftContainer.setPadding(new Insets(10));
    HBox.setHgrow(bottomLeftContainer, Priority.ALWAYS);

    HBox bottomContainer = new HBox(10, bottomLeftContainer, this.startButton);
    bottomContainer.setAlignment(Pos.CENTER_RIGHT);
    this.setBottom(bottomContainer);
    BorderPane.setAlignment(bottomContainer, Pos.TOP_RIGHT);
    BorderPane.setMargin(bottomContainer, new Insets(10, 10, 10, 10));
  }

  /**
   * Activates the start button if the user have provided player
   * and goal information.
   */
  public void activateStartButton() {
    if (this.pathsApp.getPlayer() != null && this.pathsApp.getGoals() != null) {
      this.startButton.setDisable(false);
    }
  }

  /**
   * Responsible for updating the colour of the player info button
   * if the right information about the player is provided.
   */
  public void updateToGreenPlayerInfoButton() {
    if (this.pathsApp.getPlayer() != null) {
      this.playerInfoButton.setId("greenPlayerButton");
    }
  }

  /**
   * Responsible for updating the colour if the goals info button
   * if the right information about the goals is provided.
   */
  public void updateToGreenGoalsInfoButton() {
    if (this.pathsApp.getGoals() != null) {
      this.goalsInfoButton.setId("greenGoalsButton");
    }
  }

}
