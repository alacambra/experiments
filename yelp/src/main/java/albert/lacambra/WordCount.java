package albert.lacambra;

import java.util.List;

import org.hibernate.Session;

import albert.lacambra.models.Review;

public class WordCount {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Review> reviews = session.createCriteria(Review.class).list();
		int i = reviews.size();
		
		for ( Review review : reviews ) {
			
			if ( i%(reviews.size()/100) == 0 ) 
				System.out.println(i*100/reviews.size());
			
			review.setWords(review.getText().split(" ").length);
			session.update(review);
			i--;
		}	
		
		session.getTransaction().commit();
	}

}
