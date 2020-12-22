/**
 * 
 */
package com.mvp.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvp.demo.model.Employee;
import com.mvp.demo.model.EmployeeEntity;

/**
 * @author User
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>  {

	Employee findBySalary(double salary);

	Optional<EmployeeEntity> findById(String id);

	void deleteById(String id);

}
