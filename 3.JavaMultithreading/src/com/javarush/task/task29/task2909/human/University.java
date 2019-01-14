package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
	private String name;
	private int age;
	private List<Student> students = new ArrayList<>();
	
	public University(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public Student getStudentWithAverageGrade(double averageGrade) {
		//TODO:
		return students.stream()
				.filter(student -> student.getAverageGrade() == averageGrade)
				.findFirst().get();
	}
	
	public Student getStudentWithMaxAverageGrade() {
		//TODO:
		double maxAverage = students.stream()
				.mapToDouble(students -> students.getAverageGrade())
				.max()
				.getAsDouble();
		
		return students.stream()
				.filter(student -> student.getAverageGrade() == maxAverage)
				.findFirst().get();
	}
	
	public Student getStudentWithMinAverageGrade() {
		//TODO:
		double minAverage = students.stream()
				.mapToDouble(students -> students.getAverageGrade())
				.min()
				.getAsDouble();
		
		return students.stream()
				.filter(student -> student.getAverageGrade() == minAverage)
				.findFirst().get();
	}
	
	public void expel(Student student) {
		//TODO:
		students.remove(student);
	}
}