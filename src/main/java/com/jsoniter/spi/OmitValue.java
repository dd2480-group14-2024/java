package com.jsoniter.spi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

public interface OmitValue {

    boolean shouldOmit(Object val);

    String code();

    class Null implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return val == null;
        }

        @Override
        public String code() {
            return "null == %s";
        }
    }

    class ZeroByte implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Byte) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroShort implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Short) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroInt implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Integer) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroLong implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Long) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroFloat implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Float) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroDouble implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Double) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroChar implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Character) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class False implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return !((Boolean) val);
        }

        @Override
        public String code() {
            return "false == %s";
        }
    }

    class Parsed implements OmitValue {

        private final Object defaultValue;
        private final String code;

        public Parsed(Object defaultValue, String code) {
            this.defaultValue = defaultValue;
            this.code = code;
        }

        private static Object parsePrimitive(Class<?> valueClass, String defaultValueToOmit) {
            if (valueClass.equals(boolean.class)) {
                return Boolean.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(byte.class)) {
                return Byte.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(short.class)) {
                return Short.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(int.class)) {
                return Integer.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(long.class)) {
                return Long.valueOf(defaultValueToOmit.substring(0, defaultValueToOmit.length() - 1));
            } else if (valueClass.equals(float.class)) {
                return Float.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(double.class)) {
                return Double.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(char.class) && defaultValueToOmit.length() == 1) {
                return defaultValueToOmit.charAt(0);
            } else {
                throw new UnsupportedOperationException("Failed to parse defaultValueToOmit: " + defaultValueToOmit);
            }
        }
        
        private static Object parseWrapper(Class<?> valueClass, String defaultValueToOmit) {
            if (valueClass.equals(Boolean.class)) {
                return Boolean.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(Byte.class)) {
                return Byte.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(Short.class)) {
                return Short.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(Integer.class)) {
                return Integer.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(Long.class)) {
                return Long.valueOf(defaultValueToOmit.substring(0, defaultValueToOmit.length() - 1));
            } else if (valueClass.equals(Float.class)) {
                return Float.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(Double.class)) {
                return Double.valueOf(defaultValueToOmit);
            } else if (valueClass.equals(Character.class) && defaultValueToOmit.length() == 1) {
                return defaultValueToOmit.charAt(0);
            } else {
                throw new UnsupportedOperationException("Failed to parse defaultValueToOmit: " + defaultValueToOmit);
            }
        }
        
        public static OmitValue parse(Type valueType, String defaultValueToOmit) {
            if ("void".equals(defaultValueToOmit)) {
                return null;
            } else if ("null".equals(defaultValueToOmit)) {
                return new OmitValue.Null();
            } else {
                TypeToken<?> typeToken = TypeToken.get(valueType);
                Class<?> valueClass = typeToken.getRawType();
                String format = defaultValueToOmit + " == %s";
        
                Object defaultValue;
                if (valueClass.isPrimitive()) {
                    defaultValue = parsePrimitive(valueClass, defaultValueToOmit);
                } else {
                    defaultValue = parseWrapper(valueClass, defaultValueToOmit);
                }
        
                return new OmitValue.Parsed(defaultValue, format);
            }
        }
        

        /** 
         * CNN = 21 to 4, 10, 10
         * original parse method
        public static OmitValue parse(Type valueType, String defaultValueToOmit) {
            if ("void".equals(defaultValueToOmit)) {
                return null;
            } else if ("null".equals(defaultValueToOmit)) {
                return new OmitValue.Null();
            } else if (boolean.class.equals(valueType)) {
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Boolean.class.equals(valueType)) {
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.booleanValue()");
            } else if (int.class.equals(valueType)) {
                Integer defaultValue = Integer.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Integer.class.equals(valueType)) {
                Integer defaultValue = Integer.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.intValue()");
            } else if (byte.class.equals(valueType)) {
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Byte.class.equals(valueType)) {
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.byteValue()");
            } else if (short.class.equals(valueType)) {
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Short.class.equals(valueType)) {
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.shortValue()");
            } else if (long.class.equals(valueType)) {
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s");
            } else if (Long.class.equals(valueType)) {
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s.longValue()");
            } else if (float.class.equals(valueType)) {
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s");
            } else if (Float.class.equals(valueType)) {
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s.floatValue()");
            } else if (double.class.equals(valueType)) {
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s");
            } else if (Double.class.equals(valueType)) {
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s.doubleValue()");
            } else if (char.class.equals(valueType) && defaultValueToOmit.length() == 1) {
                Character defaultValue = defaultValueToOmit.charAt(0);
                return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s");
            } else if (Character.class.equals(valueType) && defaultValueToOmit.length() == 1) {
                Character defaultValue = defaultValueToOmit.charAt(0);
                return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s.charValue()");
            } else {
                throw new UnsupportedOperationException("failed to parse defaultValueToOmit: " + defaultValueToOmit);
            }
        }
        **/

        @Override
        public boolean shouldOmit(Object val) {
            return defaultValue.equals(val);
        }

        @Override
        public String code() {
            return code;
        }
    }
}
