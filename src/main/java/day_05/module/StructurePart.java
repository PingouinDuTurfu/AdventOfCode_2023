package day_05.module;

public class StructurePart {

    private double source_start;
    private double source_end;
    private double destination_start;
    private double range;


    public StructurePart(double source_start, double source_end, double destination_start, double range) {
        this.source_start = source_start;
        this.source_end = source_end;
        this.destination_start = destination_start;
        this.range = range;
    }

    public double getSource_start() {
        return source_start;
    }

    public double getSource_end() {
        return source_end;
    }

    public double getDestination_start() {
        return destination_start;
    }

    public double getRange() {
        return range;
    }

    public double getValues(double source) {
        if(source >= source_start && source <= source_end)
            return destination_start + (source - source_start);
        return -1;
    }

    @Override
    public String toString() {
        return "StructurePart{" +
                "source_start=" + source_start +
                ", source_end=" + source_end +
                ", destination_start=" + destination_start +
                ", range=" + range +
                '}';
    }
}
