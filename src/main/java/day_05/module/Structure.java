package day_05.module;

import java.util.ArrayList;
import java.util.List;

public class Structure {

    private final List<StructurePart> subParts = new ArrayList<>();

    public Structure() {}

    public void addPart(StructurePart part) {
        subParts.add(part);
    }

    public double getValues(double source) {
        for (StructurePart part : subParts) {
            double value = part.getValues(source);
            if(value != -1)
                return value;
        }
        return source;
    }

    public List<StructurePart> getSubParts() {
        return subParts;
    }
}
