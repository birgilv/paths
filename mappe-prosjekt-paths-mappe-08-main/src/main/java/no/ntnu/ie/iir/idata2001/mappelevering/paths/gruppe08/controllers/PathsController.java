package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;

/**
 * <p>Represents the <b>controller</b> for the Paths window,
 * which is responsible for handling all events from
 * the {@link PathsApp}-class.</p>
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class PathsController {
  private final PathsApp pathsApp;

  /**
   * Creates an instance of the controller.
   *
   * @param pathsApp the corresponding view-instance.
   */
  public PathsController(PathsApp pathsApp) {
    this.pathsApp = pathsApp;
  }

  /**
   * Displays a Help dialog.
   */
  public void helpDialog() {
    ButtonType confirmButton = new ButtonType("Thank you");

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setPrefWidth(500);
    scrollPane.setPrefHeight(400);

    String contentText = """
      Paths is a game-engine for a choice-based and interactive storytelling.
      The game is created by passages and links between the passages.
      The link makes it possible to move from one passage to another, and makes
      the glue of the different passages. A passage is a smaller part of a story,
      such as a chapter.
      
      -- Here is a step by step guide on how to play this game --
      
      Step 1:
      Choose a story from a paths file on your computer.
      
      Step 2:
      Add the information you want your player to start with.
      You must add the following information:
      - a name
      - health
      - score
      - gold
      
      Step 3:
      Add the goals you want to achieve.
      Remember it's fun to challenge yourself with reachable goals!
      (Should be more than what you have assigned the start player to be)
      
      Step 4:
      Click on the "Start Game" button, which will be visible when
      you have assigned the right information about player and goals.
      
      Step 5:
      Start playing the game by clicking on the green link buttons.
      You will now see the displayed passages on the blue game area.
      Watch your goals and your score change on the scoreboard the right.
      
      Extra functionality
      
      Activate a mini game:
      If you want an extra challenge when playing the game,
      activate the mini game.
      
      Restart the game:
      If you want to restart the game to the beginning passage and
      the beginning values, click on the "restart" button.
      
      Save story to file:
      To save the current story, the save story function is available both
      when the game is playing and when the game is over. The story will
      then be saved in the folder "storyFile", which opens when you choose
      read story from file.
          """;

    scrollPane.setContent(new Label(contentText));

    Alert alert = new Alert(Alert.AlertType.INFORMATION, "", confirmButton);
    alert.getDialogPane().setContent(scrollPane);
    alert.setTitle("THIS IS PATHS!");
    alert.setHeaderText("HOW DOES THE GAME WORK?");
    alert.showAndWait();
  }
}
