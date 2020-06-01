package de.idrinth.endlessspace2.modvalidator.xmliterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class SAXSchemaFinder extends DefaultHandler {

    private String schema;

    public String value() {
        return schema;
    }

    @Override
    public void startDocument() throws SAXException {
        schema = null;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (schema != null) {
            return;
        }
        for (int i = 0; i < atts.getLength(); i++) {
            if ("noNamespaceSchemaLocation".equals(atts.getLocalName(i))) {
                schema = atts.getValue(i).replace("../", "");
            }
        }
    }
}
