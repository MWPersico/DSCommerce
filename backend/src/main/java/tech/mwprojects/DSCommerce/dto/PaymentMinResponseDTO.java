package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.Payment;

import java.io.Serializable;
import java.time.Instant;

public class PaymentMinResponseDTO implements Serializable {
    private Integer id;
    private Instant moment;

    public PaymentMinResponseDTO(){}

    public PaymentMinResponseDTO(Payment payment){
        if(payment!=null){

            this.id = payment.getId();
            this.moment = payment.getMoment();
        }
    }

    public Integer getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
