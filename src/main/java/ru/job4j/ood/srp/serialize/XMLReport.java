package ru.job4j.ood.srp.serialize;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        StringBuilder  sb = new StringBuilder();
        Employees employee = new Employees(store.findBy(filter));
        try (StringWriter stringWriter = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(employee, stringWriter);
            String xml = stringWriter.toString();
            sb.append(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}