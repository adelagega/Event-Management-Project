package entity;

import JAXB.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "Invoice")
public class Invoice {
    @XmlElement(name="invoiceId")
    @JsonProperty("invoiceId")
    private int invoiceId;
    @XmlElement(name = "invoiceDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonProperty("invoiceDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;
    @XmlElement(name = "totalAmount")
    @JsonProperty("totalAmount")
    private float totalAmount;

    public Invoice() {
    }

    public Invoice(int invoiceId, LocalDate invoiceDate, float totalAmount) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", invoiceDate=" + invoiceDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
