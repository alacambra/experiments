package albert.lacambra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;

import org.hibernate.Session;

import albert.lacambra.models.Bussines;
import albert.lacambra.models.Category;
import albert.lacambra.models.Chekin;
import albert.lacambra.models.ChekinInfo;
import albert.lacambra.models.Review;
import albert.lacambra.models.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.tools.reflect.Loader;

public class Reader {
	ObjectMapper mapper = new ObjectMapper();
	static String bussinesFile = "yelp_training_set_business.json";
	static String reviewsFile = "yelp_training_set_review.json";
	static String usersFile = "yelp_training_set_user.json";
	static String chekinFile = "yelp_training_set_checkin.json";

	public static void main(String[] args) {
		Reader reader =  new Reader();
		
		
		reader.load(User.class, usersFile, new Action<User>() {

			public void run(User object, Session session) {
				session.save(object);
			}
		});
			
		reader.load(Bussines.class, bussinesFile, new Action<Bussines>() {
			
			public void run(Bussines bussines, Session session) {
				
				session.save(bussines);
				
				for ( Category category : bussines.getCategories() ) {
					category.addBussines(bussines);
					Category c = (Category) session.get(Category.class, category.getName());
					
					c = c == null ? category : c;
					
					session.save(c);
				}
				
			}
		});
		reader.load(Review.class, reviewsFile, new Action<Review>() {

			public void run(Review review, Session session) {
				
				session.save(review);
				
			}
			
		});
		reader.load(ChekinInfo.class, chekinFile, new Action<ChekinInfo>() {

			public void run(ChekinInfo object, Session session) {
				
				for (Entry<String, Integer> chekin : object.getCheckin_info().entrySet()){
					
					Chekin c = new Chekin(object.getBusiness_id(), chekin.getKey(), chekin.getValue());
					session.save(c);
					
				}
			}
			
		});
	}


	public <T> void load(Class<?> clazz, String filename, Action<T> action) {
		URL url = getResource(filename);
		BufferedReader br;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			br = new BufferedReader(new FileReader(url.getFile()));

			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
//					sb.append(line);
//					sb.append("\n");
//					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//					session.beginTransaction();
					T b = (T) mapper.readValue(line, clazz);
					action.run(b, session);
//					session.getTransaction().commit();
//					System.out.println(line);
					line = br.readLine();
				}
			}catch(Exception e) {
				
				System.err.println(e);
				
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		session.getTransaction().commit();

	}

	public URL getResource(String resource){

		URL url ;

		//Try with the Thread Context Loader. 
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader != null){
			url = classLoader.getResource(resource);
			if(url != null){
				return url;
			}
		}

		//Let's now try with the classloader that loaded this class.
		classLoader = Loader.class.getClassLoader();
		if(classLoader != null){
			url = classLoader.getResource(resource);
			if(url != null){
				return url;
			}
		}

		//Last ditch attempt. Get the resource from the classpath.
		return ClassLoader.getSystemResource(resource);
	}

	public interface Action<T> {
		public void run(T object, Session session);
	}
	
}



























