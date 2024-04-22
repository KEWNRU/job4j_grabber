package ru.job4j.ood.tdd;

import java.awt.*;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);

     List<Session> find(Predicate<Session> filter);
}



