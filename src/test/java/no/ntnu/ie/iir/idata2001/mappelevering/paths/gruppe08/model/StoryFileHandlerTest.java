package no.ntnu.ie.iir.idata2001.mappelevering.paths.gruppe08.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Story file handler class.
 *
 * This following tests:
 * <dl>
 *     <dt>Negative tests:</dt>
 *          <dd>- Save story with invalid arguments</dd>
 *          <dd>- Read story with invalid arguments</dd>
 * </dl>
 *
 * @author mappe_08
 * @version 22/05/2023
 */
class StoryFileHandlerTest {
    /**
     * Test that the class Story file handler is able to handle read story of an instance
     * where the file provided is empty.
     *
     * <p>Expected behaviour: throw an illegal argument exception to
     * indicate that something went wrong.</p>
     *
     * <p>This is a typical <b>negative</b> test, since it does not test the
     * expected functionality of the class, but tests its robustness against
     * improper use.</p>
     */
    @Test
    public void readStoryWithInValidArguments(){
        StoryFileHandler storyFileHandler = new StoryFileHandler();

        Assertions.assertThrows(ExceptionInInitializerError.class,
                ()-> storyFileHandler.readStory(new File("src/test/resources/storyFiles/An Empty Story.paths").toPath()));
    }

}