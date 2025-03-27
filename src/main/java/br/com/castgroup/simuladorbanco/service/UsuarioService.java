package br.com.castgroup.simuladorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
		
		usuarioRepository.save(usuario);
		return SituacaoUsuarioEnum.SUCESSO;
	}
}
