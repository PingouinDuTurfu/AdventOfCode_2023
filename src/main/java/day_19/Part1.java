package day_19;

import common.ReadFile;
import day_19.module.InputData;
import day_19.module.WorkflowPart;
import day_19.module.WorkflowRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String REGEX_INPUT = "x=([0-9]+),m=([0-9]+),a=([0-9]+),s=([0-9]+)";
    private static final Pattern PATTERN_INPUT = Pattern.compile(REGEX_INPUT);
    private static final String REGEX_RULE = "([a-z]+)\\{(([xmas][><][0-9]+:[ARa-z]+,)+)([ARa-z]+)}";
    private static final String REGEX_SUB_RULE = "([xmas])([><])([0-9]+):([ARa-z]+)";
    private static final Pattern PATTERN_RULE = Pattern.compile(REGEX_RULE);
    private static final Pattern PATTERN_SUB_RULE = Pattern.compile(REGEX_SUB_RULE);

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_19.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean is_input = false;
        Map<String, WorkflowPart> workflows = new HashMap<>();
        List<InputData> inputs = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if(input[i].isBlank()) {
                is_input = true;
                continue;
            }
            if(is_input) {
                Matcher matcher = PATTERN_INPUT.matcher(input[i]);
                if(matcher.find())
                    inputs.add(new InputData(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
            } else {
                Matcher matcher = PATTERN_RULE.matcher(input[i]);
                if(matcher.find()) {
                    List<WorkflowRule> workflowRules = new ArrayList<>();
                    String[] rules = matcher.group(2).split(",");
                    for (int j = 0; j < rules.length; j++) {
                        Matcher matcher1 = PATTERN_SUB_RULE.matcher(rules[j]);
                        if(matcher1.find())
                            workflowRules.add(new WorkflowRule(
                                    matcher1.group(1).charAt(0),
                                    matcher1.group(2).charAt(0),
                                    Integer.parseInt(matcher1.group(3)),
                                    matcher1.group(4))
                            );
                    }
                    workflows.put(matcher.group(1), new WorkflowPart(workflowRules, matcher.group(4)));
                }
            }
        }

        int result = 0;
        for (InputData input1: inputs) {
            WorkflowPart workflowPart = workflows.get("in");
            String output = "";
            while (!output.equals("A") && !output.equals("R")) {
                boolean has_changed = false;
                for (WorkflowRule workflowRule: workflowPart.rules()) {
                    switch (workflowRule.key()) {
                        case 'x':
                            if((workflowRule.operator() == '>' && input1.x() > workflowRule.value()) || (workflowRule.operator() == '<' && input1.x() < workflowRule.value())) {
                                output = workflowRule.output();
                                has_changed = true;
                            }
                            break;
                        case 'm':
                            if((workflowRule.operator() == '>' && input1.m() > workflowRule.value()) || (workflowRule.operator() == '<' && input1.m() < workflowRule.value())) {
                                output = workflowRule.output();
                                has_changed = true;
                            }
                            break;
                        case 'a':
                            if((workflowRule.operator() == '>' && input1.a() > workflowRule.value()) || (workflowRule.operator() == '<' && input1.a() < workflowRule.value())) {
                                output = workflowRule.output();
                                has_changed = true;
                            }
                            break;
                        case 's':
                            if((workflowRule.operator() == '>' && input1.s() > workflowRule.value()) || (workflowRule.operator() == '<' && input1.s() < workflowRule.value())) {
                                output = workflowRule.output();
                                has_changed = true;
                            }
                            break;
                    }
                    if(has_changed) {
                        workflowPart = workflows.get(output);
                        break;
                    }
                }
                if(!has_changed) {
                    output = workflowPart.defaultOutput();
                    workflowPart = workflows.get(output);
                }
            }
            if(output.equals("A"))
                result += input1.x() + input1.m() + input1.a() + input1.s();
        }
        System.out.println(result);
    }
}
