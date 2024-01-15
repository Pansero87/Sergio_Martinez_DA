package app;

import org.hibernate.Session;

import Controller.MusicDAO;
import Controller.OrquestaDAO;
import Model.Musician;
import Model.Orchestra;
import ORM.HibernateUtil;

public class App {

	public static void main(String[] args) {
		
		OrquestaDAO orquestaDAO = new OrquestaDAO();
		
		Orchestra o1 = new Orchestra("Orquesta1", null, null);
		
		orquestaDAO.insertOrquesta(o1);
		
	

	}

}
