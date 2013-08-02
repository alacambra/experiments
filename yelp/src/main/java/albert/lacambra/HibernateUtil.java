package albert.lacambra;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		Logger log = Logger.getLogger(HibernateUtil.class);
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().buildServiceRegistry();        

//			return configuration.buildSessionFactory(serviceRegistry);
			return new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			log.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
