package com.codegnan.university.managment;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.codegnan.univerity.exceptions.CourseNotFoundException;
import com.codegnan.univerity.exceptions.ProfessorNotFoundException;
import com.codegnan.univerity.exceptions.StudentNotFoundException;
public class UniversityManagment {
	private List<student> students; // List to hold students
	private List<professor> professors; // List to hold professors
	private List<course> courses; // List to hold courses
	// Constructor initializes empty lists for students, professors, and courses
	public UniversityManagment() {
		students = new ArrayList<>();
		professors = new ArrayList<>();
		courses = new ArrayList<>();
	}
	// Method to add a student
	public void addStudent(String name) {
		students.add(new student(name));
	}
	// Method to add a professor
	public void addProfessor(String name) {
		professors.add(new professor(name));
	}
	// Method to add a course
	public void addCourse(String title) {
		courses.add(new course(title));
	}
	// Method to enroll a student in a course
	public void enrollStudentInCourse(String studentName, String courseTitle)
			throws StudentNotFoundException, CourseNotFoundException {
		student student = findStudentByName(studentName); // Find the student by name
		course course = findCourseByTitle(courseTitle); // Find the course by title
		if (student == null) {
			throw new StudentNotFoundException("Student " + studentName + " not found.");
		}
		if (course == null) {
			throw new CourseNotFoundException("Course " + courseTitle + " not found.");
		}
		student.enrollInCourse(course); // Enroll the student in the course
	}
	// Method to assign a course to a professor
	public void assignCourseToProfessor(String professorName, String courseTitle)
			throws ProfessorNotFoundException, CourseNotFoundException {
		professor professor = findProfessorByName(professorName); // Find the professor by name
		course course = findCourseByTitle(courseTitle); // Find the course by title
		if (professor == null) {
			throw new ProfessorNotFoundException("Professor " + professorName + " not found.");
		}
		if (course == null) {
			throw new CourseNotFoundException("Course " + courseTitle + " not found.");
		}
		professor.assignCourse(course); // Assign the course to the professor
	}
	// Method to list all students
	public void listStudents() {
		if (students.isEmpty()) {
			System.out.println("No students available.");
		} else {
			System.out.println("Students:");
			for (student student : students) {
				System.out.println(student); // Print each student's name
			}
		}
	}
	// Method to list all professors
	public void listProfessors() {
		if (professors.isEmpty()) {
			System.out.println("No professors available.");
		} else {
			System.out.println("Professors:");
			for (professor professor : professors) {
				System.out.println(professor); // Print each professor's name
			}
		}
	}
	// Method to list all courses
	public void listCourses() {
		if (courses.isEmpty()) {
			System.out.println("No courses available.");
		} else {
			System.out.println("Courses:");
			for (course course : courses) {
				System.out.println(course); // Print each course's title
			}
		}
	}
	// Method to display the courses a student is enrolled in
	public void displayStudentCourses(String studentName) throws StudentNotFoundException {
		student student = findStudentByName(studentName); // Find the student by name
		if (student == null) {
			throw new StudentNotFoundException("Student " + studentName + " not found.");
		}
		System.out.println("Courses for student " + studentName + ":");
		for (course course : student.getEnrolledCourses()) {
			System.out.println(course); // Print each enrolled course

		}
	}
	// Method to display the courses assigned to a professor
	public void displayProfessorCourses(String professorName) throws ProfessorNotFoundException {
		professor professor = findProfessorByName(professorName); // Find the professor by name
		if (professor == null) {
			throw new ProfessorNotFoundException("Professor " + professorName + " not found.");
		}
		System.out.println("Courses assigned to professor " + professorName + ":");
		for (course course : professor.getAssignedCourses()) {
			System.out.println(course); // Print each assigned course
		}
	}
	// Helper method to find a student by name
	private student findStudentByName(String name) {
		for (student student : students) {
			if (student.getName().equalsIgnoreCase(name)) {
				return student; // Return the student if found
			}
		}
		return null; // Return null if student not found
	}
	// Helper method to find a professor by name
	private professor findProfessorByName(String name) {
		for (professor professor : professors) {
			if (professor.getName().equalsIgnoreCase(name)) {
				return professor; // Return the professor if found
			}
		}
		return null; // Return null if professor not found
	}
	// Helper method to find a course by title
	private course findCourseByTitle(String title) {
		for (course course : courses) {
			if (course.getTitle().equalsIgnoreCase(title)) {
				return course; // Return the course if found
			}
		}
		return null; // Return null if course not found
	}
	// Main method to interact with the system through a menu
	public static void main(String[] args) {
		UniversityManagment management = new UniversityManagment();
		Scanner scanner = new Scanner(System.in); // Scanner for user input
		boolean running = true; // Flag to control the while loop
		// Loop to display the menu and process user choices
		while (running) {
			System.out.println("\nUniversity Management System");
			System.out.println("1. Add Student");
			System.out.println("2. Add Professor");
			System.out.println("3. Add Course");
			System.out.println("4. Enroll Student in Course");
			System.out.println("5. Assign Course to Professor");
			System.out.println("6. List Students");
			System.out.println("7. List Professors");
			System.out.println("8. List Courses");
			System.out.println("9. Display Student Courses");
			System.out.println("10. Display Professor Courses");
			System.out.println("11. Exit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt(); // Read user choice
			scanner.nextLine(); // Consume the newline character
			// Switch case to handle user choice
			try {
				switch (choice) {
				case 1:
					System.out.print("Enter student name: ");
					String studentName = scanner.nextLine();
					management.addStudent(studentName); // Add the student
					break;
				case 2:
					System.out.print("Enter professor name: ");
					String professorName = scanner.nextLine();
					management.addProfessor(professorName); // Add the professor
					break;
				case 3:
					System.out.print("Enter course title: ");
					String courseTitle = scanner.nextLine();
					management.addCourse(courseTitle); // Add the course
					break;
				case 4:
					System.out.print("Enter student name: ");
					String enrollStudent = scanner.nextLine();
					System.out.print("Enter course title: ");
					String enrollCourse = scanner.nextLine();
					try {
						management.enrollStudentInCourse(enrollStudent, enrollCourse); // Enroll student in course
					} catch (StudentNotFoundException | CourseNotFoundException e) {
						System.out.println(e.getMessage()); // Handle exception
					}
					break;
				case 5:
					System.out.print("Enter professor name: ");
					String assignProfessor = scanner.nextLine();
					System.out.print("Enter course title: ");
					String assignCourse = scanner.nextLine();
					try {
						management.assignCourseToProfessor(assignProfessor, assignCourse); // Assign course to professor
					} catch (ProfessorNotFoundException | CourseNotFoundException e) {
						System.out.println(e.getMessage()); // Handle exception
					}
					break;
				case 6:
					management.listStudents(); // List all students
					break;
				case 7:
					management.listProfessors(); // List all professors
					break;
				case 8:
					management.listCourses(); // List all courses
					break;
				case 9:
					System.out.print("Enter student name: ");
					String displayStudent = scanner.nextLine();
					try {
						management.displayStudentCourses(displayStudent); // Display courses for a student
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage()); // Handle exception
					}
					break;
				case 10:
					System.out.print("Enter professor name: ");
					String displayProfessor = scanner.nextLine();
					try {
						management.displayProfessorCourses(displayProfessor); // Display courses for a professor
					} catch (ProfessorNotFoundException e) {
						System.out.println(e.getMessage()); // Handle exception
					}
					break;
				case 11:
					running = false; // Exit the loop
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please enter a number between 1 and 11."); // Handle invalid
																									// input
					break;
				}
			} catch (Exception e) {
				System.out.println("An unexpected error occurred: " + e.getMessage());
			}
		}
		scanner.close(); // Close the scanner
	}
}

			
				
			
		