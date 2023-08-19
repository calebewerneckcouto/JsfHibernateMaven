package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.DaoFinanceiro;
import model.FinanceiroUser;
import model.UsuarioPessoa;


@ManagedBean(name = "analiseManagedBean")
@ViewScoped
public class AnaliseManagedBean {
	
	private List<FinanceiroUser> lista = new ArrayList<FinanceiroUser>();
	
	private DaoFinanceiro<FinanceiroUser> daoFinanceiro = new DaoFinanceiro<FinanceiroUser>();
	
	private UsuarioPessoa user = new UsuarioPessoa();
	
	String campoPesquisa;
	
	private BarChartModel barChartModel = new BarChartModel();
	
	
	@PostConstruct
	public void init() {
		
		
		
	
		
		lista = daoFinanceiro.listar(FinanceiroUser.class);
		montarGrafico();
		
		
		
	}
	
	
	private void montarGrafico() {
		barChartModel =  new BarChartModel();

		ChartSeries gastos = new ChartSeries();

		for (FinanceiroUser financeiroUser : lista) {

			gastos.set(financeiroUser.getStatus(), financeiroUser.getValor());

		}
		barChartModel.addSeries(gastos);
		barChartModel.setTitle("Gráfico de Gastos");
	}

	
	
	
public double getTotalGastos() {
		
		lista = daoFinanceiro.listar(FinanceiroUser.class);
		
		double total = 0.0;
		
		
		for(FinanceiroUser financeiro : lista) {
			total += financeiro.getValor();
		}
		
		return total;
		
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



	public UsuarioPessoa getUser() {
		return user;
	}



	public void setUser(UsuarioPessoa user) {
		this.user = user;
	}


	public BarChartModel getBarChartModel() {
		return barChartModel;
	}


	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}
	
	
	
	
	
	

}
