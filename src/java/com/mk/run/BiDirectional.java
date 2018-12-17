package com.mk.run;

import com.mk.instructor.Instructor;
import com.mk.instructor.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BiDirectional {
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
            session.beginTransaction();
            int id = 2;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, id);

            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            // print the associated instructor
            System.out.println("the associated instructor: " +
                    tempInstructorDetail.getInstructor());

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
