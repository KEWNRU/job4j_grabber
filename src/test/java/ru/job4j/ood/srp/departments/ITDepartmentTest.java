package ru.job4j.ood.srp.departments;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportEngine;
import ru.job4j.ood.srp.store.MemoryStore;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ITDepartmentTest {
    @Test
    public void testItDepartment() throws JAXBException, IOException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ITDepartment(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name, Hired, Fired, Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(", ")
                .append(parser.parse(worker.getHired())).append(", ")
                .append(parser.parse(worker.getFired())).append(", ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

}



