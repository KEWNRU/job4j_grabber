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

    class DataAccess implements AOD {
        public void execute() {
            System.out.println("Hello World");
        }
    }

    class Clients {
        AOD aod = new DataAccess();

        void execute() {
            aod.execute();
        }
    }
}