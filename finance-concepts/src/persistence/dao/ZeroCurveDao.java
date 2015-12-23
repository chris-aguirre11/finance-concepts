package persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.criterion.Restrictions;

import persistence.objects.DailyTreasuryYieldCurve;
import persistence.objects.ZeroCurve;

public class ZeroCurveDao {
	
	public static int register(ZeroCurve zeroCurve) {  
		
		int i=0;  
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
          
		Transaction t = session.beginTransaction();  
		t.begin();  
	                  
		i=(Integer)session.save(zeroCurve);  
	                  
		t.commit();  
		session.close();  
		    
		return i;  
	}
	
	public static ZeroCurve retrieve() {  
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
        
		int zeroCurveId = 1;
		Transaction t = session.beginTransaction();  
		t.begin();  
		
		Criteria criteria = session.createCriteria(ZeroCurve.class);
		criteria.add(Restrictions.eq("id", zeroCurveId));
		ZeroCurve zeroCurve = (ZeroCurve) criteria.uniqueResult();
		
		t.commit();  
		session.close();  
		
		return zeroCurve;
	} 
}

  
 
  
	 
