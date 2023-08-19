package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.DaoFinanceiro;
import model.FinanceiroUser;


@ManagedBean(name = "analiseManagedBean")
@ViewScoped
public class AnaliseManagedBean {
	
	private List<FinanceiroUser> lista = new ArrayList<FinanceiroUser>();
	
	private DaoFinanceiro<FinanceiroUser> daoFinanceiro = new DaoFinanceiro<FinanceiroUser>();
	
	String campoPesquisa;
	
	
	@PostConstruct
	public void init() {
		
		
		
	
		
		lista = daoFinanceiro.listar(FinanceiroUser.class);
		
		
		
		
	}
	
	
	
	public void recarregar() {
		lista = daoFinanceiro.listar(FinanceiroUser.class);
	}
	
	
public void pesquisar() {
		
		lista = daoFinanceiro.pesquisar(campoPesquisa);
	
		
	}
	
	

	public List<FinanceiroUser> getLista() {
		return lista;
	}


	public void setLista(List<FinanceiroUser> lista) {
		this.lista = lista;
	}


	public DaoFinanceiro<FinanceiroUser> getDaoFinanceiro() {
		return daoFinanceiro;
	}


	public void setDaoFinanceiro(DaoFinanceiro<FinanceiroUser> daoFinanceiro) {
		this.daoFinanceiro = daoFinanceiro;
	}


	public String getCampoPesquisa() {
		return campoPesquisa;
	}


	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}
	
	
	
	
	
	

}
