package day_06.module;

public class Race {

    double time;
    double record_distance;

    public Race(int time, int record_distance) {
        this.time = time;
        this.record_distance = record_distance;
    }

    public Race(double time, double record_distance) {
        this.time = time;
        this.record_distance = record_distance;
    }

    public int compute() {
        int cont = 0;
        for (double i = 0; i < time; i++) {
            if (i * (time - i) > record_distance)
                cont++;
        }
        return cont;
    }
}
