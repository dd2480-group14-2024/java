package com.jsoniter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jsoniter.spi.JsonException;

public class TestIterImpl {

    @Test
    public void testReadStringSlowPath_ValidString() throws Exception {
        byte[] jsonBytes = "{\"key\": \"Hello, World!\"}".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int result = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(1, result);
    }

    @Test
    public void testReadStringSlowPath_EscapedCharacters() throws Exception {
        byte[] jsonBytes = "\"\\n\\t\\\"\\\\\"".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int result = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(0, result);
    }

    @Test
    public void testReadStringSlowPath_UnicodeCharacters() throws Exception {
        byte[] jsonBytes = "\"Aé你😀\"".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int result = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(0, result);
    }

    @Test(expected = JsonException.class)
    public void testReadStringSlowPath_InvalidSequence() throws Exception {
        byte[] jsonBytes = "lasdkfjlösdkajflösajflöasjf02oj2093j0932jr0å123+r1å1äö13,e2-. . 24509 u234234".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        IterImpl.readStringSlowPath(iter, 0);
    }
}
