/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.util.array;

/**
 * An ordered collection of {@code double}s.
 *
 * @author Gabriele Carcassi
 */
public abstract class ListDouble implements ListNumber, CollectionDouble {

    @Override
    public IteratorDouble iterator() {
        return new IteratorDouble() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public double nextDouble() {
                return getDouble(index++);
            }
        };
    }

    @Override
    public float getFloat(int index) {
        return (float) getDouble(index);
    }

    @Override
    public long getLong(int index) {
        return (long) getDouble(index);
    }

    @Override
    public int getInt(int index) {
        return (int) getDouble(index);
    }

    @Override
    public short getShort(int index) {
        return (short) getDouble(index);
    }

    @Override
    public byte getByte(int index) {
        return (byte) getDouble(index);
    }

    @Override
    public void setDouble(int index, double value) {
        throw new UnsupportedOperationException("Read only list.");
    }

    @Override
    public void setFloat(int index, float value) {
        setDouble(index, (double) value);
    }

    @Override
    public void setLong(int index, long value) {
        setDouble(index, (double) value);
    }

    @Override
    public void setInt(int index, int value) {
        setDouble(index, (double) value);
    }

    @Override
    public void setShort(int index, short value) {
        setDouble(index, (double) value);
    }

    @Override
    public void setByte(int index, byte value) {
        setDouble(index, (double) value);
    }

    @Override
    public void setAll(int index, ListNumber list) {
        if ((index+list.size()) > size()) {
            throw new IndexOutOfBoundsException("Index: "+index+", Elements: "+list.size()+", Size: "+size());
        }
        for (int i = 0; i < list.size(); i++) {
            setDouble(index + i, list.getDouble(i));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj instanceof ListDouble) {
            ListDouble other = (ListDouble) obj;

            if (size() != other.size())
                return false;

            for (int i = 0; i < size(); i++) {
                if (Double.doubleToLongBits(getDouble(i)) != Double.doubleToLongBits(other.getDouble(i)))
                    return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size(); i++) {
            long bits = Double.doubleToLongBits(getDouble(i));
            result = 31 * result + (int)(bits ^ (bits >>> 32));
        }
        return result;
    }

    @Override
    public String toString() {
        if (size() == 0) return "[]";
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (; i < size() - 1; i++) {
            builder.append(getDouble(i)).append(", ");
        }
        builder.append(getDouble(i)).append("]");
        return builder.toString();
    }
    
    public ListDouble subList(final int fromIndex, final int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + " toIndex: " + toIndex + ", size: " + size() );
        }
        final int size = toIndex - fromIndex;
        return new ListDouble() {
            @Override
            public double getDouble(int index) {
                if (index < 0 || index >= size)
                    throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
                return ListDouble.this.getDouble(fromIndex + index);
            }

            @Override
            public void setDouble(int index, double value) {
                if (index < 0 || index >= size)
                    throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
                ListDouble.this.setDouble(fromIndex + index, value);
            }

            @Override
            public int size() {
                return size;
            }
        };
    }
}
