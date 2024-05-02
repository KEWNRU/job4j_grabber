package ru.job4j.ood.srp.departments;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;

public class HRDepartment implements Report {

    private final Store store;

    public HRDepartment(Store store) {
        this.store = store;

    }

    @Override
    public String generate(Predicate<Employee> filter) {
        TreeSet<Employee> employees = new TreeSet<>(Comparator.comparingDouble(Employee::getSalary));
        employees.addAll(store.findBy(filter));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
