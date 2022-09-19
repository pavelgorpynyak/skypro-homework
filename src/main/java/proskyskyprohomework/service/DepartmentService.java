package proskyskyprohomework.service;

import proskyskyprohomework.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getMinSalary( String department );

    Employee getMaxSalary( String department );

    List<Employee> getEmployeeByDepartment( String department );

    Map<String, List<Employee>> getAllSortedByDepartment();
}
