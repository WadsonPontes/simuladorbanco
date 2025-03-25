package br.com.castgroup.simuladorbanco.model;

import br.com.castgroup.simuladorbanco.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoUsuario {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    public TipoUsuarioEnum toEnum() {
        return TipoUsuarioEnum.fromId(this.id);
    }
}
