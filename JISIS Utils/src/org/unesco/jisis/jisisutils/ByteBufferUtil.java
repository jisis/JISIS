package org.unesco.jisis.jisisutils;

//~--- JDK imports ------------------------------------------------------------

import java.nio.ByteBuffer;

//~--- classes ----------------------------------------------------------------

public class ByteBufferUtil {
    public final static byte NULL_TYPE      = (byte) 0x00;
    public final static byte CHARACTER_TYPE = (byte) 0x02;
    public final static byte BYTE_TYPE      = (byte) 0x03;
    public final static byte BOOLEAN_TYPE   = (byte) 0x01;
    public final static byte SHORT_TYPE     = (byte) 0x04;
    public final static byte LONG_TYPE      = (byte) 0x06;
    public final static byte INTEGER_TYPE   = (byte) 0x05;
    public final static byte FLOAT_TYPE     = (byte) 0x07;
    public final static byte DOUBLE_TYPE    = (byte) 0x08;
    public final static byte STRING_TYPE    = (byte) 0x09;

    //~--- methods ------------------------------------------------------------

    public static ByteBuffer putBoolean(Boolean b) {
        return ByteBuffer.allocate(1).put((byte) (b.booleanValue()
                ? 1
                : 0));
    }

    public static ByteBuffer putBoolean(ByteBuffer buffer, Boolean b) {
        buffer.put((byte) (b.booleanValue()
                           ? 1
                           : 0));

        return buffer;
    }

    public static ByteBuffer putByte(ByteBuffer buffer, Byte b) {
        buffer.put(b.byteValue());

        return buffer;
    }

    public static ByteBuffer putByteArray(byte[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(4 + array.length);

        buffer.putInt(array.length);
        buffer.put(array);

        return buffer;
    }

    public static ByteBuffer putByteArray(ByteBuffer buffer, byte[] array) {
        buffer.putInt(array.length);
        buffer.put(array);

        return buffer;
    }

    public static ByteBuffer putCharacter(Character c) {
        return ByteBuffer.allocate(2).putChar(c.charValue());
    }

    public static ByteBuffer putCharacter(ByteBuffer buffer, Character c) {
        buffer.putChar(c.charValue());

        return buffer;
    }

    public static ByteBuffer putDouble(Double d) {
        return ByteBuffer.allocate(8).putDouble(d.doubleValue());
    }

    public static ByteBuffer putDouble(ByteBuffer buffer, Double d) {
        buffer.putDouble(d.doubleValue());

        return buffer;
    }

    public static ByteBuffer putFloat(Float f) {
        return ByteBuffer.allocate(4).putFloat(f.floatValue());
    }

    public static ByteBuffer putFloat(ByteBuffer buffer, Float f) {
        buffer.putFloat(f.floatValue());

        return buffer;
    }

    public static ByteBuffer putInt(Integer i) {
        return ByteBuffer.allocate(4).putInt(i.intValue());
    }

    public static ByteBuffer putInt(ByteBuffer buffer, Integer i) {
        buffer.putInt(i.intValue());

        return buffer;
    }

    public static ByteBuffer putLong(Long l) {
        return ByteBuffer.allocate(8).putLong(l.longValue());
    }

    public static ByteBuffer putLong(ByteBuffer buffer, Long l) {
        buffer.putLong(l.longValue());

        return buffer;
    }

    public static ByteBuffer putPrimitive(ByteBuffer buffer, Object object) {
        if (object == null) {
            buffer.put(NULL_TYPE);
        }

        Class type = object.getClass();

        if (type == Boolean.class) {
            buffer.put(BOOLEAN_TYPE);

            return putBoolean(buffer, (Boolean) object);
        } else if (type == Character.class) {
            buffer.put(CHARACTER_TYPE);

            return putCharacter(buffer, (Character) object);
        } else if (type == Byte.class) {
            buffer.put(BYTE_TYPE);

            return putByte(buffer, (Byte) object);
        } else if (type == Short.class) {
            buffer.put(SHORT_TYPE);

            return putShort(buffer, (Short) object);
        } else if (type == Integer.class) {
            buffer.put(INTEGER_TYPE);

            return putInt(buffer, (Integer) object);
        } else if (type == Long.class) {
            buffer.put(LONG_TYPE);

            return putLong(buffer, (Long) object);
        } else if (type == Float.class) {
            buffer.put(FLOAT_TYPE);

            return putFloat(buffer, (Float) object);
        } else if (type == Double.class) {
            buffer.put(DOUBLE_TYPE);

            return putDouble(buffer, (Double) object);
        } else if (type == String.class) {
            buffer.put(STRING_TYPE);

            return putStringEx(buffer, (String) object);
        }

        throw new IllegalArgumentException(
            "Object must be a primitive or string");
    }

    public static ByteBuffer putShort(Short s) {
        return ByteBuffer.allocate(2).putShort(s.shortValue());
    }

    public static ByteBuffer putShort(ByteBuffer buffer, Short s) {
        buffer.putShort(s.shortValue());

        return buffer;
    }

    public static ByteBuffer putString(String string) {
        int        size   = string.length();
        ByteBuffer buffer = ByteBuffer.allocate(2 * size);

        for (int i = 0; i < size; i++) {
            buffer.putChar(string.charAt(i));
        }

        return buffer;
    }

    public static ByteBuffer putString(ByteBuffer buffer, String string) {
        int size = string.length();

        for (int i = 0; i < size; i++) {
            buffer.putChar(string.charAt(i));
        }

        return buffer;
    }

    public static ByteBuffer putStringEx(String string) {
        int        size   = string.length();
        ByteBuffer buffer = ByteBuffer.allocate(4 + size);

        buffer.putInt(size);

        for (int i = 0; i < size; i++) {
            buffer.putChar(string.charAt(i));
        }

        return buffer;
    }

    public static ByteBuffer putStringEx(ByteBuffer buffer, String string) {
        int size = string.length();

        buffer.putInt(size);

        for (int i = 0; i < size; i++) {
            buffer.putChar(string.charAt(i));
        }

        return buffer;
    }

    //~--- get methods --------------------------------------------------------

    public static Boolean getBoolean(ByteBuffer buffer) {
        return new Boolean(buffer.get() == 1);
    }

    public static int getBooleanSize() {
        return 1;
    }

    public static Byte getByte(ByteBuffer buffer) {
        return new Byte(buffer.get());
    }

    public static byte[] getByteArray(ByteBuffer buffer) {
        byte[] array = new byte[buffer.getInt()];

        buffer.get(array);

        return array;
    }

    public static int getByteArraySize(byte[] array) {
        return 4 + array.length;
    }

    public static int getByteSize() {
        return 1;
    }

    public static Character getCharacter(ByteBuffer buffer) {
        return new Character(buffer.getChar());
    }

    public static int getCharacterSize() {
        return 2;
    }

    public static Double getDouble(ByteBuffer buffer) {
        return new Double(buffer.getDouble());
    }

    public static int getDoubleSize() {
        return 8;
    }

    public static Float getFloat(ByteBuffer buffer) {
        return new Float(buffer.getFloat());
    }

    public static int getFloatSize() {
        return 4;
    }

    public static Integer getInt(ByteBuffer buffer) {
        return new Integer(buffer.getInt());
    }

    public static int getIntSize() {
        return 4;
    }

    public static Long getLong(ByteBuffer buffer) {
        return new Long(buffer.getLong());
    }

    public static int getLongSize() {
        return 8;
    }

    public static Object getPrimitive(ByteBuffer buffer) {
        byte type = buffer.get();

        if (type == NULL_TYPE) {
            return null;
        } else if (type == BOOLEAN_TYPE) {
            return getBoolean(buffer);
        } else if (type == CHARACTER_TYPE) {
            return getCharacter(buffer);
        } else if (type == BYTE_TYPE) {
            return getByte(buffer);
        } else if (type == SHORT_TYPE) {
            return getShort(buffer);
        } else if (type == INTEGER_TYPE) {
            return getInt(buffer);
        } else if (type == LONG_TYPE) {
            return getLong(buffer);
        } else if (type == FLOAT_TYPE) {
            return getFloat(buffer);
        } else if (type == DOUBLE_TYPE) {
            return getDouble(buffer);
        } else if (type == STRING_TYPE) {
            return getString(buffer);
        }

        throw new IllegalArgumentException("Unknow type encoded in buffer");
    }

    public static int getPrimitiveSize(Object object) {
        if (object == null) {
            return 1;
        }

        Class type = object.getClass();

        if (type == Boolean.class) {
            return getBooleanSize() + 1;
        } else if (type == Character.class) {
            return getCharacterSize() + 1;
        } else if (type == Byte.class) {
            return getByteSize() + 1;
        } else if (type == Short.class) {
            return getShortSize() + 1;
        } else if (type == Integer.class) {
            return getIntSize() + 1;
        } else if (type == Long.class) {
            return getLongSize() + 1;
        } else if (type == Float.class) {
            return getFloatSize() + 1;
        } else if (type == Double.class) {
            return getDoubleSize() + 1;
        } else if (type == String.class) {
            return getStringSize((String) object) + 1;
        }

        throw new IllegalArgumentException(
            "Object must be a primitive or string");
    }

    public static Short getShort(ByteBuffer buffer) {
        return new Short(buffer.getShort());
    }

    public static int getShortSize() {
        return 2;
    }

    public static String getString(ByteBuffer buffer) {
        int pos  = buffer.position();
        int size = 0;

        while (buffer.get() != 0) {
            size++;
        }

        StringBuffer name = new StringBuffer(size);

        for (int i = 0; i < size; i++) {
            name.append(buffer.getChar());
        }

        return name.toString();
    }

    public static String getString(ByteBuffer buffer, int size) {
        StringBuffer name = new StringBuffer(size);

        for (int i = 0; i < size; i++) {
            name.append(buffer.getChar());
        }

        return name.toString();
    }

    public static String getStringEx(ByteBuffer buffer) {
        int          size = buffer.getInt();
        StringBuffer name = new StringBuffer(size);

        for (int i = 0; i < size; i++) {
            name.append(buffer.getChar());
        }

        return name.toString();
    }

    public static int getStringSize(String string) {
        return 4 + 2 * string.length();
    }

    // ------------------------------------------------------------------
    // Going through the primitive methods will write/read a header byte
    // defining the type of the object which follow
    // ------------------------------------------------------------------
    public static boolean isPrimitive(Object object) {
        if (object == null) {
            return true;
        }

        Class type = object.getClass();

        if (type == Boolean.class) {
            return true;
        } else if (type == Character.class) {
            return true;
        } else if (type == Byte.class) {
            return true;
        } else if (type == Short.class) {
            return true;
        } else if (type == Integer.class) {
            return true;
        } else if (type == Long.class) {
            return true;
        } else if (type == Float.class) {
            return true;
        } else if (type == Double.class) {
            return true;
        } else if (type == String.class) {
            return true;
        }

        return false;
    }
}
