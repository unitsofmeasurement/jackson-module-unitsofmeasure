package com.opower.unitsofmeasure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import javax.measure.Unit;
import systems.uom.ucum.format.UCUMFormat;

/**
 * @author richter
 * @author keilw
 */
@SuppressWarnings("rawtypes")
public class UnitJsonSerializer extends StdScalarSerializer<Unit> {

    public UnitJsonSerializer() {
        super(Unit.class);
    }

    @Override
    public void serialize(Unit unit, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (unit == null) {
            jgen.writeNull();
        } else {
            // Format the unit using the UCUM representation.
            // The string produced for a given unit is always the same; it is not affected by the locale.
            // It can be used as a canonical string representation for exchanging units.
            String ucumFormattedUnit = UCUMFormat.getInstance(UCUMFormat.Variant.CASE_SENSITIVE).format(unit, new StringBuilder()).toString();
            jgen.writeString(ucumFormattedUnit);
        }
    }
}
