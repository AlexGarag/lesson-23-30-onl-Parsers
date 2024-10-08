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
    static final String AUTHOR = "author";
    static final String TITLE = "title";
    static final String TEXT = "lines";
    static final String LAST_NAME = "lastName";
    static final String FIRST_NAME = "firstName";
    static final String NATIONALITY = "nationality";
    static final String YEAR_OF_BIRTH = "yearOfBirth";
    static final String YEAR_OF_DEATH = "yearOfDeath";

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
                case AUTHOR:
                    NodeList author = sonnetContent.item(i).getChildNodes();
                    sonnet.setAuthor(getAuthor(author));
                    break;
                case TITLE:
                    String title = sonnetContent.item(i).getTextContent();
                    sonnet.setTitle(title);
                    break;
                case TEXT:
                    NodeList textContent = sonnetContent.item(i).getChildNodes();
                    sonnet.setText(getTextContent(textContent));
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
                case LAST_NAME:
                    author.setLastName(nodeAuthor.item(k).getTextContent());
                    break;
                case FIRST_NAME:
                    author.setFirstName(nodeAuthor.item(k).getTextContent());
                    break;
                case NATIONALITY:
                    author.setNationality(nodeAuthor.item(k).getTextContent());
                    break;
                case YEAR_OF_BIRTH:
                    author.setYearOfBirth(Integer.valueOf(nodeAuthor.item(k).getTextContent()));
                    break;
                case YEAR_OF_DEATH:
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
