package ru.job4j.ood.srp.serialize;

import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

import ru.job4j.ood.srp.formatter.DateTimeParser;

public class XMLReport implements Report {
    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        DateTimeParser dateTimeParser = new ReportDateTimeParser();
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        xml.append("<employees>\n");
        for (Employee employee : store.findBy(filter)) {
            xml.append("""
                        <employee>
                            <name>%s</name>
                            <hired>%s</hired>
                            <fired>%s</fired>
                            <salary>%s</salary>
                        </employee>
                    """.formatted(employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    employee.getSalary()));
        }
        xml.append("</employees>\n");
        return xml.toString();
    }
}