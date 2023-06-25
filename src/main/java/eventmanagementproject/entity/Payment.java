package eventmanagementproject.entity;

import JAXB.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    @XmlElement(name="paymentId")
    private int paymentId;
    @XmlElement(name="amount")
    private float amount;
    @XmlElement(name="paymentDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate paymentDate;
    @XmlElement(name="paymentType")
    private String paymentType;
    @XmlElement(name="Invoice")
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
