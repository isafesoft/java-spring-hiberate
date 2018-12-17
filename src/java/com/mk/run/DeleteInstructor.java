package com.mk.run;

import com.mk.instructor.Instructor;
import com.mk.instructor.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructor {
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
            int id = 1;
            Instructor tempInstructor =
                    session.get(Instructor.class, id);
            if(null != tempInstructor) {
                // Note: will ALSO delete associated "details" object
                // coz of CascadeType.ALL
                session.delete(tempInstructor);
            }
            session.getTransaction().commit();
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
