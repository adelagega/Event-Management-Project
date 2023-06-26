package JSON;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import entity.Booking;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser {
    private static final Logger logger = Logger.getLogger(JacksonParser.class);

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        try {
            logger.info("Starting to parse JSON file");
            Booking booking = mapper.readValue(new File("src/main/java/JSON/Json.file"), Booking.class);
            logger.info("Successfully parsed JSON file.");
        } catch (IOException e) {
            logger.error("Failed to parse JSON file", e);
        }
    }
}
