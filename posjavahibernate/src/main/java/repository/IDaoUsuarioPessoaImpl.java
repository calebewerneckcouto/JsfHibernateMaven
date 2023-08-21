package repository;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.UsuarioPessoa;
import posjavamavenhibernate.HibernateUtil;

public class IDaoUsuarioPessoaImpl implements IDaoUsuarioPessoa {
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	

	@Override
	public UsuarioPessoa consultarUsuario(String login, String senha) {
		
		
		try {
			
			UsuarioPessoa usuarioPessoa = null;
			
			
			EntityTransaction transaction = entityManager.getTransaction(); //Obejto de transação
			transaction.begin();// Inicia uma transação
			
			
			usuarioPessoa = (UsuarioPessoa) entityManager.createQuery("select u from UsuarioPessoa u where u.login = '" + login +"' and u.senha = '" + senha + "'").getSingleResult();
			
			
			transaction.commit();// Grava no banco de dados
			entityManager.close();

			
			
			
			return usuarioPessoa;
		
			
            
		}
			
		

			
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Usuario ou Senha Errado!"));
			e.printStackTrace();
			
		}
		return null;
		
	}}
			
	

	


