package task5.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task5.entity.Department;
import task5.exception.EntityException;
import task5.exception.ExceptionMessage;
import task5.repository.DepartmentRepository;

import java.util.List;

@Component
public class Util {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void throwExceptionIfNameIncorrect(String name) {
        if (!name.matches("[a-zA-Z\\s]*")) {
            throw new EntityException(ExceptionMessage.INCORRECT_DATA);
        }
    }

    public void throwExceptionIfDepartmentAlreadyExists(String name) {
        List<Department> departmentList = departmentRepository.findAll();
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(name)) {
                throw new EntityException(ExceptionMessage.DEPARTMENT_ALREADY_EXISTS);
            }
        }
    }
}


