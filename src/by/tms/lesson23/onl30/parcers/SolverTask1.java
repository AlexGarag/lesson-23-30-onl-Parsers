package by.tms.lesson23.onl30.parcers;

import by.tms.lesson23.onl30.parcers.model.AuthorModel;
import by.tms.lesson23.onl30.parcers.model.SonnetModel;
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

public class SolverTask1 {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

//        Написать программу для парсинга xml документа. Необходимо распарсить xml документ и
//        содержимое тегов line записать в другой документ. Название файла для записи должно
//        состоять из значений тегов и имеет вид: <firstName>_<lastName>_<title>.txt

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File("text.xml"));
        doc.getDocumentElement().normalize();

        Node rootNode = doc.getFirstChild();
        NodeList rootChildren = rootNode.getChildNodes();
        SonnetModel sonnetModel = new SonnetModel();
        AuthorModel authorModel = new AuthorModel();

        for (int i = 0; i < rootChildren.getLength(); i++) {
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            System.out.println(rootChildren.item(i).getNodeName());
            switch (rootChildren.item(i).getNodeName()) {
                case "author":
                    NodeList authorChildren = rootChildren.item(i).getChildNodes();
                    for (int k = 0; k < authorChildren.getLength(); k++) {
                        if (authorChildren.item(k).getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                        switch (authorChildren.item(k).getNodeName()) {
                            case "lastName":
                                authorModel.setLastName(authorChildren.item(k).getTextContent());
                                break;
                            case "firstName":
                                authorModel.setFirstName(authorChildren.item(k).getTextContent());
                                break;
                            case "nationality":
                                authorModel.setNationality(authorChildren.item(k).getTextContent());
                                break;
                            case "yearOfBirth":
                                authorModel.setYearOfBirth(Integer.valueOf(authorChildren.item(k).getTextContent()));
                                break;
                            case "yearOfDeath":
                                authorModel.setYearOfDeath(Integer.valueOf(authorChildren.item(k).getTextContent()));
                                break;
                        }
                    }
                    sonnetModel.setAuthor(authorModel);
                    break;
                case "title":
                    sonnetModel.setTitle(rootChildren.item(i).getTextContent());
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
                    sonnetModel.setLines(sonnetLines);
                    break;
            }
        }

        int i = 0;
    }
}