package persistence.dao;

import org.hibernate.Session;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  

import persistence.objects.DailyTreasuryYieldCurve;

public class DailyTreasuryYieldCurveDao {
	
	public static int register(DailyTreasuryYieldCurve treasuryCurve){  
		
		 int i=0;  
		 Session session=new Configuration().configure().buildSessionFactory().openSession();  
		          
		  Transaction t=session.beginTransaction();  
		  t.begin();  
		                  
		  i=(Integer)session.save(treasuryCurve);  
		                  
		  t.commit();  
		  session.close();  
		    
		  return i;  
		  
		 } 

}

  
 
  
	 
