package task5.services;

import task5.entity.Department;

import java.util.List;


public interface DepartmentService {

    public List<Department> getAllDepartment();

    public void addNewDepartment(Department department);

    public void addPersonToDepartment(String departmentName, String personName, String personSurname);

    public void deletePersonFromDepartment(String departmentName, String personName, String personSurname);

    public void deleteDepartment(String departmentName);

}
