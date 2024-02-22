package com.jsoniter;

import com.jsoniter.spi.OmitValue;
import com.jsoniter.spi.OmitValue.*;
import junit.framework.TestCase;
import java.lang.reflect.Type;


public class TestOmitValue extends TestCase {

    // Tests parsing a boolean type with a "true" string as the default value to omit.
    // Resulting OmitValue should omit true and not omit false.
    public void test_parseBooleanTypeWithTrueString() {
        Type valueType = boolean.class;
        String defaultValueToOmit = "true";
        OmitValue result = Parsed.parse(valueType, defaultValueToOmit);
        assertTrue(result.shouldOmit(true));
        assertFalse(result.shouldOmit(false));
    }

    // Tests parsing a Boolean type with a "false" string as the default value to omit.
    // Resulting OmitValue should omit false and not omit true.
    public void test_parseBooleanTypeWithFalseString() {
        Type valueType = Boolean.class;
        String defaultValueToOmit = "false";
        OmitValue result = Parsed.parse(valueType, defaultValueToOmit);
        assertTrue(result.shouldOmit(false));
        assertFalse(result.shouldOmit(true));
    }

    // Tests parsing an integer type with a "0" string as the default value to omit.
    // Resulting OmitValue should omit 0 and not omit other integers (e.g., 42).
    public void test_parseIntegerTypeWithZeroString() {
        Type valueType = int.class;
        String defaultValueToOmit = "0";
        OmitValue result = Parsed.parse(valueType, defaultValueToOmit);
        assertTrue(result.shouldOmit(0));
        assertFalse(result.shouldOmit(42));
    }

    // Tests parsing a float type with a "0.0F" string as the default value to omit.
    // Resulting OmitValue should omit 0.0F and not omit other floats (e.g., 3.14F).
    public void test_parseFloatTypeWithZeroString() {
        Type valueType = float.class;
        String defaultValueToOmit = "0.0F";
        OmitValue result = Parsed.parse(valueType, defaultValueToOmit);
        assertTrue(result.shouldOmit(0.0F));
        assertFalse(result.shouldOmit(3.14F));
    }

    // Tests parsing a double type with a "0.0D" string as the default value to omit.
    // Resulting OmitValue should omit 0.0D and not omit other doubles (e.g., 2.718D).
    public void test_parseDoubleTypeWithZeroString() {
        Type valueType = Double.class;
        String defaultValueToOmit = "0.0D";
        OmitValue result = Parsed.parse(valueType, defaultValueToOmit);
        assertTrue(result.shouldOmit(0.0D));
        assertFalse(result.shouldOmit(2.718D));
    }

    public void test_shouldOmitInputPositiveOutputFalse() {

        // Arrange
        final ZeroByte objectUnderTest = new ZeroByte();
        final Object val = (byte)1;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    } 

    public void test_shouldOmitInputPositiveOutputFalse2() {

        // Arrange
        final ZeroInt objectUnderTest = new ZeroInt();
        final Object val = 1;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }

    public void test_shouldOmitInputPositiveOutputFalse3() {

        // Arrange
        final ZeroLong objectUnderTest = new ZeroLong();
        final Object val = 1L;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }

    public void test_shouldOmitInputZeroOutputTrue() {

        // Arrange
        final ZeroLong objectUnderTest = new ZeroLong();
        final Object val = 0L;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(true, retval);
    }

    public void test_shouldOmitInputPositiveOutputFalse4() {

        // Arrange
        final ZeroShort objectUnderTest = new ZeroShort();
        final Object val = (short)1;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }

    public void test_shouldOmitInputTrueOutputFalse() {

        // Arrange
        final False objectUnderTest = new False();
        final Object val = true;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }

    public void test_shouldOmitInputNotNullOutputFalse() {

        // Arrange
        final ZeroChar objectUnderTest = new ZeroChar();
        final Object val = '\u0001';
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }

    public void test_shouldOmitInputPositiveOutputFalse5() {

        // Arrange
        final ZeroDouble objectUnderTest = new ZeroDouble();
        final Object val = 0x0.0000000000001p-1022 /* 4.94066e-324 */;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }

    public void test_shouldOmitInputZeroOutputTrue2() {

        // Arrange
        final ZeroDouble objectUnderTest = new ZeroDouble();
        final Object val = 0.0;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(true, retval);
    }

    public void test_shouldOmitInputPositiveOutputFalse6() {

        // Arrange
        final ZeroFloat objectUnderTest = new ZeroFloat();
        final Object val = 0x1p-149f /* 1.4013e-45 */;
    
        // Act
        final boolean retval = objectUnderTest.shouldOmit(val);
    
        // Assert result
        assertEquals(false, retval);
    }
}
