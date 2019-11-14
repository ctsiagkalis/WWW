package www.erg4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserDao {

	public static int register(User u) {
		String str1;
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			str1 = (String) session.save(u);
			System.out.println("session.save(u): "+str1);
			System.out.println("u.getUsername(): "+u.getUsername());
			System.out.println("u.getPassword(): "+u.getPassword());
			t.commit();
			session.close();
			return 0;
		} catch (PersistenceException e) {
			System.out.println("********");
			System.out.println(e.hashCode());
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			System.out.println("********User already exists");
			session.close();
			return -1;
		}
	}

	public static int login(String username, String password) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		System.out.println("\n****username: " + username);
		System.out.println("\n****password: " + password);
		try {
			User user = (User) session.get(User.class, username);
			System.out.println("\n****user.username: " + user.getUsername());
			System.out.println("\n****user.password: " + user.getPassword());
			System.out.println("\n****password: " + password);
			if (password.contentEquals(user.getPassword())) {
				System.out.println("\n****Equals");
				return 0;
			}else {
				System.out.println("\n****Not equals");
				return 1;
			}
			
		} catch (Exception exception) {
			System.out.println("Exception occred while reading user data: " + exception.getMessage());
			return 1;
		}
	}
}