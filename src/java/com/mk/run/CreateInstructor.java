package com.mk.run;

import com.mk.hibernate.demo.Student;
import com.mk.instructor.Instructor;
import com.mk.instructor.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructor {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
                configure().
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the objects
            Instructor tempInstructor = new Instructor("Chad", "Darby",
                    "darby@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://ytb.com/darby", "love code");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            System.out.println("Saving instructor:" + tempInstructor);
            session.beginTransaction();
            session.save(tempInstructor);
            session.getTransaction().commit();
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
            session.beginTransaction();

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
