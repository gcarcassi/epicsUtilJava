/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.util.array;

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
public class ArrayIntegerTest extends FeatureTestListNumber {

    @Override
    public ArrayInteger createConstantCollection() {
        return CollectionNumbers.unmodifiableListInt(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    @Override
    public ArrayInteger createRampCollection() {
        return CollectionNumbers.unmodifiableListInt(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Override
    public ArrayInteger createModifiableCollection() {
        return CollectionNumbers.toListInt(new int[10]);
    }

    @Override
    public ListNumber createEmpty() {
        return CollectionNumbers.toListInt(new int[0]);
    }

    @Test
    public void serialization1() throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(buffer);
        ArrayInteger array = createRampCollection();
        stream.writeObject(array);
        ObjectInputStream inStream = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        ArrayInteger read = (ArrayInteger) inStream.readObject();
        assertThat(read, not(sameInstance(array)));
        assertThat(read, equalTo(array));
    }
}
