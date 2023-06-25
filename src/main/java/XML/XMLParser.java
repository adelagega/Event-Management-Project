package XML;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser {
    private static final Logger logger = Logger.getLogger(XMLParser.class);

    public static void main(String[] args) {
        try {
            logger.info("Starting XML parsing process");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/main/java/XML/booking.xml");
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            logger.info("Root element: " + root.getNodeName());
            System.out.println("Root element: " + root.getNodeName());
            System.out.println("bookingId: " + getElementTextContent(root, "bookingId"));
            System.out.println("paymentStatus: " + getElementTextContent(root, "paymentStatus"));
            Element event = (Element) root.getElementsByTagName("Event").item(0);
            System.out.println("Event:");
            System.out.println("\teventId: " + getElementTextContent(event, "eventId"));
            System.out.println("\teventDate: " + getElementTextContent(event, "eventDate"));
            System.out.println("\tbudget: " + getElementTextContent(event, "budget"));
            System.out.println("\ttheme: " + getElementTextContent(event, "theme"));
            NodeList clientNodes = root.getElementsByTagName("Client");
            for (int i = 0; i < clientNodes.getLength(); i++) {
                Element client = (Element) clientNodes.item(i);
                System.out.println("Client:");
                System.out.println("\tclientId: " + getElementTextContent(client, "clientId"));
                System.out.println("\tfirstName: " + getElementTextContent(client, "firstName"));
                System.out.println("\tlastName: " + getElementTextContent(client, "lastName"));
                System.out.println("\temail: " + getElementTextContent(client, "email"));
                System.out.println("\tphoneNumber: " + getElementTextContent(client, "phoneNumber"));
            }
            Element payment = (Element) root.getElementsByTagName("Payment").item(0);
            System.out.println("Payment:");
            System.out.println("\tpaymentId: " + getElementTextContent(payment, "paymentId"));
            System.out.println("\tamount: " + getElementTextContent(payment, "amount"));
            System.out.println("\tpaymentDate: " + getElementTextContent(payment, "paymentDate"));
            System.out.println("\tpaymentType: " + getElementTextContent(payment, "paymentType"));

            Element invoice = (Element) payment.getElementsByTagName("Invoice").item(0);
            System.out.println("\tInvoice:");
            System.out.println("\t\tinvoiceId: " + getElementTextContent(invoice, "invoiceId"));
            System.out.println("\t\tinvoiceDate: " + getElementTextContent(invoice, "invoiceDate"));
            System.out.println("\t\ttotalAmount: " + getElementTextContent(invoice, "totalAmount"));

        } catch (Exception e) {
            logger.error("An error occurred while parsing the XML", e);
            e.printStackTrace();
        }
    }

    private static String getElementTextContent(Element element, String tagName) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            return list.item(0).getTextContent();
        } else {
            logger.warn("Element " + tagName + " not found in the XML");
            return null;
        }
    }
}
