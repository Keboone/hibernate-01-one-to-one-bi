package learning.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Instructor;
import learning.hibernate.demo.entity.InstructorDetail;


public class DeleteInstructorDetailDemo
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
			// start transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
					
			
			// print the instructor detail
			System.out.println("tempIinstructorDetail :" + tempInstructorDetail);
			
			
			// print the associated intructor
			System.out.println("the associated instructor: " + 
									tempInstructorDetail.getInstructor());
			
			
			// additionally remove associated  object reference
			// break bi-directional link!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			// delete instructor detail // to usunie też cascadowo Instructor'a
			System.out.println("deleting tempInstructorDetail: " + tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}
		// catch exception
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
		
		
	}
	
}
