package vn.elca.training.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.entity.Employee;

import java.time.LocalDate;

@ContextConfiguration(classes = {ApplicationWebConfig.class})
@RunWith(value= SpringRunner.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveAndUpdateEmployee() {
        Employee e = new Employee();
        e.setVisa("dtx");
        e.setFirstName("Duong");
        e.setLastName("Tran");
        e.setBirthDate(LocalDate.now());
        e = employeeRepository.save(e);
        e.setLastName("Tran Xuan");
        Employee exp = employeeRepository.save(e);
        Assert.assertEquals(exp, e);
    }
}
