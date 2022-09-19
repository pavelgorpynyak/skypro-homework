package proskyskyprohomework.service;

import org.springframework.stereotype.Service;
import proskyskyprohomework.exeption.EmployeeNotFoundException;
import proskyskyprohomework.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    EmployeeService employeeService;

    public DepartmentServiceImpl( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMinSalary( String department ) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getMaxSalary( String department ) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> getEmployeeByDepartment( String department ) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment().equals(department))
                .toList();
    }

    @Override
    public Map<String, List<Employee>> getAllSortedByDepartment() {
        return employeeService.findAll().stream()
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparing(Employee::getLastName)
                )
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    private boolean employeeValidate( String firstName, String lastName ) {
        return isAlpha(firstName) && isAlpha(lastName);
    }
}
