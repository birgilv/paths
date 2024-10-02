package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A test class for the Player class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of player with empty name</dd>
 *          <dd>- Creation of player with blank name</dd>
 *          <dd>- Creation of player with null name</dd>
 *          <dd>- Creation of player with invalid health</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 */
class PlayerTest {

    /**
     * Tests that an instance of Player is correctly created when the
     * parameters given to the builder are valid (a valid name, health, score and gold).
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Player is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments() {
        Player player = new Player.Builder()
                .setName("PlayerName")
                .setHealth(6)
                .setScore(30)
                .setGold(10)
                .build();
        assertEquals("PlayerName", player.getName());
        assertEquals(6,player.getHealth());
        assertEquals(30, player.getScore());
        assertEquals(10, player.getGold());
    }

    /**
     * Test that the class Player is able to handle creation of an instance
     * where the name provided is empty.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void testCreationOfPlayerWithEmptyName(){
                Assertions.assertThrows(IllegalArgumentException.class,
                        ()-> {
                            Player player = new Player.Builder()
                                    .setName("")
                                    .setHealth(6)
                                    .setScore(30)
                                    .setGold(10)
                                    .build();
                        });
    }

    /**
     * Test that the class Player is able to handle creation of an instance
     * where the name provided is a blank string (string of only spaces).
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void testCreationOfPlayerWithBlankName(){
                Assertions.assertThrows(IllegalArgumentException.class,
                        ()-> {
                            Player player = new Player.Builder()
                                    .setName("      ")
                                    .setHealth(6)
                                    .setScore(30)
                                    .setGold(10)
                                    .build();
                        });
    }

    /**
     * Test that the class Player is able to handle creation of an instance
     * where the name provided is {@code null}.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void testCreationOfPlayerWithNullName(){
                Assertions.assertThrows(IllegalArgumentException.class,
                        ()-> {
                            Player player = new Player.Builder()
                                    .setName(null)
                                    .setHealth(6)
                                    .setScore(30)
                                    .setGold(10)
                                    .build();
                        });
    }

    /**
     * Test that the class Player is able to handle creation of an instance
     * where the health provided is invalid (less than 0).
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     */
    @Test
    public void testCreationOfPlayerInvalidHealth(){
                Assertions.assertThrows(IllegalArgumentException.class,
                        ()-> {
                            Player player = new Player.Builder()
                                    .setName("PlayerName")
                                    .setHealth(-5)
                                    .setScore(30)
                                    .setGold(10)
                                    .build();
                        });

    }
}