package com.terapia.controle.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer pacientId;

    private Double amount;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate payedDate;

    private String status;
}
