package br.com.castgroup.simuladorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.castgroup.simuladorbanco.enums.SituacaoEnum;
import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Transactional
	public SituacaoEnum criar(Usuario usuario) {
		if (usuario.getNome() == null) {
			return SituacaoEnum.ERRO_NOME_NULL;
		}
		else if (usuario.getEmail() == null) {
			return SituacaoEnum.ERRO_EMAIL_NULL;
		}
		else if (usuario.getSenha() == null) {
			return SituacaoEnum.ERRO_SENHA_NULL;
		}
		else if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			return SituacaoEnum.ERRO_EMAIL_EXISTE;
		}
		else if (usuario.getSenha().length() < 8) {
			return SituacaoEnum.ERRO_SENHA_FRACA;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));

		usuarioRepository.save(usuario);
		return SituacaoEnum.SUCESSO;
	}
	
	@Transactional
	public SituacaoEnum logar(Usuario usuario, HttpSession session) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Usuario usuarioPossivel;
		
		if (usuario.getEmail() == null) {
			return SituacaoEnum.ERRO_EMAIL_NULL;
		}
		else if (usuario.getSenha() == null) {
			return SituacaoEnum.ERRO_SENHA_NULL;
		}
		
		usuarioPossivel = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioPossivel == null) {
			return SituacaoEnum.ERRO_EMAIL_NAO_EXISTE;
		}
		else if (!encoder.matches(usuario.getSenha(), usuarioPossivel.getSenha())) {
			return SituacaoEnum.ERRO_SENHA_ERRADA;
		}
		
		session.setAttribute("usuario", usuarioPossivel);
		session.setAttribute("conta", usuarioPossivel.getContas().get(0));
		
		return SituacaoEnum.SUCESSO;
	}
}
