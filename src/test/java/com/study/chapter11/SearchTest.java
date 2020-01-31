package com.study.chapter11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.study.util.ContainsMatches.containsMatches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
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
            stream = streamOn("rest of text here"
                                + "1234567890search term1234567890"
                                + "more rest of text");
            Search search = new Search(stream, "search term", A_TITLE);
            search.setSurroundingCharacterCount(10);

            search.execute();

            assertThat(search.getMatches(), containsMatches(new Match[] {
                new Match(A_TITLE, "search term",
                        "1234567890search term1234567890") }));
    }

    private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
    }

    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent() {
        stream = streamOn("any text");
        Search search = new Search(stream, "text that doesn't match", A_TITLE);

        search.execute();

        assertTrue(search.getMatches().isEmpty());
    }

    @Test
    public void returns_Errored_When_UnableToRead_Stream() {
        stream = create_Stream_Throwing_Error_WhenRead();
        Search search = new Search(stream, "", "");

        search.execute();

        assertTrue(search.errored());
    }

    private InputStream create_Stream_Throwing_Error_WhenRead() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };
    }

    @Test
    public void errored_returns_false_when_read_succeeds() {
        stream = streamOn("");
        Search search = new Search(stream, "", "");

        search.execute();

        assertFalse(search.errored());
    }

}
