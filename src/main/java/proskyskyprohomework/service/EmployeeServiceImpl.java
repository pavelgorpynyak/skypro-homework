package proskyskyprohomework.service;

import org.springframework.stereotype.Service;
import proskyskyprohomework.exeption.EmployeeAlreadyAddedException;
import proskyskyprohomework.exeption.EmployeeNotFoundException;
import proskyskyprohomework.exeption.InvalidInputException;
import proskyskyprohomework.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee( String firstName, String lastName, String department, int salary ) {
        employeeValidate(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(getKey(firstName, lastName), employee);
        return employee;
    }

    private void employeeValidate( String firstName, String lastName ) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }

    @Override
    public Employee deleteEmployee( String firstName, String lastName, String department, int salary ) {
        employeeValidate(firstName, lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee searchEmployee( String firstName, String lastName ) {
        employeeValidate(firstName, lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public String getKey( String firstName, String lastName ) {
        return firstName + " " + lastName;
    }
}
