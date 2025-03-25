package br.com.castgroup.simuladorbanco.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "de_id", nullable = true)
    private Usuario de;

    @ManyToOne
    @JoinColumn(name = "para_id", nullable = true)
    private Usuario para;

    @ManyToOne
    @JoinColumn(name = "tipo_transacao_id", nullable = false)
    private TipoTransacao tipo;

    @Column(nullable = false)
    private LocalDateTime dataHora;
}
