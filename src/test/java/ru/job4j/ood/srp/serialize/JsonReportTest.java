package ru.job4j.ood.srp.serialize;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;
import java.util.Calendar;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class JsonReportTest {

    @Test
    public void whenJSONGenerated() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Sergey", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        JsonReport jsonReport = new JsonReport(store);
        String expected = "[\n"
                +  "  {\n"
                +  "    \"name\": \"Ivan\",\n"
                +  "    \"hired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"fired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"salary\": 100.0\n"
                +  "  },\n"
                +  "  {\n"
                +  "    \"name\": \"Sergey\",\n"
                +  "    \"hired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"fired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"salary\": 200.0\n"
                +  "  }\n"
                +  "]";
        assertThat(jsonReport.generate(employee -> true)).isEqualTo(expected);
    }
}
