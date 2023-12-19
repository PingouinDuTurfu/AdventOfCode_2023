package day_19.module;

import java.util.List;

public record WorkflowPart(List<WorkflowRule> rules, String defaultOutput){}
