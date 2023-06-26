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
@JsonRootName(value = "Payment")
public class Payment {
    @XmlElement(name="paymentId")
    @JsonProperty("paymentId")
    private int paymentId;
    @XmlElement(name="amount")
    @JsonProperty("amount")
    private float amount;
    @XmlElement(name="paymentDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonProperty("paymentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    @XmlElement(name="paymentType")
    @JsonProperty("paymentType")
    private String paymentType;
    @XmlElement(name="Invoice")
    @JsonProperty("Invoice")
    private Invoice invoice;

    public Payment() {
    }

    public Payment(int paymentId, float amount, LocalDate paymentDate, String paymentType, Invoice invoice) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.invoice = invoice;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentType='" + paymentType + '\'' +
                ", invoice=" + invoice +
                '}';
    }
}
