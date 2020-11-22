package com.musala.gateway.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String vendor;

    @ManyToOne
    @JoinColumn(name="gateway_id")
    private Gateway gateway;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private DeviceStatus status;

    @CreatedDate
    private LocalDateTime created;

}
