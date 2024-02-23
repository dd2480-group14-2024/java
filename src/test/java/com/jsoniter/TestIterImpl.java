package com.jsoniter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestIterImpl {

    @Test
    public void testReadStringSlowPathEscapedb() throws Exception {
        byte[] jsonBytes = "\\b{\"key\":\"\\b\"}".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int res = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(res, 2);
    }

    @Test
    public void testReadStringSlowPathEscapedn() throws Exception {
        byte[] jsonBytes = "\\n{\"key\":\"\\n\"}".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int res = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(res, 2);
    }

    @Test
    public void testReadStringSlowPathEscapedf() throws Exception {
        byte[] jsonBytes = "\\f{\"key\":\"\\f\"}".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int res = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(res, 2);
    }

    @Test
    public void testReadStringSlowPathEscapedr() throws Exception {
        byte[] jsonBytes = "\\r{\"key\":\"\\r\"}".getBytes();
        JsonIterator iter = JsonIterator.parse(jsonBytes);

        int res = IterImpl.readStringSlowPath(iter, 0);

        assertEquals(res, 2);
    }
}
