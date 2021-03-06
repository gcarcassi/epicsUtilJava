/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epics.util.number;

/**
 * A wrapper for a unsigned {@code byte} value.
 *
 * @author carcassi
 */
public final class UByte extends Number {
    
    private final byte unsignedValue;

    /**
     * Constructs a newly allocated {@code UByte} object that represent
     * the specified unsigned {@code byte} value.
     * 
     * @param unsignedValue the value to be represented by the {@code UByte}
     */
    public UByte(byte unsignedValue) {
        this.unsignedValue = unsignedValue;
    }

    @Override
    public int intValue() {
        return UnsignedConversions.toInt(unsignedValue);
    }

    @Override
    public long longValue() {
        return UnsignedConversions.toLong(unsignedValue);
    }

    @Override
    public float floatValue() {
        return UnsignedConversions.toFloat(unsignedValue);
    }

    @Override
    public double doubleValue() {
        return UnsignedConversions.toDouble(unsignedValue);
    }
    
    /**
     * A wrapper for the given unsigned {@code byte}.
     * 
     * @param unsignedValue an unsigned {@code byte} value
     * @return a {@code UByte} instance representing {@code unsignedValue}
     */
    public static UByte valueOf(byte unsignedValue) {
        return new UByte(unsignedValue);
    }

    /**
     * Returns a new {@code String} object representing the
     * specified unsigned {@code byte}. The radix is assumed to be 10.
     *
     * @param unsignedValue the unsigned {@code byte} to be converted
     * @return the string representation of the specified unsigned {@code byte}
     */
    public static String toString(byte unsignedValue) {
        return Integer.toString(UnsignedConversions.toInt(unsignedValue), 10);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UByte) {
            return unsignedValue == ((UByte)obj).unsignedValue;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Byte.hashCode(unsignedValue);
    }

    @Override
    public String toString() {
        return UByte.toString(unsignedValue);
    }
}
