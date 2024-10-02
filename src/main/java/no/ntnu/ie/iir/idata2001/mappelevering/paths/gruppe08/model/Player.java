package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The player class represents a player with different characteristics
 * that can be influenced in a story.
 * This includes a player's name, health, score, gold and inventory.
 *
 * @author mappe_08
 * @version 22/05/2023
 */
public class    Player {
  private String name;
  private int health;
  private int score;
  private int gold;
  private List<String> inventory;

  /**
   * Creates an instance of a player with name, health, score, gold and inventory.
   *
   * @param name   the name of the player.
   * @param health the health of the player.
   * @param score  the score of the player.
   * @param gold   the gold of the player.
   */
  private Player(String name, int health, int score, int gold) {
    this.setName(name);
    this.setHealth(health);
    this.score = score;
    this.gold = gold;
    this.inventory = new ArrayList<>();
  }

  /**
   * Creates an instance of a player.
   *
   * @param player the player to be created.
   */
  public Player(Player player) {
    this(player.getName(), player.getHealth(), player.getScore(), player.getGold());
  }

  /**
   * Creates an instance of a player.
   */
  private Player() {
  }

  /**
   * Builder design pattern class. Responsible for creating a player using the builder.
   */
  public static class Builder {
    private String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory;

    /**
     * Creates an instance of a builder.
     */
    public Builder() {
      this.name = "";
      this.health = 0;

    }

    /**
     * Sets the name. The name can not be null or blank.
     *
     * @param name the name to be set.
     * @return the builder.
     */
    public Builder setName(String name) {
      if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("The name can not be null or blank.");
      }
      this.name = name;
      return this;
    }

    /**
     * Sets the health. The health must be greater than 0.
     *
     * @param health the health to be set.
     * @return the builder.
     */
    public Builder setHealth(int health) {
      if (health < 0) {
        throw new IllegalArgumentException("The health must be greater than 0.");
      }
      this.health = health;
      return this;
    }

    /**
     * Sets the score. The score must be greater than 0.
     *
     * @param score the score to be set.
     * @return the builder.
     */
    public Builder setScore(int score) {
      if (score < 0) {
        throw new IllegalArgumentException("The score must be greater than 0.");
      }
      this.score = score;
      return this;
    }

    /**
     * Sets the gold. The gold must be greater than 0.
     *
     * @param gold the gold to be set.
     * @return the builder.
     */
    public Builder setGold(int gold) {
      if (gold < 0) {
        throw new IllegalArgumentException("The gold must be greater than 0.");
      }
      this.gold = gold;
      return this;
    }


    /**
     * Responsible for creating the player.
     *
     * @return the created player.
     */
    public Player build() {
      Player player = new Player();
      player.setName(this.name);
      player.setHealth(this.health);
      player.score = score;
      player.gold = gold;
      player.inventory = new ArrayList<>();
      return player;
    }
  }

  /**
   * Returns the name of the player.
   *
   * @return the name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the player. The player can not be null or blank.
   *
   * @param name the name of the player.
   */
  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("The player can not be null or blank.");
    }
    this.name = name;
  }

  /**
   * Sets the health of the player. The health must be greater than 0.
   *
   * @param health the health of the player.
   */
  public void setHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("The health must be greater than 0.");
    }
    this.health = health;
  }

  /**
   * Adds a health to the players' health. The health to be added can be both positive and negative.
   *
   * @param health the health to be added.
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /**
   * Returns the health of the player.
   *
   * @return the health of the player.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Returns a string representation of the health.
   *
   * @return a string representation of the health.
   */
  public String getHealthAsString() {
    String healthAsString = getHealth() + "";
    return healthAsString;
  }

  /**
   * Sets the score of the player. The score must be greater than 0.
   *
   * @param score the score of the player.
   */
  public void setScore(int score) {
    if (score < 0) {
      throw new IllegalArgumentException("The score must be greater than 0.");
    }
    this.score = score;
  }

  /**
   * Adds a score to the players' score. The score to be added can be both positive and negative.
   *
   * @param score the score to be added.
   */
  public void addScore(int score) {
    this.score += score;
  }

  /**
   * Returns the players score.
   *
   * @return the players score.
   */
  public int getScore() {
    return score;
  }


  /**
   * Returns a string representation of the score.
   *
   * @return a string representation of the score.
   */
  public String getScoreAsString() {
    String scoreAsString = getScore() + "";
    return scoreAsString;
  }

  /**
   * Sets the gold of the player. The gold must be greater than 0.
   *
   * @param gold the gold of the player.
   */
  public void setGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("The gold must be greater than 0.");
    }
    this.gold = gold;
  }

  /**
   * Adds gold to the players' gold. The gold to be added can be both positive and negative.
   *
   * @param gold the gold to be added.
   */
  public void addGold(int gold) {
    this.gold += gold;
  }

  /**
   * Returns the players gold.
   *
   * @return the players gold.
   */
  public int getGold() {
    return gold;
  }


  /**
   * Returns a string representation of the gold.
   *
   * @return a string representation of the gold.
   */
  public String getGoldAsString() {
    String goldAsString = getGold() + "";
    return goldAsString;
  }

  /**
   * Adds an item to the inventory list. The item can not be null or blank.
   *
   * @param item the item to be added in the inventory
   */
  public void addToInventory(String item) {
    if (item == null || item.isBlank()) {
      throw new IllegalArgumentException("The item can not be null or blank.");
    }
    this.inventory.add(item);
  }

  /**
   * Return the list of items in the inventory.
   *
   * @return the inventory list of items.
   */
  public List<String> getInventory() {
    return inventory;
  }

  /**
   * Returns a string representation of the inventory.
   * If the list of inventory is empty, the inventory is the
   * string returns 'Empty..'.
   *
   * @return a string representation of the inventory.
   */
  public String getInventoryAsString() {
    String allInventory = "";
    for (String inventory : getInventory()) {
      allInventory += inventory + "\n";
    }
    if (getInventory().size() <= 0) {
      allInventory = "Empty..";
    }
    return allInventory;
  }
}
