package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a passage, a smaller section of a story or a chapter.
 * It is possible to transfer between passages with a link.
 * A passage consists of a title and content.
 * The title works as a description and as an identifier.
 * Content is a text which represents a chapter or a section of a dialog.
 *
 * @author mappe_08
 * @version 22/05/2023
 */

public class Passage {
  private String title;
  private String content;
  private final List<Link> links;

  /**
   * Creates an instance of a passage.
   *
   * @param title   the title of a passage.
   * @param content the content of a passage.
   */
  public Passage(String title, String content) {
    this.setTitle(title);
    this.setContent(content);
    this.links = new ArrayList<>();
  }

  /**
   * Sets the title of the Passage. The title can not be null.
   *
   * @param title the title of a passage.
   */
  private void setTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("The title can not be null or blank.");
    }
    this.title = title;
  }

  /**
   * Sets the content of the Passage. The content can not be null or blank.
   *
   * @param content the content of a passage.
   */
  private void setContent(String content) {
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("The content can not be null or blank.");
    }
    this.content = content;
  }

  /**
   * Returns the title of the passage.
   *
   * @return the title of the passage.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the content of the passage.
   *
   * @return the content of the passage.
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Adds a link. The link can not be null. If a link is added, true is returned. False, if not.
   *
   * @param link The link to be added.
   */
  public void addLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("The link can not be null.");
    }
    this.links.add(link);
  }

  /**
   * Returns the list of links, which contains all links to a passage.
   *
   * @return the links to a passage.
   */
  public List<Link> getLinks() {
    return this.links;
  }

  /**
   * Checks if a passage has links.
   *
   * @return True if the passage has links. False if not.
   */
  public boolean hasLinks() {
    boolean foundLinks = false;

    Iterator<Link> it = this.links.iterator();
    if (it.hasNext()) {
      foundLinks = true;
    }

    return foundLinks;
  }

  /**
   * Returns a string representation of the passage.
   *
   * @return a string representation of the passage.
   */
  @Override
  public String toString() {
    return "Passage{"
        + "title='" + title + '\''
        + ", content='" + content + '\''
        + '}';
  }

  /**
   * Test for content equality between two objects.
   *
   * @param other the object to compare to this one.
   * @return true if the argument object is a set of contact details with matching attributes.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Passage passage) {
      return title.equals(passage.title)
          && content.equals(passage.content)
          && links.equals(passage.links);
    } else {
      return false;
    }
  }

  /**
   * Compute a hashcode using the rules to be found in
   * "Effective Java", by Joshua Bloch.
   *
   * @return A hashcode for passage.
   */
  @Override
  public int hashCode() {
    int code = 17;
    code = 37 * code + title.hashCode();
    code = 37 * code + content.hashCode();
    code = 37 * code + links.hashCode();
    return code;
  }
}



