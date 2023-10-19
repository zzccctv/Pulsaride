package com.zzccctv.model;

import java.util.Arrays;
import java.util.Vector;

abstract class AbstractOverview {
    private final Vector dataVector;

    public AbstractOverview() {

        dataVector = new Vector();
    }


    public Vector getDataVector() {
        return dataVector;
    }

    public void addDataVector(Object... data) {
        dataVector.add(new Vector(Arrays.asList(data)));
    }
}
