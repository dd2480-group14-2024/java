package com.jsoniter;

import org.junit.Test;

import com.jsoniter.spi.JsonException;

public class TestIterImpl {

    @Test(expected = JsonException.class)
    public void testReadStringSlowPathEscapedb() throws Exception {
        byte[] jsonBytes = { 92 , 98 };
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        IterImpl.readStringSlowPath(iter, 0);
    }

    @Test(expected = JsonException.class)
    public void testReadStringSlowPathEscapedn() throws Exception {
        byte[] jsonBytes = { 92 , 110 };
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        IterImpl.readStringSlowPath(iter, 0);
    }

    @Test(expected = JsonException.class)
    public void testReadStringSlowPathEscapedf() throws Exception {
        byte[] jsonBytes = { 92 , 102 };
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        IterImpl.readStringSlowPath(iter, 0);
    }

    @Test(expected = JsonException.class)
    public void testReadStringSlowPathEscapedr() throws Exception {
        byte[] jsonBytes = { 92 , 114 };
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        IterImpl.readStringSlowPath(iter, 0);
    }
}
