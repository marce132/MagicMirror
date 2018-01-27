package Manager;

import java.time.Instant;
import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import SensorData.TempAndHumidity;

/**
 * Wetterdatena us Messtation
 * @author Markus
 *
 */
public class TempAndHumdityManager {

	
	protected SessionFactory sessionFactory;
	public void setup() {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	public void exit() {
		sessionFactory.close();
	}
	
	public void createNewEntry() {
		TempAndHumidity tah = new TempAndHumidity();
		tah.setId((long) 1);
		tah.setTemperature(24.2);
		tah.setHumidity(69);
		
				
		Instant machineTimestamp = Instant.now();
		
		tah.setMeasureDate(machineTimestamp);
				
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(tah);
		
		session.getTransaction().commit();
		session.clear();
	}
	
	
	public TempAndHumidity getCurrTempAndHum() {
		
		Session session = sessionFactory.openSession();
		
		String hql = "FROM TempAndHumidity WHERE id = ( select max(id) from TempAndHumidity)";
		Query query = session.createQuery(hql);
		ArrayList<TempAndHumidity> results = (ArrayList<TempAndHumidity>) query.list();
		return results.get(0);
		
	}
	
	public static void main(String args[]) {
		TempAndHumdityManager tah = new TempAndHumdityManager();
		tah.setup();
	//	tah.createNewEntry();
		tah.getCurrTempAndHum();
	}
	
	

	
	
}
