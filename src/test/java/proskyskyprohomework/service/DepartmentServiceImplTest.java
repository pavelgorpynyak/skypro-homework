package proskyskyprohomework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proskyskyprohomework.exeption.EmployeeNotFoundException;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static proskyskyprohomework.EmployeeTestInfo.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void shouldFindEmployeeWithMaxSalaryByDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE, out.getMaxSalary(DEPARTMENT));
    }

    @Test
    public void shouldFindEmployeeWithMinSalaryByDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MIN_SALARY_EMPLOYEE, out.getMinSalary(DEPARTMENT));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenFindMaxSalaryInEmptyEmployeeList() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalary(DEPARTMENT));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenFindMinSalaryInEmptyEmployeeList() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> out.getMinSalary(DEPARTMENT));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenFindMaxSalaryInEmptyDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalary(WRONG_DEPARTMENT));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenFindMinSalaryInEmptyDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertThrows(EmployeeNotFoundException.class, () -> out.getMinSalary(WRONG_DEPARTMENT));
    }

    @Test
    public void shouldReturnAllEmployeesByDepartmentWhenEmployeesEvist() {
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);
        assertEquals(DEPARTMENT_MAP, out.getAllSortedByDepartment());
    }

    @Test
    public void shouldReturnEmptyMapWhenEmployeeDontExist() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertEquals(emptyList(), out.getEmployeeByDepartment(DEPARTMENT));
    }

    @Test
    public void shouldReturnEmployeesByDepartmentWhenDepartmentIsCorrectEmployeeExistThere() {
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(singletonList(MIN_SALARY_EMPLOYEE),out.getEmployeeByDepartment(DEPARTMENT));
        assertEquals(singletonList(DIFFERENT_DEPARTMENT_EMPLOYEE),out.getEmployeeByDepartment(WRONG_DEPARTMENT));

    }

    @Test
    public void shouldReturnEmptyListWhenEmployeesDontFoundInDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(emptyList(), out.getEmployeeByDepartment(WRONG_DEPARTMENT));
    }
}