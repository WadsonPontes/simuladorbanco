package br.com.castgroup.simuladorbanco.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroConta;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario titular;

    @Column(nullable = false)
    private BigDecimal saldo;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public boolean debitar(BigDecimal valor) {
        if (this.saldo.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
            return true;
        }
        return false;
    }
}
