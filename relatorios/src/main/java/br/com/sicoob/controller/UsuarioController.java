package br.com.sicoob.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.sicoob.model.Usuario;
import br.com.sicoob.repository.UsuarioRepository;
import br.com.sicoob.services.UsuarioService;
import lombok.RequiredArgsConstructor;

@Controller
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;
    
    
    @GetMapping("/v1-cadastrar-pessoa")
    public String cadastrarUsuario(){
        return "cadastrar-usuario";
    }
    @PostMapping("/v1-cadastrar-usuario")
    public String salvar(@Valid Usuario usuario){
    	
    	if (usuarioService.jaExiste(usuario.getNome()) == false) {
    		usuarioService.cadastrarUsuario(usuario);
    		System.out.println("Usu�rio ser� cadastrado");
    		return "redirect:/v1-cadastrar-pessoa?sucess";
    	}else {
    		System.out.println("Usu�rio j� possui cadastro");
    		return "redirect:/v1-cadastrar-pessoa?jaCadastrado";
    	}
        
    }
}