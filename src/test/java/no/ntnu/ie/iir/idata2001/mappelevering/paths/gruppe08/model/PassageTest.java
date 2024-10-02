package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the link class.
 *
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *          <dd>- Creation of passage with links</dd>
 *          <dd>- Creation of passage with no links</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of instance with empty arguments</dd>
 *          <dd>- Creation of instance with blank arguments</dd>
 *          <dd>- Creation of instance with null arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 */

class PassageTest {
    /**
     * Tests that an instance of Passage is correctly created when the
     * parameters given to the constructor are valid (a valid passage tile and content).
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        Passage passage = new Passage("PassageTitle","PassageContent");
        assertEquals("PassageTitle",passage.getTitle());
        assertEquals("PassageContent",passage.getContent());
    }

    /**
     * Test if the passage has links.
     */
    @Test
    public void passageHasLinks(){
        Link link = new Link("LinkText","LinkReference");
        Passage passage = new Passage("PassageTitle","PassageContent");
        passage.addLink(link);
        assertEquals(true,passage.hasLinks());

    }

    /**
     * Test if the passage has no links.
     */
    @Test
    public void passageHasNoLinks(){
        Passage passage = new Passage("PassageTitle","PassageContent");
        assertEquals(false,passage.hasLinks());

    }

    /**
     * Test that the class Passage is able to handle creation of an instance
     * where the title and content provided is empty.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfInstanceWithInvalidParametersAreEmpty(){
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Passage passage = new Passage("","PassageContent");
          });
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Passage passage = new Passage("PassageTitle","");
          });
    }

    /**
     * Test that the class Passage is able to handle creation of an instance
     * where the title and content provided a blank string (string of only spaces).
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfInstanceWithInvalidParametersAreBlank(){
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Passage passage = new Passage("     ","PassageContent");
          });

      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Passage passage = new Passage("PassageTitle","   ");
          });
    }

    /**
     * Test that the class Passage is able to handle creation of an instance
     * where the title and content provided is {@code null}.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void creationOfInstanceWithInvalidParametersAreNull(){
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Passage passage = new Passage(null,"PassageContent");
          });
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Passage passage = new Passage("PassageTitle",null);
          });
    }

}