package de.idrinth.endlessspace2.modvalidator.xmliterator;

import java.util.Arrays;
import org.xml.sax.ContentHandler;
import java.util.HashSet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MultiEventHandler extends DefaultHandler implements ContentHandler
{
    private final HashSet<ContentHandler> handlers = new HashSet<>();

    public MultiEventHandler(ContentHandler ...handlers) {
        this.handlers.addAll(Arrays.asList(handlers));
    }

    @Override
    public void startDocument() throws SAXException {
        for (var handler : handlers) {
            handler.startDocument();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        for (var handler : handlers) {
            handler.endDocument();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        for (var handler : handlers) {
            handler.startElement(uri, localName, qName, atts);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        for (var handler : handlers) {
            handler.endElement(uri, localName, qName);
        }
    }
}
