package com.epam.university.java.core.task055;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task055Impl implements Task055 {
    @Override
    public ProcessingContext createContext(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<HouseDefinitionImpl> data = new ArrayList<>();
        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(getClass().getResource("/task055/livingHousesPassports.xml").getPath()));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        NodeList houses = document.getDocumentElement().getElementsByTagName("passports_houses");
        for (int i = 0; i < houses.getLength(); i++) {
            Node house = houses.item(i);
            NodeList attributes = house.getChildNodes();
            HouseDefinitionImpl houseDefinition = new HouseDefinitionImpl();
            houseDefinition.setHasCommunal(false);
            for (int j = 0; j < attributes.getLength(); j++) {
                Node item = attributes.item(j);
                String nodeName = item.getNodeName();
                switch (nodeName) {
                    case "addr_district" : {
                        houseDefinition.setAddress(item.getFirstChild().getNodeValue());
                        break;
                    }
                    case "data_buildingdate" : {
                        if (item.getFirstChild() == null) {
                            break;
                        }
                        houseDefinition.setYear(normalizeYear(item.getFirstChild().getNodeValue()));
                        break;
                    }
                    case "data_buildingarea" : {
                        if (item.getFirstChild() == null) {
                            houseDefinition.setArea(0.0);
                            break;
                        }
                        houseDefinition.setArea(Double.parseDouble(item.getFirstChild().getNodeValue()));
                        break;
                    }
                    case "comm_type" : {
                        if (item.getFirstChild() != null) {
                            houseDefinition.setHasCommunal(true);
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            data.add(houseDefinition);
        }
        return new ProcessingContextImpl(data);
    }

    private static int normalizeYear(String data) {
        String regex = "([1]|[2])[\\d]{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        int year = 0;
        while (matcher.find()) {
            year = Integer.parseInt(data.substring(matcher.start(), matcher.end()));
        }
        return year;
    }
}
