package managedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private FinanceiroUser financeiroUser = new FinanceiroUser();

	String campoPesquisa;
	String campoPesquisanome;
	

	private BarChartModel barChartModel = new BarChartModel();

	@PostConstruct
	public void init() {

		lista = daoFinanceiro.listar(FinanceiroUser.class);

		montarGrafico();
		montarGraficonome();

	}

	private Map<String, Double> calcularTotalPorNome(List<FinanceiroUser> lista) {
		Map<String, Double> totalPornome = new HashMap<>();

		for (FinanceiroUser financeiroUser : lista) {

			String nome = financeiroUser.getNome();
			double valor = financeiroUser.getValor();

			totalPornome.put(nome, totalPornome.getOrDefault(nome, 0.0) + valor);
		}

		return totalPornome;
	}

	private Map<String, Double> calcularTotalPorStatus(List<FinanceiroUser> lista) {
		Map<String, Double> totalPorStatus = new HashMap<>();

		for (FinanceiroUser financeiroUser : lista) {

			String status = financeiroUser.getStatus();
			double valor = financeiroUser.getValor();

			totalPorStatus.put(status, totalPorStatus.getOrDefault(status, 0.0) + valor);
		}

		return totalPorStatus;
	}

	private void montarGrafico() {
		barChartModel = new BarChartModel();

		ChartSeries gastos = new ChartSeries();
		Map<String, Double> totalPorStatus = calcularTotalPorStatus(lista);

		for (Map.Entry<String, Double> entry : totalPorStatus.entrySet()) {
			gastos.set(entry.getKey(), entry.getValue());
		}

		barChartModel.addSeries(gastos);
		barChartModel.setTitle("Gráfico de Gastos");
	}

	private void montarGraficonome() {
		barChartModel = new BarChartModel();

		ChartSeries nomes = new ChartSeries();
		Map<String, Double> totalPornome = calcularTotalPorNome(lista);

		for (Map.Entry<String, Double> entry : totalPornome.entrySet()) {
			nomes.set(entry.getKey(), entry.getValue());
		}

		barChartModel.addSeries(nomes);
		barChartModel.setTitle("Gráfico de Gastos");
	}

	public void recarregar() {
		lista = daoFinanceiro.listar(FinanceiroUser.class);
		montarGrafico();
		montarGraficonome();
	}

	public void pesquisar() throws Exception {

		lista = daoFinanceiro.pesquisar(campoPesquisa, campoPesquisanome);

		montarGrafico();

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

	public FinanceiroUser getFinanceiroUser() {
		return financeiroUser;
	}

	public void setFinanceiroUser(FinanceiroUser financeiroUser) {
		this.financeiroUser = financeiroUser;
	}

	public String getCampoPesquisanome() {
		return campoPesquisanome;
	}

	public void setCampoPesquisanome(String campoPesquisanome) {
		this.campoPesquisanome = campoPesquisanome;
	}

	
}
