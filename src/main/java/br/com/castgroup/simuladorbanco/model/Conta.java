package br.com.castgroup.simuladorbanco.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    @Column(nullable = false, columnDefinition = "NUMERIC DEFAULT 0.0")
    private BigDecimal saldo;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;
    
    public Conta(Usuario titular) {
    	this.numeroConta = UUID.randomUUID().toString();
    	this.titular = titular;
    	this.saldo = BigDecimal.ZERO;
    }

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
