/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.util.array;

import org.epics.util.array.ArrayByte;
import org.epics.util.array.CollectionNumbers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carcassi
 */
public class ArrayUByteTest extends FeatureTestListNumber {

    @Override
    public ArrayUByte createConstantCollection() {
        return CollectionNumbers.unmodifiableListUByte(new byte[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
    }

    @Override
    public ArrayUByte createRampCollection() {
        return CollectionNumbers.unmodifiableListUByte(new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    @Override
    public ArrayUByte createModifiableCollection() {
        return CollectionNumbers.toListUByte(new byte[10]);
    }

    @Override
    public ArrayUByte createEmpty() {
        return CollectionNumbers.toListUByte(new byte[0]);
    }

    @Test
    public void serialization1() throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(buffer);
        ArrayUByte array = createRampCollection();
        stream.writeObject(array);
        ObjectInputStream inStream = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        ArrayUByte read = (ArrayUByte) inStream.readObject();
        assertThat(read, not(sameInstance(array)));
        assertThat(read, equalTo(array));
    }
}