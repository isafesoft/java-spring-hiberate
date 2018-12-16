package com.mk.run;

import com.mk.hibernate.demo.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentMain {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            System.out.println("Create new student object...");
            Student tempStudent = new Student("Paul", "Wall", "paul@mk.com");
            session.beginTransaction();

            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

        }
        finally {
            factory.close();
        }
    }
}
