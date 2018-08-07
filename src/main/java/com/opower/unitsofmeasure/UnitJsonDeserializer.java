package com.opower.unitsofmeasure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.text.ParsePosition;
import javax.measure.Unit;
import systems.uom.ucum.format.UCUMFormat;

/**
 *
 * @author richter
 */
public class UnitJsonDeserializer extends StdScalarDeserializer<Unit> {

    private static final long serialVersionUID = -6327531740958676293L;

    public UnitJsonDeserializer() {
        super(Unit.class);
    }

    @Override
    public Unit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            return UCUMFormat.getInstance(UCUMFormat.Variant.CASE_INSENSITIVE).parse(jsonParser.getText(), new ParsePosition(0));
        }
        throw deserializationContext.wrongTokenException(jsonParser, JsonToken.VALUE_STRING, "Expected unit value in String format");
    }
}
