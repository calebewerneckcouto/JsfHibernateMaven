package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DaoGeneric;
import model.UsuarioPessoa;


@ViewScoped
@ManagedBean(name = "usuarioPessoaManageBean")
public class UsuarioPessoaManageBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();

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

		return "";
	}

}
