package task5.services;

import task5.entity.Department;

import java.util.List;


public interface DepartmentService {

    public List<Department> getAllDepartment();

    public void addNewDepartment(Department department);

    public Department addPersonToDepartment(String departmentName, String personName, String personSurname);

    public Department deletePersonFromDepartment(String departmentName, String personName, String personSurname);

    public void deleteDepartment(String departmentName);

    public Department save(Department department);
}
