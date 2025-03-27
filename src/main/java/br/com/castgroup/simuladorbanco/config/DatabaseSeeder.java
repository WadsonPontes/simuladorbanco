package br.com.castgroup.simuladorbanco.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.castgroup.simuladorbanco.enums.TipoTransacaoEnum;
import br.com.castgroup.simuladorbanco.enums.TipoUsuarioEnum;
import br.com.castgroup.simuladorbanco.repository.TipoTransacaoRepository;
import br.com.castgroup.simuladorbanco.repository.TipoUsuarioRepository;

@Configuration
public class DatabaseSeeder {
	@Bean
    CommandLineRunner initDatabase(TipoTransacaoRepository tipoTransacaoRepository, TipoUsuarioRepository tipoUsuarioRepository) {
        return args -> {
            for (TipoTransacaoEnum tipo : TipoTransacaoEnum.values()) {
            	tipoTransacaoRepository.findById(tipo.getId())
                        .orElseGet(() -> tipoTransacaoRepository.save(tipo.toModel()));
            }
            
            for (TipoUsuarioEnum tipo : TipoUsuarioEnum.values()) {
            	tipoUsuarioRepository.findById(tipo.getId())
                        .orElseGet(() -> tipoUsuarioRepository.save(tipo.toModel()));
            }
        };
    }
}
