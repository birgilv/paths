package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a link. A link makes it possible to go from one passage to another.
 * Links is the connections in the story.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class Link {
  private String text;
  private String reference;
  private final List<Action> actions;

  /**
   * Creates an instance of a link. Initialize with the corresponding text and reference,
   * constructs an empty list of actions.
   *
   * @param text      the text of the link.
   * @param reference the reference of the link.
   */
  public Link(String text, String reference) {
    this.setText(text);
    this.setReference(reference);
    this.actions = new ArrayList<>();
  }

  /**
   * Sets the text of the link. The text can not be null or blank.
   *
   * @param text the text to a link.
   */
  private void setText(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("The text can not be null or blank.");
    }
    this.text = text;
  }

  /**
   * Sets the reference of the link.
   *
   * @param reference the reference of the link.
   */
  private void setReference(String reference) {
    if (reference == null || reference.isBlank()) {
      throw new IllegalArgumentException("The reference can not be null or blank.");
    }
    this.reference = reference;
  }

  /**
   * Returns the text of the link.
   *
   * @return the text of the link.
   */
  public String getText() {
    return this.text;
  }

  /**
   * Returns the reference of the link.
   *
   * @return the reference of the link.
   */
  public String getReference() {
    return this.reference;
  }

  /**
   * Adds an action to the list og actions. The action can not be null..
   *
   * @param action the action to be added.
   */
  public void addAction(Action action) { //throws DuplicateActionException
    if (action == null) {
      throw new IllegalArgumentException("The action can not be null.");
    }
    this.actions.add(action);
  }

  /**
   * Returns the list actions which contains all the actions.
   *
   * @return the list of actions.
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Test for content equality between two objects.
   *
   * @param other the object to compare to this one.
   * @return true if the argument object is a set of contact details with matching attributes.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Link link) {
      return reference.equals(link.reference);
    } else {
      return false;
    }
  }

  /**
   * Compute a hashcode using the rules to be found in
   * "Effective Java", by Joshua Bloch.
   *
   * @return A hashcode for link.
   */
  @Override
  public int hashCode() {
    int code = 17;
    code = 37 * code + text.hashCode();
    code = 37 * code + reference.hashCode();
    code = 37 * code + actions.hashCode();
    return code;
  }

  /**
   * Returns a string representation of the link.
   *
   * @return a string representation of the link.
   */
  @Override
  public String toString() {
    return "Link{"
        + "text='" + text + '\''
        + ", reference='" + reference + '\''
        + ", actions=" + actions
        + '}';
  }

}
