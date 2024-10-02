package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.window;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers.PathsGameController;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Link;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Passage;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.StoryFileHandler;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;


/**
 * Responsible for creating the window where the game is going to be played.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class PathsGameWindow extends BorderPane {
  private final PathsApp pathsApp;
  private StoryFileHandler storyFileHandler;
  private final PathsGameController pathsGameController;
  private Label currentPassageTitle;
  private Label currentPassageContent;
  private VBox gameButtons;
  private HBox scoreBoard;
  private Label playerHealthLabel;
  private String playerHealth;
  private Label playerScoreLabel;
  private String playerScore;
  private Label playerGoldLabel;
  private String playerGold;
  private Label playerInventoryLabel;
  private String playerInventory;
  private ToggleButton miniGameOn;
  private ToggleButton miniGameOff;

  /**
   * Crates an instance of the game window.
   *
   * @param pathsApp the corresponding view-instance
   */
  public PathsGameWindow(PathsApp pathsApp) {
    super();
    this.pathsApp = pathsApp;
    this.pathsGameController = new PathsGameController(this.pathsApp);
    this.buildPathsGameWindow();
    getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
  }

  /**
   * Responsible for building the Paths game window with gui elements.
   */
  public void buildPathsGameWindow() {
    this.storyFileHandler = new StoryFileHandler();

    Label pathsGameLabel = new Label(this.pathsApp.setStoryTitleLabel());
    pathsGameLabel.setId("pathsGameLabel");
    this.setTop(pathsGameLabel);
    BorderPane.setMargin(pathsGameLabel, new Insets(10, 10, 10, 10));

    FlowPane gameArea = new FlowPane();
    gameArea.setId("gameArea");
    currentPassageTitle = new Label();
    currentPassageTitle.setId("currentPassageTitle");
    currentPassageContent = new Label();
    currentPassageContent.setId("currentPassageContent");
    currentPassageContent.wrapTextProperty().set(true);
    currentPassageContent.setMaxWidth(300);
    VBox passageText = new VBox();
    passageText.getChildren().addAll(currentPassageTitle, currentPassageContent);
    gameArea.getChildren().addAll(passageText);
    gameArea.setPadding(new Insets(10, 10, 10, 10));
    this.setLeft(gameArea);
    BorderPane.setMargin(gameArea, new Insets(10, 10, 10, 10));

    gameButtons = new VBox(20);
    this.setCenter(gameButtons);
    BorderPane.setMargin(gameButtons, new Insets(10, 10, 10, 10));
    gameButtons.setAlignment(Pos.CENTER);

    createScoreBoard();
    createMiniGameToggleSwitch();
    HBox miniGameBox = new HBox(20);
    Label mineGameToggleTxt = new Label("Activate mini game:");
    VBox rightSide = new VBox(50);
    miniGameBox.getChildren().addAll(mineGameToggleTxt, miniGameOn, miniGameOff);
    rightSide.getChildren().addAll(scoreBoard, miniGameBox);
    this.setRight(rightSide);
    Label scoreBoardLabel = new Label("SCOREBOARD");
    BorderPane.setMargin(scoreBoardLabel, new Insets(10, 10, 10, 10));
    BorderPane.setAlignment(scoreBoardLabel, Pos.CENTER);

    Button restartButton = new Button("RESTART");
    restartButton.setId("restartButton");
    restartButton.setOnAction(event -> this.pathsGameController.restartGame());
    Button exitButton = new Button("EXIT");
    exitButton.setId("exitButton");
    exitButton.setOnAction(event -> this.pathsGameController.doExitGame());
    Button saveButton = new Button("SAVE");
    saveButton.setId("saveButton");
    HBox bottomButtons = new HBox(20);
    saveButton.setOnAction(event -> this.pathsGameController.doSaveGame());
    bottomButtons.getChildren().addAll(restartButton, exitButton, saveButton);
    this.setBottom(bottomButtons);
    bottomButtons.setAlignment(Pos.TOP_RIGHT);
    BorderPane.setMargin(bottomButtons, new Insets(10, 10, 10, 10));
  }

  public void updatePassage(Passage passage) {
    currentPassageTitle.setText(passage.getTitle());
    currentPassageContent.setText(passage.getContent());
  }

  /**
   * This method creates the links to each passage as buttons.
   *
   * @param passage the passage link's to make buttons.
   */
  public void createLinkButtons(Passage passage) {
    this.gameButtons.getChildren().clear();
    for (Link link : passage.getLinks()) {
      Button button = new Button(link.getReference());
      button.setId("linkButtons");
      button.setOnAction(event -> this.pathsGameController.followLink(link));
      this.gameButtons.getChildren().add(button);
    }

  }

  /**
   * This method creates the scoreboard for the player. This includes player information and goals.
   */
  public void createScoreBoard() {
    this.scoreBoard = new HBox();

    HBox playerName = new HBox();
    Label playerNameTxt = new Label("Name: ");
    Label playerNameValue = new Label(this.pathsApp.getPlayer().getName());
    playerNameTxt.setMinWidth(100);
    playerNameValue.setMinWidth(100);
    playerName.getChildren().addAll(playerNameTxt, playerNameValue);

    Label playerHealthTxt = new Label("Health: ");
    this.playerHealth = this.pathsApp.getPlayer().getHealthAsString();
    this.playerHealthLabel = new Label(this.playerHealth);
    playerHealthTxt.setMinWidth(100);
    playerHealthLabel.setMinWidth(100);
    HBox playerHealthArea = new HBox();
    playerHealthArea.getChildren().addAll(playerHealthTxt, playerHealthLabel);

    Label playerScoreTxt = new Label("Score: ");
    this.playerScore = this.pathsApp.getPlayer().getScoreAsString();
    this.playerScoreLabel = new Label(this.playerScore);
    playerScoreTxt.setMinWidth(100);
    playerScoreLabel.setMinWidth(100);
    HBox playerScoreArea = new HBox();
    playerScoreArea.getChildren().addAll(playerScoreTxt, playerScoreLabel);

    Label playerGoldTxt = new Label("Gold: ");
    this.playerGold = this.pathsApp.getPlayer().getGoldAsString();
    this.playerGoldLabel = new Label(this.playerGold);
    playerGoldTxt.setMinWidth(100);
    playerGoldLabel.setMinWidth(100);
    HBox playerGoldArea = new HBox();
    playerGoldArea.getChildren().addAll(playerGoldTxt, playerGoldLabel);

    Label playerInventoryTxt = new Label("Inventory: ");
    this.playerInventory = this.pathsApp.getPlayer().getInventoryAsString();
    this.playerInventoryLabel = new Label(this.playerInventory);
    playerInventoryTxt.setMinWidth(100);
    playerInventoryLabel.setMinWidth(100);
    HBox playerInventoryArea = new HBox();
    playerInventoryArea.getChildren().addAll(playerInventoryTxt, playerInventoryLabel);
    VBox playerInformation = new VBox();
    playerInformation.getChildren()
        .addAll(playerName, playerHealthArea, playerScoreArea, playerGoldArea, playerInventoryArea);

    HBox goalHealth = new HBox();
    Label goalHealthTxt = new Label("Health: ");
    Label goalHealthValue = new Label(this.pathsApp.getGoal("HealthGoal").toString());
    goalHealthTxt.setMinWidth(100);
    goalHealthValue.setMinWidth(100);
    goalHealth.getChildren().addAll(goalHealthTxt, goalHealthValue);

    HBox goalScore = new HBox();
    Label goalScoreTxt = new Label("Score: ");
    Label goalScoreValue = new Label(this.pathsApp.getGoal("ScoreGoal").toString());
    goalScoreTxt.setMinWidth(100);
    goalScoreValue.setMinWidth(100);
    goalScore.getChildren().addAll(goalScoreTxt, goalScoreValue);

    HBox goalGold = new HBox();
    Label goalGoldTxt = new Label("Gold: ");
    Label goalGoldValue = new Label(this.pathsApp.getGoal("GoldGoal").toString());
    goalGoldTxt.setMinWidth(100);
    goalGoldValue.setMinWidth(100);
    goalGold.getChildren().addAll(goalGoldTxt, goalGoldValue);

    HBox goalInventory = new HBox();
    Label goalInventoryTxt = new Label("Inventory: ");
    Label goalInventoryValue = new Label(this.pathsApp.getGoal("InventoryGoal").toString());
    goalInventoryTxt.setMinWidth(100);
    goalInventoryValue.setMinWidth(100);
    goalInventory.getChildren().addAll(goalInventoryTxt, goalInventoryValue);
    VBox goalInformation = new VBox();
    HBox goal = new HBox(new Label("Goals"));
    goalInformation.getChildren().addAll(goal, goalHealth, goalScore, goalGold, goalInventory);
    scoreBoard.getChildren().addAll(playerInformation, goalInformation);
  }

  /**
   * This method updates the scoreboard goals when the game is playing.
   */
  public void updateScoreBoard() {

    updateValue(this.playerHealth, this.playerHealthLabel,
        this.pathsApp.getPlayer().getHealthAsString());
    updateValue(this.playerGold, this.playerGoldLabel,
        this.pathsApp.getPlayer().getGoldAsString());
    updateValue(this.playerScore, this.playerScoreLabel,
        this.pathsApp.getPlayer().getScoreAsString());

    this.playerHealth = this.pathsApp.getPlayer().getHealthAsString();
    this.playerGold = this.pathsApp.getPlayer().getGoldAsString();
    this.playerScore = this.pathsApp.getPlayer().getScoreAsString();
    this.playerInventory = this.pathsApp.getPlayer().getInventoryAsString();
    this.playerInventoryLabel.setText(this.playerInventory);
  }

  /**
   * Updates the values in the scoreboard. Displays the difference in health,
   * gold and score to the user, using - and + as well as colors.
   * Red indicates loss in value, meanwhile green means value added.
   *
   * @param value    the value to be updated.
   * @param label    the label to be updated.
   * @param newValue the updated value.
   */
  private void updateValue(String value, Label label, String newValue) {
    String differenceAsString = "";
    int oldValue = Integer.parseInt(value);
    value = newValue;
    int difference = Integer.parseInt(newValue) - oldValue;
    if (difference != 0) {
      if (difference > 0) {
        differenceAsString = "+";
        label.setTextFill(Color.GREEN);
      } else {
        label.setTextFill(Color.RED);
      }
      label.setText(value + "\t" + differenceAsString + difference);
    } else {
      label.setText(value);
      label.setTextFill(Color.BLACK);
    }
  }

  /**
   * Responsible for creating a mini-game toggle switch.
   */
  private void createMiniGameToggleSwitch() {
    this.miniGameOff = new ToggleButton("Off");
    miniGameOff.setId("miniGameOff");
    this.miniGameOn = new ToggleButton("On");
    miniGameOn.setId("miniGameOn");
    ToggleGroup miniGameToggleGroup = new ToggleGroup();
    miniGameOff.setToggleGroup(miniGameToggleGroup);
    miniGameOn.setToggleGroup(miniGameToggleGroup);
  }

  /**
   * Returns true if the mini-game toggle is selected,
   * returns false if not.
   *
   * @return miniGameActivated
   */
  public boolean miniGameToggleSwitchActivate() {
    boolean miniGameActivated = false;

    if (miniGameOn.isSelected()) {
      miniGameActivated = true;
    }

    return miniGameActivated;
  }

}
