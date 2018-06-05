package com.opower.unitsofmeasure;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javax.measure.Dimension;

import javax.measure.Unit;

/**
 * Configures Jackson to (de)serialize JSR 363 Unit objects using their UCUM
 * representation, since the actual objects don't translate well into JSON.
 */
public class UnitJacksonModule extends SimpleModule {
    /**
     *
     */
    private static final long serialVersionUID = 7601584599518016604L;

    public UnitJacksonModule() {
        super("UnitJsonSerializationModule", new Version(1, 3, 5, null, UnitJacksonModule.class.getPackage().getName(),
                "jackson-module-unitsofmeasure"));

        addSerializer(Unit.class, new UnitJsonSerializer());
        addSerializer(Dimension.class, new DimensionJsonSerializer());
        addDeserializer(Unit.class, new UnitJsonDeserializer());
        addDeserializer(Dimension.class, new DimensionJsonDeserializer());
    }
}
