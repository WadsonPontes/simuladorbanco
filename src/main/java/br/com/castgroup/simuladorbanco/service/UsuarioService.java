package br.com.castgroup.simuladorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.castgroup.simuladorbanco.enums.SituacaoUsuarioEnum;
import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	public SituacaoUsuarioEnum criar(Usuario usuario) {
		if (usuario.getNome() == null) {
			return SituacaoUsuarioEnum.ERRO_NOME_NULL;
		}
		else if (usuario.getEmail() == null) {
			return SituacaoUsuarioEnum.ERRO_EMAIL_NULL;
		}
		else if (usuario.getSenha() == null) {
			return SituacaoUsuarioEnum.ERRO_SENHA_NULL;
		}
		else if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			return SituacaoUsuarioEnum.ERRO_EMAIL_EXISTE;
		}
		else if (usuario.getSenha().length() < 8) {
			return SituacaoUsuarioEnum.ERRO_SENHA_FRACA;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));

		usuarioRepository.save(usuario);
		return SituacaoUsuarioEnum.SUCESSO;
	}
	
	public SituacaoUsuarioEnum logar(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Usuario usuarioPossivel;
		
		if (usuario.getEmail() == null) {
			return SituacaoUsuarioEnum.ERRO_EMAIL_NULL;
		}
		else if (usuario.getSenha() == null) {
			return SituacaoUsuarioEnum.ERRO_SENHA_NULL;
		}
		
		usuarioPossivel = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioPossivel == null) {
			return SituacaoUsuarioEnum.ERRO_EMAIL_NAO_EXISTE;
		}
		else if (!encoder.matches(usuario.getSenha(), usuarioPossivel.getSenha())) {
			return SituacaoUsuarioEnum.ERRO_SENHA_ERRADA;
		}
		
		return SituacaoUsuarioEnum.SUCESSO;
	}
}
