package br.com.castgroup.simuladorbanco.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import br.com.castgroup.simuladorbanco.enums.TipoUsuarioEnum;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id", nullable = false, columnDefinition = "INT DEFAULT 1")
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "titular", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Conta> contas;
    
    @PrePersist
    public void atribuirValoresPadrao() {
        if (tipo == null) {
            this.tipo = TipoUsuarioEnum.CLIENTE.toModel();
        }
        
        if (this.contas == null) {
        	this.contas = new ArrayList<Conta>();
        }
        
        if (this.contas.isEmpty()) {
            this.contas.add(new Conta(this));
        }
    }
}
