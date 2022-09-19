package proskyskyprohomework.service;

import proskyskyprohomework.model.Employee;

import java.util.Collection;

public interface EmployeeService {


    Employee addEmployee( String firstName, String lastName, String department, int salary );


    Employee deleteEmployee( String firstName, String lastName, String department, int salary );


    Employee searchEmployee( String firstName, String lastName );

    Collection<Employee> findAll();
}
