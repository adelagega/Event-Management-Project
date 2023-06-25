package eventmanagementproject.JAXB;

import eventmanagementproject.entity.Booking;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import org.apache.log4j.Logger;

public class JAXBParser {

    public static Logger logger = Logger.getLogger(JAXBParser.class);
    public static void main(String[] args) {
        try {
            logger.info("Starting unmarshalling process");
            File file = new File("src/main/java/eventmanagementproject/XML/booking.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Booking.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Booking booking = (Booking) unmarshaller.unmarshal(file);
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Payment Status: " + booking.getPaymentStatus());
            System.out.println("Event: " + booking.getEvent());
            System.out.println("Clients: " + booking.getClients());
            System.out.println("Payment: " + booking.getPayment());
            logger.info("Unmarshalling process completed successfully");
        } catch (JAXBException e) {
            logger.error("JAXBException caught during unmarshalling", e);
        }
    }
}
