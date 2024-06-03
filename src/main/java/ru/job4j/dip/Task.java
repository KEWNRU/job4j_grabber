package ru.job4j.dip;

public class Task {

    /* Класс client зависит от DataAcces, что является нарушение DIP */
    static class DataAcces {
        void execute() {
            System.out.println("Hello World");
        }
    }

   static class Client {
        DataAcces dataAcces = new DataAcces();

        void execute() {
            dataAcces.execute();
        }
    }
    /* Решением будет являться разделение через interface */

    interface AOD {
        void execute();
    }

    static class DataAccess implements AOD {
        public void execute() {
            System.out.println("Hello World");
        }
    }

    static class Clients {

        void execute(AOD aod) {
            aod.execute();
        }
    }
}