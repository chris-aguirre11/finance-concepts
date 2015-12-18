package persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.criterion.Restrictions;

import persistence.objects.DailyTreasuryYieldCurve;

public class DailyTreasuryYieldCurveDao {
	
	public static int register(DailyTreasuryYieldCurve treasuryCurve) {  
		
		int i=0;  
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
          
		Transaction t = session.beginTransaction();  
		t.begin();  
	                  
		i=(Integer)session.save(treasuryCurve);  
	                  
		t.commit();  
		session.close();  
		    
		return i;  
	}
	
	public static DailyTreasuryYieldCurve retrieve() {  
		Session session = new Configuration().configure().buildSessionFactory().openSession();  
        
		int treasuryCurveId = 1;
		Transaction t = session.beginTransaction();  
		t.begin();  
		
		Criteria criteria = session.createCriteria(DailyTreasuryYieldCurve.class);
		criteria.add(Restrictions.eq("id", treasuryCurveId));
		DailyTreasuryYieldCurve treasuryYieldCurve = (DailyTreasuryYieldCurve) criteria.uniqueResult();
		
		t.commit();  
		session.close();  
		
		return treasuryYieldCurve;
	} 
}

  
 
  
	 
