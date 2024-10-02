package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the link class.
 * This following tests:
 * <dl>
 *     <dt>Positive tests:</dt>
 *          <dd>- Creation of instance with valid arguments</dd>
 *     <dt>Negative tests:</dt>
 *          <dd>- Creation of instance with empty arguments</dd>
 *          <dd>- Creation of instance with blank arguments</dd>
 *          <dd>- Creation of instance with null arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 */
class LinkTest {
    /**
     * Tests that an instance of Link is correctly created when the
     * parameters given to the constructor are valid (a valid link text and reference).
     *
     * <p>This is a <b>positive</b> test since it tests that the class
     * Person is working according to the expected behaviour or the
     * main role/purpose of the class. I.e. verifies functionality.</p>
     */
    @Test
    public void creationOfInstanceWithValidArguments(){
        Link link = new Link("LinkText","LinkReference");
        assertEquals("LinkText",link.getText());
        assertEquals("LinkReference",link.getReference());
    }

    /**
     * Test that the class Link is able to handle creation of an instance
     * where the text and reference provided is empty.
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
            Link link = new Link("","LinkReference");
          });
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Link link = new Link("LinkText","");
          });
    }

    /**
     * Test that the class Link is able to handle creation of an instance
     * where the text and reference provided a blank string (string of only spaces).
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
              Link link = new Link("     ","LinkReference");
          });
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
              Link link = new Link("LinkText","     ");
          });
    }

    /**
     * Test that the class Link is able to handle creation of an instance
     * where the text and reference provided is {@code null}.
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
            Link link = new Link(null,"LinkReference");
          });
      Assertions.assertThrows(IllegalArgumentException.class,
          ()-> {
            Link link = new Link("LinkText",null);
          });
    }
}