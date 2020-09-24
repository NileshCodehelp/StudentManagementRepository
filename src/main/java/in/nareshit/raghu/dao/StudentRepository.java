package in.nareshit.raghu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.modal.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> { 
	

}
