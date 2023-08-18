package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.DaoFinanceiro;
import dao.DaoUsuario;
import model.FinanceiroUser;
import model.UsuarioPessoa;

@ManagedBean(name = "financeiroManagedBean")
@ViewScoped
public class FinanceiroManagedBean {

	private UsuarioPessoa user = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<UsuarioPessoa>();
	private DaoFinanceiro<FinanceiroUser> daoFinanceiro = new DaoFinanceiro<FinanceiroUser>();

	private FinanceiroUser financeiroUser = new FinanceiroUser();
	private BarChartModel barChartModel = new BarChartModel();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private DaoUsuario<UsuarioPessoa> daoGeneric = new DaoUsuario<UsuarioPessoa>();

	@PostConstruct
	public void init() {
		list = daoGeneric.listar(UsuarioPessoa.class);
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		user = daoUser.pesquisar(Long.parseLong(coduser), UsuarioPessoa.class);

		

	}
	
	
	public String novo() {
		
		financeiroUser = new FinanceiroUser();
		
		return "";
	}
	
	
	
	public String salvar() {
		financeiroUser.setUsuarioPessoa(user);
		daoFinanceiro.salvar(financeiroUser);
		financeiroUser = new FinanceiroUser();
		user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));

		return "";
	}

	public String removeFinanceiro() throws Exception {

		daoFinanceiro.deletarPoId(financeiroUser);
		user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
		financeiroUser = new FinanceiroUser();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Dados Removido!"));
		return "";
	}

	public DaoUsuario<UsuarioPessoa> getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUsuario<UsuarioPessoa> daoUser) {
		this.daoUser = daoUser;
	}

	public DaoFinanceiro<FinanceiroUser> getDaoFinanceiro() {
		return daoFinanceiro;
	}

	public void setDaoFinanceiro(DaoFinanceiro<FinanceiroUser> daoFinanceiro) {
		this.daoFinanceiro = daoFinanceiro;
	}

	public FinanceiroUser getFinanceiroUser() {
		return financeiroUser;
	}

	public void setFinanceiroUser(FinanceiroUser financeiroUser) {
		this.financeiroUser = financeiroUser;
	}

	public void setUser(UsuarioPessoa user) {
		this.user = user;
	}

	public UsuarioPessoa getUser() {
		return user;
	}

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public List<UsuarioPessoa> getList() {
		return list;
	}

	public void setList(List<UsuarioPessoa> list) {
		this.list = list;
	}

	public DaoUsuario<UsuarioPessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoUsuario<UsuarioPessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	
}