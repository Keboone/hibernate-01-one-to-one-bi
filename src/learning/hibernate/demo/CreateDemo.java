package learning.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Instructor;
import learning.hibernate.demo.entity.InstructorDetail;


public class CreateDemo
{
	public static void main(String[] args)
	{
		// create session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		
		Session session = factory.getCurrentSession();
		
		try 
		{
			// create the objects
//			Instructor tempInstructor =
//					new Instructor("Chad", "Darby", "darby@luv2code.com");
//			
//			InstructorDetail tempInstructorDetail = 
//					new InstructorDetail("youtube.com/affafaga", "luv 2 code!!");
			
			Instructor tempInstructor =
					new Instructor("Jam", "Lasica", "lasica@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("youtube.com/1234", "Jam lasica!!");
			
			
			
			// associate the objects (połącz? obiekty razem)
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			// start transaction
			session.beginTransaction();
			
			// save the instructor
			// this will ALSO save the details object because of the CascadeType.ALL
			System.out.println("Saving instructor!!" + tempInstructor);
			session.save(tempInstructor);
			
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
		finally 
		{
			factory.close();
		}
		
		
	}
	
}
