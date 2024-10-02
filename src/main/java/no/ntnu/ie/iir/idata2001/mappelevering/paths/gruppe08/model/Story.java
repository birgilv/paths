package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a story, which is an interactive, non-linear narrative which contains a
 * collection of passages.
 * The story is represented by a title, opening passage and a list of passages.
 * The title is the title of the story and is an identification for the story.
 * The opening passage is the first passage in the story and represents the start of a story.
 * The list of passages is all passages who the story contains.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class Story {
  private String title;
  private Map<Link, Passage> passages;
  private Passage openingPassage;

  /**
   * Creates an instance of a story.
   */
  public Story() {
  }

  /**
   * Creates an instance of a story with a title, opening passage and a list of all passages.
   *
   * @param title          the title of the story.
   * @param openingPassage the opening passage of the story.
   */
  public Story(String title, Passage openingPassage) {
    setTitle(title);
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  /**
   * Sets the title of a story. The title can not be null or blank.
   *
   * @param title the title to be set.
   */
  private void setTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title can not be null or blank");
    }
    this.title = title;
  }

  /**
   * Returns the title of the story.
   *
   * @return the title of the story.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the opening passage.
   *
   * @return the opening passage.
   */
  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

  /**
   * Adds a passage. If a passage is added, true is returned. False, if not.
   *
   * @param passage The passage to be added.
   * @param link    the link to the passage.
   */
  public void addPassage(Passage passage, Link link) {
    this.passages.put(link, passage);
  }

  /**
   * Returns a passage from a link.
   *
   * @param link The link connected to the passage to be returned.
   * @return The passage connected to the link.
   */
  public Passage getPassage(Link link) {
    return this.passages.get(link);
  }

  /**
   * Returns the list of passages, which contains all passages in the story.
   *
   * @return the map of passages, which contains all passages in the story.
   */
  public Map<Link, Passage> getPassages() {
    return this.passages;
  }

  /**
   * Removes a passage from list passages.
   *
   * @param link the passage to be removed.
   */
  public void removePassage(Link link) {
    this.passages.remove(link);
  }

  /**
   * Finds and returns a list of broken links.
   * A link is broken if it refers to a passage who is not in the list passages.
   *
   * @return A list of broken links.
   */
  public List<Link> getBrokenLinks() {
    Set<Link> allLinks = passages.keySet();

    Stream<Link> linksStream = allLinks.stream()
        .flatMap(link -> {
          Passage passage = passages.get(link);
          if (passage != null) {
            List<Link> passageLinks = passage.getLinks();
            return passageLinks.stream()
                .filter(passageLink -> !allLinks.contains(passageLink));
          } else {
            return Stream.of(link);
          }
        });

    List<Link> deadLinks = linksStream.collect(Collectors.toList());
    return deadLinks;
  }
}
