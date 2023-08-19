package dao;

import java.util.List;

import javax.persistence.Query;

import model.FinanceiroUser;
import model.UsuarioPessoa;

public class DaoFinanceiro<E> extends DaoGeneric<FinanceiroUser> {
	
	
	public FinanceiroUser pesquisar(FinanceiroUser financeiroUser) {
		Query query = super.getEntityManager().createQuery("from FinanceiroUser");
		return (FinanceiroUser) query.getResultList();
	}
	
	
	
	public List<FinanceiroUser> pesquisar(String campoPesquisa) {
		Query query = super.getEntityManager().createQuery("from FinanceiroUser where status like '%"+campoPesquisa+"%' ");
		return query.getResultList();
	}

	
	
	


}
