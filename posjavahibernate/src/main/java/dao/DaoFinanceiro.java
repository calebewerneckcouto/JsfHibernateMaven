package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import model.FinanceiroUser;
import model.UsuarioPessoa;

public class DaoFinanceiro<E> extends DaoGeneric<FinanceiroUser> {

	public FinanceiroUser pesquisar(FinanceiroUser financeiroUser) {
		Query query = super.getEntityManager().createQuery("from FinanceiroUser");
		return (FinanceiroUser) query.getResultList();
	}

	public List<FinanceiroUser> pesquisar(String campoPesquisa, String campoPesquisanome) throws Exception {
		
		
		

		try {

			Query query = super.getEntityManager().createQuery(
					"from FinanceiroUser where status like :status and nome like :nome");
			query.setParameter("status", '%' + campoPesquisa + '%');
			query.setParameter("nome", '%' + campoPesquisanome + '%');
			
			
			

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
