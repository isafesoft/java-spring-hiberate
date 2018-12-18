package com.mk.run;

import com.mk.instructor.Course;
import com.mk.instructor.Instructor;
import com.mk.instructor.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToMany {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
                configure().
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the objects
            session.beginTransaction();

            int id = 2;
            // add instructor & detail
            /*
            Instructor instructor =
                    new Instructor("Susan", "Public", "susan@gmail.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("http://susan.youtube.com", "Video Games");

            instructor.setInstructorDetail(instructorDetail);

            System.out.println("Instructor is: " + instructor);
            session.save(instructor);
            */

            createCourses(session, id);


            // print the associated instructor

            session.getTransaction().commit();
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
            factory.close();
        }

    }

    static private void createCourses(Session session, int id) {
        // add courses
        Instructor instructor =
                session.get(Instructor.class, id);

        Course course1 = new Course("Air Guitar");
        Course course2 = new Course("Pinball");
        instructor.add(course1);
        instructor.add(course2);

        // Save the courses
        session.save(course1);
        session.save(course2);
    }
}
