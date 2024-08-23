package by.tms.lesson23.onl30.parcers.xml_parsing;

import by.tms.lesson23.onl30.parcers.model.Author;
import by.tms.lesson23.onl30.parcers.model.Sonnet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static Sonnet doXmlParsing(File file) throws IOException, ParserConfigurationException, SAXException {
        Node rootNode = getRootNode(file);
        NodeList sonnetContent = rootNode.getChildNodes();
        // todo сделать record???
        Sonnet sonnet = new Sonnet();
        sonnet.setType(rootNode.getAttributes().item(0).getNodeValue());
        for (int i = 0; i < sonnetContent.getLength(); i++) {
            if (sonnetContent.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (sonnetContent.item(i).getNodeName()) {
                case "author":
                    NodeList author = sonnetContent.item(i).getChildNodes();
                    sonnet.setAuthor(getAuthor(author));
                    break;
                case "title":
                    String title = sonnetContent.item(i).getTextContent();
                    sonnet.setTitle(title);
                    break;
                case "lines":
                    NodeList textContent = sonnetContent.item(i).getChildNodes();
                    sonnet.setLines(getTextContent(textContent));
                    break;
            }
        }
        return sonnet;
    }

    private static Node getRootNode(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(file);
        return document.getDocumentElement();
    }

    private static Author getAuthor(NodeList nodeAuthor) {
        Author author = new Author();
        for (int k = 0; k < nodeAuthor.getLength(); k++) {
            if (nodeAuthor.item(k).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (nodeAuthor.item(k).getNodeName()) {
                case "lastName":
                    author.setLastName(nodeAuthor.item(k).getTextContent());
                    break;
                case "firstName":
                    author.setFirstName(nodeAuthor.item(k).getTextContent());
                    break;
                case "nationality":
                    author.setNationality(nodeAuthor.item(k).getTextContent());
                    break;
                case "yearOfBirth":
                    author.setYearOfBirth(Integer.valueOf(nodeAuthor.item(k).getTextContent()));
                    break;
                case "yearOfDeath":
                    author.setYearOfDeath(Integer.valueOf(nodeAuthor.item(k).getTextContent()));
                    break;
            }
        }
        return author;
    }

    private static List<String> getTextContent(NodeList textContent) {
        List<String> sonnetLines = new ArrayList<>();
        for (int k = 0; k < textContent.getLength(); k++) {
            if (textContent.item(k).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            sonnetLines.add(textContent.item(k).getTextContent());
        }
        return sonnetLines;
    }
}
