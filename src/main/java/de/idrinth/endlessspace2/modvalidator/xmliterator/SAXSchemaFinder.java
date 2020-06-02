package de.idrinth.endlessspace2.modvalidator.xmliterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class SAXSchemaFinder extends SAXFinder {

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
        if (schema == null) {
            schema = byLocalName(atts, "noNamespaceSchemaLocation").replace("../", "");
        }
    }
}
