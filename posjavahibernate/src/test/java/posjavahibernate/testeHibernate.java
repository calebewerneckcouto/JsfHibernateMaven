package posjavahibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class testeHibernate {

	@Test
	public void testeHibernateUtil() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setNome("Livia");
		pessoa.setSobrenome("Resende");
		pessoa.setEmail("livinha@hotmail.com");
		pessoa.setLogin("livinha");
		pessoa.setSenha("livinha");
		
		daoGeneric.salvar(pessoa);

	}
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setId(1L);
		
		pessoa= daoGeneric.pesquisar(pessoa);	
		
		System.out.println(pessoa);
	}
	
	
	
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
		
		
	}
	
	
	

}
