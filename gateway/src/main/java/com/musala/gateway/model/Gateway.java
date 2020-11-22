package com.musala.gateway.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gateway")
@Entity
@ToString
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String serialNo;
    private String name;
    private String ip;
}
