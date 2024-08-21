package by.tms.lesson23.onl30.parcers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SolverTask1 {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

//        Написать программу для парсинга xml документа. Необходимо распарсить xml документ и
//        содержимое тегов line записать в другой документ. Название файла для записи должно
//        состоять из значений тегов и имеет вид: <firstName>_<lastName>_<title>.txt

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File("text.xml"));
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("sonnet");

        Node users = nodeList.item(0); //doc.getElementsByTagName("user");
//
//        System.out.println(nodeList.getLength());

String contentSonnet = doc.getElementsByTagName("lines").item(0).getTextContent();
        int i = 0;
    }
}