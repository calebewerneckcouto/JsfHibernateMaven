package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import model.UsuarioPessoa;

@ViewScoped
@ManagedBean(name = "usuarioPessoaManageBean")
public class UsuarioPessoaManageBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();

	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public DaoGeneric<UsuarioPessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<UsuarioPessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public String salvar() {

		daoGeneric.salvar(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:","Salvo com Sucesso!!"));
		usuarioPessoa = new UsuarioPessoa();

		return "";
	}

	public String novo() {

		usuarioPessoa = new UsuarioPessoa();

		return "";
	}

	public List<UsuarioPessoa> getList() {
		
		list = daoGeneric.listar(UsuarioPessoa.class);
		
		return list;
	}
	
	public String remover() {
		
		daoGeneric.deletePorId(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:","Removido com Sucesso!!"));
		usuarioPessoa = new UsuarioPessoa();
		
		
		return "";
	}

	

}