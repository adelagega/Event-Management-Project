package JAXB;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.log4j.Logger;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static Logger logger = Logger.getLogger(LocalDateAdapter.class);
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        logger.info("Attempting to unmarshal date string: " + dateString);
        try {
            LocalDate date = LocalDate.parse(dateString, dtf);
            logger.info("Successfully unmarshalled date string: " + dateString);
            return date;
        } catch (Exception e) {
            logger.error("Failed to unmarshal date string: " + dateString, e);
            throw e;
        }
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        logger.info("Attempting to marshal LocalDate: " + localDate);
        try {
            String dateString = localDate.format(dtf);
            logger.info("Successfully marshalled LocalDate: " + localDate);
            return dateString;
        } catch (Exception e) {
            logger.error("Failed to marshal LocalDate: " + localDate, e);
            throw e;
        }
    }
}