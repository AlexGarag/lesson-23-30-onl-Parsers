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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static void doXmlParsing(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();

        // todo вынести алгоритм парсера в отдельный метод (м.б. сделать через стрим?)
        Node rootNode = doc.getFirstChild();
        NodeList rootChildren = rootNode.getChildNodes();
        // todo сделать record?
        Sonnet sonnet = new Sonnet();
        Author author = new Author();
        sonnet.setType(rootNode.getAttributes().

                item(0).

                getNodeValue());
        for (
                int i = 0; i < rootChildren.getLength(); i++) {
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (rootChildren.item(i).getNodeName()) {
                case "author":
                    NodeList authorChildren = rootChildren.item(i).getChildNodes();
                    for (int k = 0; k < authorChildren.getLength(); k++) {
                        if (authorChildren.item(k).getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                        switch (authorChildren.item(k).getNodeName()) {
                            case "lastName":
                                author.setLastName(authorChildren.item(k).getTextContent());
                                break;
                            case "firstName":
                                author.setFirstName(authorChildren.item(k).getTextContent());
                                break;
                            case "nationality":
                                author.setNationality(authorChildren.item(k).getTextContent());
                                break;
                            case "yearOfBirth":
                                author.setYearOfBirth(Integer.valueOf(authorChildren.item(k).getTextContent()));
                                break;
                            case "yearOfDeath":
                                author.setYearOfDeath(Integer.valueOf(authorChildren.item(k).getTextContent()));
                                break;
                        }
                    }
                    sonnet.setAuthor(author);
                    break;
                case "title":
                    sonnet.setTitle(rootChildren.item(i).getTextContent());
                    break;
                case "lines":
                    List<String> sonnetLines = new ArrayList<>();
                    NodeList linesChildren = rootChildren.item(i).getChildNodes();
                    for (int k = 0; k < linesChildren.getLength(); k++) {
                        if (linesChildren.item(k).getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                        sonnetLines.add(linesChildren.item(k).getTextContent());
                    }
                    sonnet.setLines(sonnetLines);
                    break;
            }
        }

        // todo вынести работу с файлом в отдельный метод с созданием своего потока
        StringBuilder sonnetText = new StringBuilder();
        for (
                String line : sonnet.getLines()) {
            sonnetText.append(line).append("\n");
        }

        StringBuilder nameFileSonnet = new StringBuilder();
        nameFileSonnet.append(sonnet.getAuthor().

                        getFirstName()).

                append("_")
                        .

                append(sonnet.getAuthor().

                        getLastName()).

                append("_")
                        .

                append(sonnet.getTitle()).

                append(".txt");

        Path path = Paths.get(nameFileSonnet.toString());
        try {
            Files.writeString(path, sonnetText, StandardCharsets.UTF_8);
        } catch (
                IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
