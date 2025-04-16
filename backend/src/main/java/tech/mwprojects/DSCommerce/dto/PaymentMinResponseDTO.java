package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.Payment;

import java.io.Serializable;
import java.time.Instant;

public class PaymentMinResponseDTO implements Serializable {
    private Integer id;
    private Instant moment;

    public PaymentMinResponseDTO(){}

    public PaymentMinResponseDTO(Integer id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentMinResponseDTO(Payment payment){
        this(payment.getId(), payment.getMoment());
    }

    public Integer getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
