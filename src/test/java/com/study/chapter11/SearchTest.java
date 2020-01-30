package com.study.chapter11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.study.util.ContainsMatches.containsMatches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.*;
import java.util.logging.*;

class SearchTest {

    private static final String A_TITLE = "1";
    private InputStream stream;

    @BeforeEach
    public void turnOffLogging() {
        Search.LOGGER.setLevel(Level.OFF);
    }

    @AfterEach
    public void closeResources() throws IOException {
        stream.close();
    }

    @Test
    public void returnsMatchesShowingContextWhenSearchStringInContent(){
            stream = streamOn();
            Search search = new Search(stream, "practical joke", A_TITLE);
            search.setSurroundingCharacterCount(10);

            search.execute();

            assertThat(search.getMatches(), containsMatches(new Match[] {
                new Match(A_TITLE, "practical joke",
                        "or a vast practical joke, though t") }));
    }

    private InputStream streamOn() {
        return new ByteArrayInputStream(("There are certain queer times and occasions " +
                "in this strange mixed affair w call life when a man takes this whole" +
                " universe for a vast practical joke, though the wit thereof he but " +
                "dimly discerns, and more than suspects that the joke is at nobody's" +
                " expense but his own.").getBytes());
    }

    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent()
        throws IOException {

        URLConnection connection =
                new URL("http://bit.ly/15sYPA7").openConnection();
        stream = connection.getInputStream();
        Search search = new Search(stream, "smelt", A_TITLE);

        search.execute();

        assertTrue(search.getMatches().isEmpty());
    }
}
