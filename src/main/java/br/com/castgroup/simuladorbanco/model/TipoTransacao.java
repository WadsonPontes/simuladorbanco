package br.com.castgroup.simuladorbanco.model;

import br.com.castgroup.simuladorbanco.enums.TipoTransacaoEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoTransacao {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    public TipoTransacaoEnum toEnum() {
        return TipoTransacaoEnum.fromId(this.id);
    }
}
