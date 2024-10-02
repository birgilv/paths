package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view;

import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.PathsController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.PathsGameController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.StoryFileHandlerController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Game;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Goal;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Player;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Story;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.window.NewGameWindow;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.window.PathsGameWindow;

/**
 * A paths game application, which is a game engine for choice-based and interactive storytelling.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class PathsApp extends Application {
  private PathsController mainController;
  private PathsGameController pathsGameController;
  private StoryFileHandlerController storyFileHandlerController;
  private Player startPlayer;
  private Story story;
  private Player player;
  private List<Goal> goals;
  private Game game;
  private Stage stage;
  private PathsGameWindow pathsGameWindow;
  private Scene mainMenu;

  /**
   * Responsible for the gui elements in the starting window.
   *
   * @param primaryStage the primary stage
   */
  @Override
  public void start(Stage primaryStage) {
    this.stage = primaryStage;
    this.mainController = new PathsController(this);
    this.storyFileHandlerController = new StoryFileHandlerController(this);

    try {
      this.storyFileHandlerController.createDataDirectory();
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Error creating data directory");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
      Platform.exit();
    }

    BorderPane root = new BorderPane();

    Label welcomeLabel = new Label("WELCOME TO PATHS GAME");
    welcomeLabel.setId("welcomeLabel");
    welcomeLabel.setPadding(new Insets(20, 20, 20, 20));
    root.setTop(welcomeLabel);
    BorderPane.setAlignment(welcomeLabel, Pos.CENTER);

    Button readFileButton = new Button("CHOOSE STORY FROM PATHS FILE");
    readFileButton.setOnAction(event -> this.storyFileHandlerController.doOpenFile());
    readFileButton.setId("read-button");
    readFileButton.setPrefWidth(300);
    readFileButton.setPrefHeight(100);
    Button helpButton = new Button("HOW DOES THE GAME WORK");
    helpButton.setOnAction(event -> mainController.helpDialog());
    helpButton.setId("medium-button");

    VBox startButtons = new VBox(20);
    startButtons.getChildren().addAll(readFileButton, helpButton);
    root.setCenter(startButtons);
    startButtons.setAlignment(Pos.CENTER);
    BorderPane.setMargin(startButtons, new Insets(10, 10, 10, 10));

    mainMenu = new Scene(root, 1100, 600);
    mainMenu.getStylesheets().add("/style.css");

    primaryStage.setTitle("Paths game");
    primaryStage.setScene(mainMenu);
    primaryStage.show();
  }

  /**
   * Responsible for keeping the current window size, when
   * the application changes scenes.
   *
   * @param newScene the new scene
   */
  private void changeSceneTo(Scene newScene) {
    stage.hide();
    double currentWidth = this.stage.getWidth();
    double currentHeight = this.stage.getHeight();
    this.stage.setScene(newScene);
    this.stage.setWidth(currentWidth);
    this.stage.setHeight(currentHeight);
    stage.show();
  }

  /**
   * Responsible for changing scene to the
   * window where the user need to fill inn the
   * necessary info to play the game.
   */
  public void changeSceneToNewGameWindow() {
    NewGameWindow newGameWindow = new NewGameWindow(this.storyFileHandlerController, this);
    Scene newGameWindowScene = new Scene(newGameWindow);
    this.changeSceneTo(newGameWindowScene);
  }

  /**
   * Responsible for returning the paths game window.
   *
   * @return pathsGameWindow
   */
  public PathsGameWindow getPathsGameWindow() {
    return this.pathsGameWindow;
  }

  /**
   * Responsible for changing the scene to the
   * window where the game is played.
   */
  public void changeSceneToPathsGameWindow() {
    this.pathsGameWindow = new PathsGameWindow(this);
    Scene pathsGameWindowScene = new Scene(this.pathsGameWindow);
    this.changeSceneTo(pathsGameWindowScene);
  }

  /**
   * Responsible for changing scene back to the
   * main menu.
   */
  public void changeSceneToMainMenu() {
    this.changeSceneTo(mainMenu);
  }

  /**
   * Returns the stage.
   *
   * @return the stage
   */
  public Stage getStage() {
    return this.stage;
  }

  /**
   * Responsible for setting the story.
   *
   * @param story the story to be set
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * Returns the story.
   *
   * @return the story
   */
  public Story getStory() {
    return this.story;
  }

  /**
   * Responsible for setting the game.
   *
   * @param game the game to be set
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * Returns the game.
   *
   * @return the game
   */
  public Game getGame() {
    return this.game;
  }

  /**
   * Responsible for setting the player.
   *
   * @param player the player to be set
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Returns the player.
   *
   * @return the player
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Responsible for creating the start player.
   * Take care of the information first provided
   * of the player.
   */
  public void createStartPlayer() {
    this.startPlayer = new Player(this.player);
  }

  /**
   * Returns the start player.
   *
   * @return the start player
   */
  public Player getStartPlayer() {
    return this.startPlayer;
  }

  /**
   * Responsible for setting the goals.
   *
   * @param goals the goals to be set
   */
  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

  /**
   * Returns the goals.
   *
   * @return the goals
   */
  public List<Goal> getGoals() {
    return this.goals;
  }

  /**
   * Returns the goal type.
   *
   * @param goalType the goal type to be returned
   * @return the goal type
   */
  public Goal getGoal(String goalType) {
    switch (goalType) {
      case "GoldGoal":
        return this.goals.get(0);
      case "HealthGoal":
        return this.goals.get(1);
      case "ScoreGoal":
        return this.goals.get(2);
      case "InventoryGoal":
        return this.goals.get(3);
      default:
        throw new IllegalArgumentException("Unknown goal type: " + goalType);
    }
  }

  /**
   * Responsible for setting the story title label.
   *
   * @return the story title
   */
  public String setStoryTitleLabel() {
    String storyTitle = "";
    if (this.getStory() != null) {
      storyTitle = this.getStory().getTitle();
      if (storyTitle.isBlank()) {
        storyTitle = "No story chosen";
      }
    }
    return storyTitle;
  }

  /**
   * Returns the story file handler controller.
   *
   * @return the story file handler controller.
   */
  public StoryFileHandlerController getStoryFileHandlerController() {
    return this.storyFileHandlerController;
  }

  /**
   * The main starting method.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Responsible for exit the paths game.
   */
  @Override
  public void stop() {
    System.exit(0);
  }

}
