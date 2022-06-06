package com.arsnaz.testing.exceptions;

import com.arsnaz.testing.PropertiesConstants;

public class IllegalPropertyException extends IllegalArgumentException {
    public IllegalPropertyException(PropertiesConstants propKey, String propValue) {
        super("Value of property \"" + propKey.toString() + "\" is invalid: \"" + propValue + "\"");
    }
}
