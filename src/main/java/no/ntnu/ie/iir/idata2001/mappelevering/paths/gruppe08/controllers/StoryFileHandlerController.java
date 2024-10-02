package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Link;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.Story;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.StoryFileHandler;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.view.PathsApp;



/**
 * <p>Represents the <b>controller</b> for the story file handler,
 * which is responsible for handling all events from
 * the {@link StoryFileHandler}-class.</p>
 *
 * @author mappe_08
 * @version 22/05/2023
 */

public class StoryFileHandlerController {

  private final PathsApp pathsApp;
  private final StoryFileHandler storyFileHandler;
  private Path dataDirectory;
  private String fileName;
  private static final String DATA_DIRECTORY = "src/main/resources/storyFiles/";

  /**
   * Creates an instance of the controller.
   *
   * @param pathsApp the corresponding view-instance.
   */
  public StoryFileHandlerController(PathsApp pathsApp) {
    this.pathsApp = pathsApp;
    this.storyFileHandler = new StoryFileHandler();
  }

  /**
   * Create a default directory for storing data files, if the directory does not
   * already exist.
   */
  public void createDataDirectory() throws IOException {
    this.dataDirectory = Path.of(DATA_DIRECTORY);
    if (!Files.exists(this.dataDirectory)) {
      Files.createDirectory(this.dataDirectory);
    }
  }

  /**
   * Opens a paths-file. Displays a file chooser dialog to select a paths-file.
   * Then reads the selected file and retrieves the story.
   * If the story contains a broken link, a dialog is displayed with all the broken links.
   * If the story does not contain any broken links, the story collected and the scene
   * changes to the New Game Window.
   */
  public void doOpenFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Paths file");
    fileChooser.setInitialDirectory(Path.of(DATA_DIRECTORY).toFile());
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Paths Files", "*.paths")
    );

    File file = fileChooser.showOpenDialog(this.pathsApp.getStage());
    if (file != null) {
      try {
        Story story = this.storyFileHandler.readStory(file.toPath());
        if (story.getBrokenLinks().isEmpty()) {
          this.pathsApp.setStory(story);
          this.pathsApp.changeSceneToNewGameWindow();
          this.fileName = file.getName();
        } else {
          String brokenLinks = "";
          for (Link brokenLink : story.getBrokenLinks()) {
            brokenLinks += brokenLink.getText() + " : " + brokenLink.getReference()
                + "\n";
          }
          deadLinksDialog(brokenLinks, story);
        }
      } catch (IOException ex) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Could not read file");
        alert.setContentText("Something is wrong with this file.\n"
            + "Check if the file follows the '.paths'-format");

        alert.showAndWait();
      }
    }
  }

  /**
   * Displays dead-links dialog, which displays dead links to the user.
   * If the user select the remove broken links button, the passage with broken links will
   * be removed if the passage is not referenced by another passage.
   * If true, the passage is removed and the updated story will be saved.
   */
  private void deadLinksDialog(String deadLinks, Story story) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setPrefWidth(400);
    scrollPane.setPrefHeight(300);

    String contentText = "This file contains one or more dead links: \n"
        + deadLinks
        + "\n You can remove the passage with broken links, "
        + "\n and select the updated file in the main menu to "
        + "\n continue the game without any issues.\n"
        + "\n A broken link can not be removed if another passage is linked "
        + "\nto the passage you try to remove.";
    scrollPane.setContent(new Label(contentText));

    Alert alert = new Alert(Alert.AlertType.WARNING);
    ButtonType removeButton = new ButtonType("Remove broken links");
    alert.getButtonTypes().add(removeButton);
    alert.getDialogPane().setContent(scrollPane);
    alert.setTitle("DEAD LINKS WARNING");
    alert.setHeaderText("Dead links warning");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent()) {
      if (result.get() == removeButton) {
        for (Link link : story.getBrokenLinks()) {
          story.removePassage(link);
        }
        storyFileHandler.saveStory(story);
      }
    }
  }

  /**
   * Returns the story file handler.
   *
   * @return the story file handler.
   */
  public StoryFileHandler getStoryFileHandler() {
    return this.storyFileHandler;
  }
}
