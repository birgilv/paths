package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions.GoldAction;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions.HealthAction;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions.InventoryAction;
import no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model.actions.ScoreAction;


/**
 * The story file handler is responsible for handling the files,
 * which includes both saving and writing stories to local files.
 * The file handler reads and writes stories corresponding to the paths-format.
 * Each story begins with a story title.
 * The first passage in the story is the opening passage.
 * This and the following passages follows a given pattern:
 * <ul>
 *     <li>::<em> The passage title</em></li>
 *     <li><em> Passage content </em></li>
 *     <li>[<em> Link text </em>](<em> Link reference </em>)</li>
 * </ul>
 *
 * @author mappe_08
 * @version 22/05/2023
 */

public class StoryFileHandler {
  private final List<String> inventoryInStory = new ArrayList<>();

  /**
   * Creates an instance of the story file handler.
   */
  public StoryFileHandler() {
    // Is empty because there is no specific initialization or setup required when
    // creating an instance of the class.
  }

  /**
   * This method saves a story of the game, and creates a file with the file ending
   * .paths with a file-writer.
   * The filename is set to [the story-title].paths.
   * The file-writer writes the story-title first and then
   * the opening passage. For every passage in the list of passages
   * the file-writer generates the passage as string,
   * and add the written passage in a list of saved passages.
   * This reassures no duplicate passages in the file.
   * If the saving was not successful, a warning dialog will be displayed to the user.
   *
   * @param story the story to save.
   * @return true, if the story was saved. False, if not.
   */
  public boolean saveStory(Story story) {
    boolean saveStorySuccess = false;
    try (FileWriter fileWriter = new FileWriter(
        "src/main/resources/storyFiles/" + story.getTitle() + ".paths")) {
      ;
      fileWriter.write(story.getTitle() + "\n");
      Passage openingPassage = story.getOpeningPassage();
      fileWriter.write(generatePassageAsString(openingPassage));
      ArrayList<Passage> savePassages = new ArrayList<>();

      for (Passage passage : story.getPassages().values()) {

        if (!passage.equals(openingPassage)) {
          if (!savePassages.contains(passage)) {
            fileWriter.write(generatePassageAsString(passage));
            savePassages.add(passage);
          }
        }
      }

      saveStorySuccess = true;
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Error");
      alert.setHeaderText("Could not save file");
      alert.setContentText("Something is wrong with this file\n"
          + "It can not be saved.");

      Optional<ButtonType> result = alert.showAndWait();

    }
    return saveStorySuccess;
  }

  /**
   * Returns a string representation of the passage. For every link in the passage,
   * generate link as string and add it to the passage string.
   *
   * @param passage the passage to generated as string.
   * @return a string representation of the passage.
   */
  private String generatePassageAsString(Passage passage) {
    StringBuilder builder = new StringBuilder("\n::" + passage.getTitle());
    builder.append("\n" + passage.getContent() + "\n");
    for (Link link : passage.getLinks()) {
      builder.append(generateLinkAsString(link));
    }
    return builder.toString();
  }

  /**
   * Returns a string representation of the link. For every action in the link,
   * generate action as string and add it to the link string.
   *
   * @param link the link to generated as string.
   * @return a string representation of the link.
   */
  private String generateLinkAsString(Link link) {
    StringBuilder builder = new StringBuilder("[" + link.getText() + "]");
    builder.append("(" + link.getReference() + ")");
    for (Action action : link.getActions()) {
      builder.append(generateActionAsString(action));
    }
    return builder + "\n";
  }

  /**
   * Returns a string representation of the action. Identification of the action type and value.
   *
   * @param actionType the action to generated as string.
   * @return a string representation of the action.
   */
  private String generateActionAsString(Action actionType) {
    String actionTypeTxt = actionType.getClass().getSimpleName();
    String actionValueTxt = "";
    switch (actionTypeTxt) {
      case "GoldAction":
        actionValueTxt = actionType.toString();
        break;
      case "HealthAction":
        actionValueTxt = actionType.toString();
        break;
      case "InventoryAction":
        actionValueTxt = actionType.toString();
        break;
      case "ScoreAction":
        actionValueTxt = actionType.toString();
        break;
      default:
        // Handle the case when the actionTypeTxt doesn't match any of the defined cases
        actionValueTxt = "Unknown action type";
        break;
    }
    return "]" + actionTypeTxt + "," + actionValueTxt;
  }

  /**
   * This method reads a story from a given file.
   * The file must be written in the format of .paths and consists
   * of one story. If the story is blank, a warning dialog is displayed to the user.
   * This informs the user that the selected story can not be read.
   * If the story is not blank the story will be parsed and returned.
   *
   * @param fileName the name of the file to be read.
   * @return the story retrieved from the file.
   */
  public Story readStory(Path fileName) throws IOException {
    Story story = null;

    String storyBlock = Files.readString(fileName);
    if (storyBlock.isBlank()) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Error");
      alert.setHeaderText("Could not read file");
      alert.setContentText("Something is wrong with this file.\n"
          + "Check if the file follows the '.paths'-format");

      alert.showAndWait();

    } else {
      story = parseStory(storyBlock);
    }

    return story;
  }

  /**
   * Parsing the text from the file, with a scanner.
   * The scanner reads the story title which is the first line in the file.
   * Then search for the pattern '::' which represents one passage.
   * The first found passage is set as the opening passage of the story.
   * When the opening passage is first found a story is created with the story
   * title and opening passage.
   * The rest of the passages will be parsed and added to the list of passages.
   *
   * @param storyBlock the text from the file.
   * @return the story collected from the file.
   */
  private Story parseStory(String storyBlock) {
    List<Passage> passageList = new ArrayList<>();
    Scanner inputScanner = new Scanner(storyBlock);
    String storyTitle = inputScanner.nextLine();

    inputScanner.useDelimiter("::");
    Story story = null;
    boolean foundOpeningPassage = false;
    inputScanner.next(); // Skip empty line
    while (inputScanner.hasNext()) {
      String passageBlock = inputScanner.next();
      Passage passage = parsePassage(passageBlock);
      passageList.add(passage);
      if (!foundOpeningPassage) {
        story = new Story(storyTitle, passage);
        foundOpeningPassage = true;
      }
    }
    for (Passage passage : passageList) {
      for (Link link : passage.getLinks()) {
        Optional<Passage> passageLink = passageList.stream()
            .filter(passage1 -> passage1.getTitle().equals(link.getReference())).findFirst();
        if (passageLink.isPresent()) {
          story.addPassage(passageLink.get(), link);
        } else {
          story.addPassage(null, link);
        }
      }
    }
    return story;
  }

  /**
   * Parsing the text from the file, divided into smaller parts where each part is one passage.
   * The block is split by new line, where the first line is the passage title
   * and the second is the passage content. This creates a new passage.
   * If the passage block contains more lines, indicates that the passage has links. Each line
   * is parsed with parse link, and added to the passage.
   *
   * @param passageBlock the text from the passage part of the text.
   * @return the passage retrieved from the text.
   */
  private Passage parsePassage(String passageBlock) {
    String[] lines = passageBlock.split("\\r?\\n");
    String title = lines[0];
    String content = lines[1];
    Passage passage = new Passage(title, content);
    if (lines.length > 2) {
      for (int i = 2; i < lines.length; i++) {
        String linkBlock = lines[i];
        if (!linkBlock.isBlank()) {
          Link link = parseLink(linkBlock);
          passage.addLink(link);
        }
      }
    }
    return passage;
  }

  /**
   * Parsing the text from the file, each passage part is divided into smaller parts,
   * where each part is one link to the passage. This block is split by the symbol ']'.
   * The first line is the link text and the second is the reference.
   * This creates a new link. If the block contains more than 2 lines,
   * the link has actions. Each new line is parsed with parse action and added to the link.
   *
   * @param linkBlock the text from the link part of the text.
   * @return the link retrieved from the text.
   */
  private Link parseLink(String linkBlock) {
    String[] lines = linkBlock.split("]");
    String text = lines[0].substring(1);
    String reference = lines[1].substring(1, lines[1].length() - 1);

    Link link = new Link(text, reference);
    if (lines.length > 2) {
      for (int i = 2; i < lines.length; i++) {
        String actionBlock = lines[i];
        if (!actionBlock.isBlank()) {
          Action action = parseAction(actionBlock);
          link.addAction(action);
        }
      }
    }

    return link;
  }

  /**
   * Parsing the text from the file, each link can contain one or more action.
   * Such as gold-, health-, inventory- and score actions.
   * All actions except inventory action takes integers, while inventory
   * takes strings. The block is split by the symbol ','.
   * The first line is the action type and the second is the value of the action.
   * The action type is identified by a switch case. An action is created
   * by the action type and value.
   *
   * @param actionBlock the text from the action part of the text.
   * @return the action retrieved from the text.
   */
  private Action parseAction(String actionBlock) {
    String[] lines = actionBlock.split(",");
    String actionType = lines[0];
    String actionValue = lines[1];

    Action action = null;
    switch (actionType) {
      case "GoldAction":
        int goldValue = Integer.parseInt(actionValue);
        action = new GoldAction(goldValue);
        break;
      case "HealthAction":
        int healthValue = Integer.parseInt(actionValue);
        action = new HealthAction(healthValue);
        break;
      case "InventoryAction":
        action = new InventoryAction(actionValue);
        if (!this.inventoryInStory.contains(actionValue.strip().toLowerCase())){
          this.inventoryInStory.add(actionValue.strip().toLowerCase());
        }
        break;
      case "ScoreAction":
        int scoreValue = Integer.parseInt(actionValue);
        action = new ScoreAction(scoreValue);
        break;
      default:
        // Handle the case when actionType doesn't match any of the defined cases
        throw new IllegalArgumentException("Unknown action type: " + actionType);
    }
    return action;
  }

  /**
   * Returns the list of items in the parsed story.
   *
   * @return the list of items in the parsed story.
   */
  public List<String> getInventoryInStory() {
    return this.inventoryInStory;
  }
}
