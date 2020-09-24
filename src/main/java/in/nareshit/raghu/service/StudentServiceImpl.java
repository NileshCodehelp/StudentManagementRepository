package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.dao.StudentRepository;
import in.nareshit.raghu.modal.Student;



@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentRepository repo;

	@Override
	public void createStudent(Student student) {
		repo.save(student);
	}
	
	@Override
	public List<Student> getAllStudents() {
		return repo.findAll();
	}

	@Override
	public Optional<Student> getOneStudent(Integer id) {
		return repo.findById(id);
	}
}
