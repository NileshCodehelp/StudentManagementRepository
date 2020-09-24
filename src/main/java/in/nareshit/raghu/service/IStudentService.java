package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;


import in.nareshit.raghu.modal.Student;



public interface IStudentService {  
	
	void createStudent(Student student);
	List<Student> getAllStudents();
	Optional<Student> getOneStudent(Integer id);

	
}
