package de.idrinth.endlessspace2.modvalidator.xmliterator;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

abstract class SAXFinder extends DefaultHandler {
    protected final String byLocalName(Attributes atts, String name)
    {
        var test = atts.getValue(name);
        if (null != test) {
            return test;
        }
        for (var i = 0; i < atts.getLength(); i++) {
            var attribute = atts.getLocalName(i);
            if (name.equals(attribute)) {
                var at = atts.getValue(i);
                if (at != null) {
                    return at;
                }
            }
        }
        return "";
    }
}
