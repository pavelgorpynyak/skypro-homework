package proskyskyprohomework.service;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.events.Event;
import proskyskyprohomework.exeption.EmployeeAlreadyAddedException;
import proskyskyprohomework.exeption.EmployeeNotFoundException;
import proskyskyprohomework.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static proskyskyprohomework.EmployeeTestInfo.*;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeWhenTheyNotExist() {
        Employee expected = new Employee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);
        assertEquals(0, out.findAll().size());
        assertFalse(out.findAll().contains(expected));
        Employee current = out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);
        assertEquals(expected, current);
        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));
    }

    @Test
    public void trowEmployeeAlreadyAddedExceptionWhenTheyAdded() {
        Employee added = out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);
        assertTrue(out.findAll().contains(added));
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY));
    }

    @Test
    public void shouldFindEmployeeWhenTheyAdded() {
        Employee added = out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);
        assertEquals(added, out.searchEmployee(FIRST_NAME1, LAST_NAME1));
    }

    @Test
    public void shouldTrowEmployeeNotFoundWhenFindEmployeeDontExist() {
        assertEquals(0, out.findAll().size());
        assertThrows(EmployeeNotFoundException.class, () -> out.searchEmployee(FIRST_NAME1, LAST_NAME1));
    }

    @Test
    public void shouldRemoveEmployeeWhenTheyExist() {
        Employee expected = out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);

        assertEquals(1,out.findAll().size());
        assertTrue(out.findAll().contains(expected));

        Employee current = out.deleteEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);
        assertEquals(expected,current);
        assertTrue(out.findAll().isEmpty());
        assertFalse(out.findAll().contains(expected));
    }

    @Test
    public void shouldTrowEmployeeNotFoundWhenRemoveEmployeeWhichDoesntExist() {
        assertTrue(out.findAll().isEmpty());
        assertThrows(EmployeeNotFoundException.class, () -> out.searchEmployee(FIRST_NAME1, LAST_NAME1));
    }

    @Test
    public void shouldReturnEmployeeCollectionWhenEmployeeDosntAdded() {
        assertIterableEquals(emptyList(), out.findAll());

    }

    @Test
    public void shouldReturnListOfEmployeesWhenTheyAdded() {
        Employee employee1 = out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT, SALARY);
        Employee employee2 = out.addEmployee(FIRST_NAME2, LAST_NAME2, DEPARTMENT, SALARY);
        Collection<Employee> expected = List.of(employee1, employee2);

        Collection<Employee> curent = out.findAll();

        assertIterableEquals(expected, curent);
    }
}