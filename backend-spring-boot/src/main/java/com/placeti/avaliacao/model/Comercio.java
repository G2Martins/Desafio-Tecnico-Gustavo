package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String responsavel;
    
    @Enumerated(EnumType.STRING)
    private TipoComercio tipo;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}
