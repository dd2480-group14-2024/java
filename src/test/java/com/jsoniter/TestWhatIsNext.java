package com.jsoniter;

import junit.framework.TestCase;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

public class TestWhatIsNext extends TestCase {
    public void test() throws IOException {
        JsonIterator parser = JsonIterator.parse("{}");
        assertEquals(ValueType.OBJECT, parser.whatIsNext());
    }

    /**
     * Assert that parser.whatIsNext() returns BOOLEAN for the string "true"
     * 
     * @throws IOException if whatIsNext throws an IOException
     */
    public void testWhatIsNextBoolean() throws IOException {
        JsonIterator parser = JsonIterator.parse("true");
        assertEquals(ValueType.BOOLEAN, parser.whatIsNext());
    }

    /**
     * Assert that parser.whatIsNext() returns NUMBER for the string "128378"
     * 
     * @throws IOException if whatIsNext throws an IOException
     */
    public void testWhatIsNextInteger() throws IOException {
        JsonIterator parser = JsonIterator.parse("128378");
        assertEquals(ValueType.NUMBER, parser.whatIsNext());
    }

    /**
     * Assert that parser.whatIsNext() returns NUMBER for the string "1.28378"
     * 
     * @throws IOException if whatIsNext throws an IOException
     */
    public void testWhatIsNextFloat() throws IOException {
        JsonIterator parser = JsonIterator.parse("1.28378");
        assertEquals(ValueType.NUMBER, parser.whatIsNext());
    }

    /**
     * Assert that parser.whatIsNext() returns ARRAY for the string "[1,2,3]"
     * 
     * @throws IOException if whatIsNext throws an IOException
     */
    public void testWhatIsNextNull() throws IOException {
        JsonIterator parser = JsonIterator.parse("[1,2,3]");
        assertEquals(ValueType.ARRAY, parser.whatIsNext());
    }
}
