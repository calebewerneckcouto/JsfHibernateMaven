package repository;

import model.UsuarioPessoa;

public interface IDaoUsuarioPessoa {
	
	
	UsuarioPessoa consultarUsuario(String login,String senha);
	
	
	

}
