package eventmanagementproject.util;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public class XMLParser {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            URL xmlURL = XMLParser.class.getClassLoader().getResource("events.xml");
            if (xmlURL == null) {
                throw new IllegalArgumentException("Could not find resource file");
            }
            Document document = documentBuilder.parse(xmlURL.toString());
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());

            NodeList nodeList = document.getElementsByTagName("event");
            System.out.println("--------------------");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element event = (Element) node;
                    System.out.println("Event ID : " + event.getElementsByTagName("eventID").item(0).getTextContent());
                    System.out.println("Event Type ID : " + event.getElementsByTagName("eventTypeId").item(0).getTextContent());
                    System.out.println("Event Type Name : " + event.getElementsByTagName("eventTypeName").item(0).getTextContent());
                    System.out.println("Venue ID : " + event.getElementsByTagName("venueId").item(0).getTextContent());
                    System.out.println("Venue Name : " + event.getElementsByTagName("venueName").item(0).getTextContent());
                    System.out.println("Venue Capacity : " + event.getElementsByTagName("venueCapacity").item(0).getTextContent());
                    System.out.println("Venue Location : " + event.getElementsByTagName("venueLocation").item(0).getTextContent());

                    NodeList supplierList = event.getElementsByTagName("supplier");
                    for (int i = 0; i < supplierList.getLength(); i++) {
                        Element supplier = (Element) supplierList.item(i);
                        System.out.println("Supplier ID : " + supplier.getElementsByTagName("supplierId").item(0).getTextContent());
                        System.out.println("Supplier Name : " + supplier.getElementsByTagName("supplierName").item(0).getTextContent());
                        System.out.println("Supplier Type : " + supplier.getElementsByTagName("supplierType").item(0).getTextContent());
                        System.out.println("Supplier Contact Name : " + supplier.getElementsByTagName("contactName").item(0).getTextContent());
                        System.out.println("Supplier Contact Email : " + supplier.getElementsByTagName("contactEmail").item(0).getTextContent());
                        System.out.println("Supplier Contact Phone : " + supplier.getElementsByTagName("contactPhone").item(0).getTextContent());
                    }

                    NodeList staffList = event.getElementsByTagName("staffMember");
                    for (int i = 0; i < staffList.getLength(); i++) {
                        Element staffMember = (Element) staffList.item(i);
                        System.out.println("Staff ID : " + staffMember.getElementsByTagName("staffId").item(0).getTextContent());
                        System.out.println("First Name : " + staffMember.getElementsByTagName("firstName").item(0).getTextContent());
                        System.out.println("Last Name : " + staffMember.getElementsByTagName("lastName").item(0).getTextContent());
                        System.out.println("Staff Member Role : " + staffMember.getElementsByTagName("role").item(0).getTextContent());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

