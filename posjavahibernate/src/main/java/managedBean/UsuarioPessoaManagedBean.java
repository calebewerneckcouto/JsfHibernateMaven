package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.DaoEmail;
import dao.DaoUsuario;
import model.EmailUser;
import model.UsuarioPessoa;
import repository.IDaoUsuarioPessoa;
import repository.IDaoUsuarioPessoaImpl;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private DaoUsuario<UsuarioPessoa> daoGeneric = new DaoUsuario<UsuarioPessoa>();
	private BarChartModel barCharModel = new BarChartModel();
	private EmailUser emailuser = new EmailUser();
	private DaoEmail<EmailUser> daoEmail = new DaoEmail<EmailUser>();
	private String campoPesquisa;
	private IDaoUsuarioPessoa iDaoUsuarioPessoa = new IDaoUsuarioPessoaImpl();

	@PostConstruct
	public void init() {
		list = daoGeneric.listar(UsuarioPessoa.class);
		montarGrafico();
	}

	private void montarGrafico() {
		barCharModel =  new BarChartModel();

		ChartSeries userSalario = new ChartSeries();

		for (UsuarioPessoa usuarioPessoa : list) {

			userSalario.set(usuarioPessoa.getNome(), usuarioPessoa.getSalario());

		}
		barCharModel.addSeries(userSalario);
		barCharModel.setTitle("Gráfico de Salários");
	}

	public BarChartModel getBarCharModel() {
		return barCharModel;
	}

	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public String salvar() {
		daoGeneric.salvar(usuarioPessoa);
		list.add(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
		montarGrafico();
		return "usuario-salvo";
	}

	public String novo() {
		usuarioPessoa = new UsuarioPessoa();
		return "";
	}

	public List<UsuarioPessoa> getList() {
		return list;
	}

	public String remover() {

		try {
			daoGeneric.removerUsuario(usuarioPessoa);
			list.remove(usuarioPessoa);
			montarGrafico();
			usuarioPessoa = new UsuarioPessoa();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));
			montarGrafico();

		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Informação: ", "Existem telefones para o usuario!"));
			} else {
				e.printStackTrace();
			}
		}

		return "";
	}

	public void setEmailuser(EmailUser emailuser) {
		this.emailuser = emailuser;
	}

	public EmailUser getEmailuser() {
		return emailuser;
	}

	public void addEmail() {
		emailuser.setUsuarioPessoa(usuarioPessoa);
		emailuser = daoEmail.updateMerge(emailuser);
		usuarioPessoa.getEmails().add(emailuser);
		emailuser = new EmailUser();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado..", "Salvo com sucesso!"));
	}

	public void removerEmail() throws Exception {
		String codigoemail = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigoemail");

		EmailUser remover = new EmailUser();
		remover.setId(Long.parseLong(codigoemail));
		daoEmail.deletarPoId(remover);
		usuarioPessoa.getEmails().remove(remover);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado..", "Removido com sucesso!"));
	}
	
	
	
	public void pesquisar() {
		
		list = daoGeneric.pesquisar(campoPesquisa);
		montarGrafico();
		
	}
	
	
	public String logar() {
		
		
		UsuarioPessoa pessoa  = iDaoUsuarioPessoa.consultarUsuario(usuarioPessoa.getLogin(), usuarioPessoa.getSenha());
		
		if(pessoa != null) {
			
			
					
			return "principal.jsf";
		}
		
		
		return "index.jsf";
	}
	
	
	

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public DaoUsuario<UsuarioPessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoUsuario<UsuarioPessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public DaoEmail<EmailUser> getDaoEmail() {
		return daoEmail;
	}

	public void setDaoEmail(DaoEmail<EmailUser> daoEmail) {
		this.daoEmail = daoEmail;
	}

	public IDaoUsuarioPessoa getiDaoUsuarioPessoa() {
		return iDaoUsuarioPessoa;
	}

	public void setiDaoUsuarioPessoa(IDaoUsuarioPessoa iDaoUsuarioPessoa) {
		this.iDaoUsuarioPessoa = iDaoUsuarioPessoa;
	}

	public void setList(List<UsuarioPessoa> list) {
		this.list = list;
	}

	public void setBarCharModel(BarChartModel barCharModel) {
		this.barCharModel = barCharModel;
	}
	
	
	
	

}