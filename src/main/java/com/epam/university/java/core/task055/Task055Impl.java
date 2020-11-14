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
                        //TODO resolve year with regex
                        if (item.getFirstChild() == null) {
                            break;
                        }
                        String year = item.getFirstChild().getNodeValue();
                        if (year.length() == 4) {
                            houseDefinition.setYear(Integer.parseInt(year));
                            break;
                        } else {
                            String[] split = year.split("[\\D]+");
                            System.out.println(year);
                            if (split.length != 2 && split[0].length() == 4) {
                                houseDefinition.setYear(Integer.parseInt(split[0]));
                                break;
                            }
                            if (split[0].length() == split[1].length() || split[0].length() < 2) {
                                year = split[1];
                            } else {
                                year = split[0].substring(0,2) + split[1];
                            }

                            houseDefinition.setYear(Integer.parseInt(year));
                        }
                        break;
                    }
                    case "data_buildingarea" : {
                        if (item.getFirstChild() == null) {
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




        return null;
    }
}
