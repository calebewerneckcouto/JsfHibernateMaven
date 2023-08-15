package posjavahibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class testeHibernate {

	@Test
	public void testeHibernateUtil() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setNome("Calebe Werneck");
		pessoa.setSobrenome("Couto");
		pessoa.setIdade(35);
		pessoa.setEmail("calebewerneck@hotmail.com");
		pessoa.setLogin("cwc3d");
		pessoa.setSenha("cwc3d");
		
		daoGeneric.salvar(pessoa);

	}
	
	@Test
	public void testeUpdate() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		
		pessoa.setNome("Calebe");
		pessoa.setSobrenome("Couto");
		pessoa.setIdade(35);
		pessoa.setEmail("calebewerneck@hotmail.com");
		pessoa.setLogin("cwc3d");
		pessoa.setSenha("cwc3d");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
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
	
	
	@Test
	public void testedelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		daoGeneric.deletePorId(pessoa);
		
		
		
		
	}
	
	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		
		for(UsuarioPessoa usuarioPessoa:list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------------");
		}
	}
	
	
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			System.out.println("----------------------------------------");
			
		}
	}
		
	
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by nome")
				.setMaxResults(1)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			System.out.println("---------------------------------------------------------------------------------------");
			
		}
	}
	
	@Test
	public void testeQueryListParameter() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome =:nome")
				.setParameter("nome", "Calebe Werneck").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			
		}
		
	}
	
	@Test
	public void testeQuerySomaIdade() {
	
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
    Long soma = (Long) daoGeneric.getEntityManager().
		          createQuery("select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		System.out.println(soma);
		
	}

}
