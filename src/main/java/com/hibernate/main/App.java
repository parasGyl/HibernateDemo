package com.hibernate.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.pojo.Student;
import com.hibernate.util.Hibernateutil;

public class App {

	public static void main(String[] args) {

		try (Session session = Hibernateutil.getSessionFactory(Student.class).openSession()) {
			Student student = new Student();
			//student.setId(2l);
			student.setForename("Aakash");
			student.setSurname("Bansal");
			student.setRollNo(27);
			session.beginTransaction();
			session.persist(student);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
