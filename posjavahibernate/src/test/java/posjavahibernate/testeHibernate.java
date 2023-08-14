package posjavahibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class testeHibernate {

	@Test
	public void testeHibernateUtil() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setNome("Calebe");
		pessoa.setSobrenome("Werneck");
		pessoa.setEmail("calebewerneck@hotmail.com");
		pessoa.setLogin("cwc3d");
		pessoa.setSenha("cwc3d");
		
		daoGeneric.salvar(pessoa);

	}

}
