package ru.job4j.ood.srp.serialize;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.MemoryStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {
    @Test
    void testGenerate() {
        MemoryStore store = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Sergey", now, now, 200));
        XMLReport xmlReport = new XMLReport(store, dateTimeParser);
        String xml = xmlReport.generate(employee -> true);
        assertThat(xml).contains("<employee>", "Ivan", "Sergey");
    }
}