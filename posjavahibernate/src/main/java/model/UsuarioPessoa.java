package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({

		@NamedQuery(name = "UsuarioPessoa.todos", query = "select u from UsuarioPessoa u"),
		@NamedQuery(name = "UsuarioPessoa.buscaPorNome", query = "select u from UsuarioPessoa u where u.nome = :nome") })
public class UsuarioPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;

	private String sobrenome;
	private String login;
	private String senha;
	private String sexo;
	private int idade;
	private Double salario;

	@OneToMany(mappedBy = "usuarioPessoa", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<TelefoneUser> telefoneUsers = new ArrayList<TelefoneUser>();

	@OneToMany(mappedBy = "usuarioPessoa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<FinanceiroUser> financeiroUsers = new ArrayList<FinanceiroUser>();

	@OneToMany(mappedBy = "usuarioPessoa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<EmailUser> emails = new ArrayList<EmailUser>();

	public void setEmails(List<EmailUser> emails) {
		this.emails = emails;
	}

	public List<EmailUser> getEmails() {
		return emails;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setTelefoneUsers(List<TelefoneUser> telefoneUsers) {
		this.telefoneUsers = telefoneUsers;
	}

	public List<TelefoneUser> getTelefoneUsers() {
		return telefoneUsers;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getIdade() {
		return idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FinanceiroUser> getFinanceiroUsers() {
		return financeiroUsers;
	}

	public void setFinanceiroUsers(List<FinanceiroUser> financeiroUsers) {
		this.financeiroUsers = financeiroUsers;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "UsuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", login=" + login
				+ ", senha=" + senha + ", sexo=" + sexo + ", idade=" + idade + ", salario=" + salario
				+ ", telefoneUsers=" + telefoneUsers + ", financeiroUsers=" + financeiroUsers + ", emails=" + emails
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPessoa other = (UsuarioPessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}