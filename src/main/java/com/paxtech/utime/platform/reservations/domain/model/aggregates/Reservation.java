package com.paxtech.utime.platform.reservations.domain.model.aggregates;

import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clientId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Long paymentId;

    @Column(nullable = false, length = 32)
    private String timeSlotId;

    @Column(nullable = false)
    private Long workerId;

    public Reservation() {}

    public Reservation(Long clientId, Long providerId, Long paymentId, String timeSlotId, Long workerId) {
        this.clientId = clientId;
        this.providerId = providerId;
        this.paymentId = paymentId;
        this.timeSlotId = timeSlotId;
        this.workerId = workerId;
    }

    public Long getId() { return id; }
    public Long getClientId() { return clientId; }
    public Long getProviderId() { return providerId; }
    public Long getPaymentId() { return paymentId; }
    public String getTimeSlotId() { return timeSlotId; }
    public Long getWorkerId() { return workerId; }
}
