package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

	@PostConstruct
	public void init() {

		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		user = daoUser.pesquisar(Long.parseLong(coduser), UsuarioPessoa.class);

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

}