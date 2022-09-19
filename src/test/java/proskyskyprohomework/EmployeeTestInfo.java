package proskyskyprohomework;

import proskyskyprohomework.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeTestInfo {

    public static final String FIRST_NAME1 = "Ivan";
    public static final String FIRST_NAME2 = "Igor";
    public static final String LAST_NAME1 = "Ivanov";
    public static final String LAST_NAME2 = "Igorev";
    public static final int SALARY = 10000;
    public static final int MIN_SALARY = 5000;
    public static final String DEPARTMENT = "1";
    public static final String WRONG_DEPARTMENT = "100";
    public static final Employee MIN_SALARY_EMPLOYEE = new Employee( FIRST_NAME1, LAST_NAME1, DEPARTMENT, MIN_SALARY);
    public static final Employee MAX_SALARY_EMPLOYEE = new Employee(FIRST_NAME2, LAST_NAME2, DEPARTMENT, SALARY);
    public static final Set<Employee> EMPLOYEES = Set.of(MIN_SALARY_EMPLOYEE, MAX_SALARY_EMPLOYEE);
    public static final Employee DIFFERENT_DEPARTMENT_EMPLOYEE = new Employee(FIRST_NAME2, LAST_NAME2, WRONG_DEPARTMENT, SALARY);
    public static final Set<Employee> DIFFERENT_DEPARTMENT_EMPLOYEES = Set.of(MIN_SALARY_EMPLOYEE,DIFFERENT_DEPARTMENT_EMPLOYEE);
    public static final Map<String, List<Employee>> DEPARTMENT_MAP = DIFFERENT_DEPARTMENT_EMPLOYEES.stream().collect(groupingBy(Employee::getDepartment));

}
