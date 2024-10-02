package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the Story class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Creation of Story with opening passage</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of Story with empty title</dd>
 *          <dd>- Creation of Story with blank title</dd>
 *          <dd>- Creation of Link with null title</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 */
class StoryTest {
    /**
     * Tests that an instance of Story is correctly created when the
     * parameters given to the constructor are valid (a valid story title and passage).
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        Story story = new Story("StoryTitle",
                new Passage("PassageTitle", "PassageContent"));
        assertEquals("StoryTitle", story.getTitle());
    }

    /**
     * Test that the Story has opening passage
     */
    @Test
    public void storyHasOpeningPassage(){
        Story story = new Story("StoryTitle",
                new Passage("PassageTitle", "PassageContent"));
        assertEquals("PassageTitle", story.getOpeningPassage().getTitle());
    }


    /**
     * Test that the class Story is able to handle creation of an instance
     * where the title provided is empty.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfStoryWithEmptyTitle(){
        Assertions.assertThrows(IllegalArgumentException.class,
            ()-> {
                Story story = new Story("",
                    new Passage("PassageTitle", "PassageContent"));
            });
    }

    /**
     * Test that the class Story is able to handle creation of an instance
     * where the title provided a blank string (string of only spaces).
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfStoryWithBlankTitle(){
        Assertions.assertThrows(IllegalArgumentException.class,
            ()-> {
                Story story = new Story("       ",
                    new Passage("PassageTitle", "PassageContent"));
            });
    }

    /**
     * Test that the class Story is able to handle creation of an instance
     * where the title provided is {@code null}.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfStoryWithNullTitle(){
        Assertions.assertThrows(IllegalArgumentException.class,
            ()-> {
                Story story = new Story(null,
                    new Passage("PassageTitle", "PassageContent"));
            });
    }
}