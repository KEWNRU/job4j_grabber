package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public void add(Session session) {

    }

    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> sessions = new ArrayList<>();
        return sessions;
    }
}
