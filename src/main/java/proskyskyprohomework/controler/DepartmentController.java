package proskyskyprohomework.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proskyskyprohomework.model.Employee;
import proskyskyprohomework.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController( DepartmentService departmentService ) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    public Employee minSalary( @RequestParam("department") String department ) {
        return departmentService.getMinSalary(department);
    }

    @GetMapping("/departments/max-salary")
    public Employee maxSalary( @RequestParam("department") String department ) {
        return departmentService.getMaxSalary(department);
    }

    @GetMapping(value = "departments/all", params = "department")
    public List<Employee> allEmployeeOnDepartment( @RequestParam("department") String department ) {
        return departmentService.getEmployeeByDepartment(department);
    }

    @GetMapping("/departments/all")
    public Map<String, List<Employee>> getAllSortedByDepartment() {
        return departmentService.getAllSortedByDepartment();
    }
}
