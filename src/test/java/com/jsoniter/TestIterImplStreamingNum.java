package com.jsoniter;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestIterImplStreamingNum { 

    // Test reading a simple integer value without any special characters or decimal points
    @Test
    public void testReadSimpleInteger() throws Exception {
        byte[] jsonBytes = "1234".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);
        IterImplForStreaming.numberChars result = IterImplForStreaming.readNumber(iter);
        assertEquals("1234", new String(result.chars, 0, result.charsLength));
        assertFalse(result.dotFound);
    }

    // Test reading a floating-point number, which includes a decimal point
    @Test
    public void testReadFloatingPointNumber() throws Exception {
        byte[] jsonBytes = "56.78".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);
        IterImplForStreaming.numberChars result = IterImplForStreaming.readNumber(iter);
        assertEquals("56.78", new String(result.chars, 0, result.charsLength));
        assertTrue(result.dotFound);
    }

    // Test to ensure that the internal buffer expands as needed when reading a long number
    @Test
    public void testReadBufferExpansionNeeded() throws Exception {
        // Assuming initial buffer size is smaller than the number length
        byte[] jsonBytes = "1234567890123456".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);
        IterImplForStreaming.numberChars result = IterImplForStreaming.readNumber(iter);
        assertEquals("1234567890123456", new String(result.chars, 0, result.charsLength));
        assertFalse(result.dotFound);
    }

    // Test reading a number that is terminated by non-numeric characters
    @Test
    public void testReadNonNumericCharactersTermination() throws Exception {
        byte[] jsonBytes = "123abc".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);
        IterImplForStreaming.numberChars result = IterImplForStreaming.readNumber(iter);
        assertEquals("123", new String(result.chars, 0, result.charsLength));
        assertFalse(result.dotFound);
    }
}