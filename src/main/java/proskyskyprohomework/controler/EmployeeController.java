package proskyskyprohomework.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proskyskyprohomework.model.Employee;
import proskyskyprohomework.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee( @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("department") String department,
                                 @RequestParam("salary") int salary ) {
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee deliteEmployee( @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("department") String department,
                                    @RequestParam("salary") int salary ) {
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        return employeeService.deleteEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee searchEmployee( @RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName) {
        return employeeService.searchEmployee(firstName,lastName);
    }


    @GetMapping
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}
