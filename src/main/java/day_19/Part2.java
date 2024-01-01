package day_19;

import common.ReadFile;
import day_19.module.InputData;
import day_19.module.RangeData;
import day_19.module.WorkflowPart;
import day_19.module.WorkflowRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String REGEX_RULE = "([a-z]+)\\{(([xmas][><][0-9]+:[ARa-z]+,)+)([ARa-z]+)}";
    private static final String REGEX_SUB_RULE = "([xmas])([><])([0-9]+):([ARa-z]+)";
    private static final Pattern PATTERN_RULE = Pattern.compile(REGEX_RULE);
    private static final Pattern PATTERN_SUB_RULE = Pattern.compile(REGEX_SUB_RULE);
    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("tmp.txt");
//            input = ReadFile.read("day_19.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Map<String, WorkflowPart> workflows = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if(input[i].isBlank())
                break;
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

        int result = 0;
        for (Map.Entry<String, WorkflowPart> entry: workflows.entrySet()) {
            List<RangeData> rangeDataList = new ArrayList<>();
            RangeData rangeDataStart = new RangeData(1, 4000, 1, 4000, 1, 4000, 1, 4000, entry.getKey());
            rangeDataList.add(rangeDataStart);

            while (!rangeDataList.isEmpty()) {
                RangeData rangeData = rangeDataList.get(0);
                rangeDataList.remove(0);
                String workflow_key = rangeData.next_workflow();
                WorkflowPart workflow = workflows.get(workflow_key);
                for (WorkflowRule workflowRule: workflow.rules()) {
                    switch (workflowRule.key()) {
                        case 'x':
                            if(workflowRule.operator() == '>') {
                                if (rangeData.x_max() < workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - Math.max(0, workflowRule.value() + 1)) * (rangeData.m_max() - rangeData.m_min()) * (rangeData.a_max() - rangeData.a_min()) * (rangeData.s_max() - rangeData.s_min());
                                        System.out.println("x > A" + result);
                                        break;
                                    case "R":
                                        System.out.println("x > R");
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(workflowRule.value() + 1, rangeData.x_max(), rangeData.m_min(), rangeData.m_max(), rangeData.a_min(), rangeData.a_max(), rangeData.s_min(), rangeData.s_max(), workflowRule.output()));
                                        System.out.println("x > sub new x_max " + workflowRule.value() + 1 + " x_min " + rangeData.x_max());
                                        break;
                                }
                                break;
                            } else {
                                if (rangeData.x_min() > workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += Math.max(0, workflowRule.value() - 1 - rangeData.x_min()) * (rangeData.m_max() - rangeData.m_min()) * (rangeData.a_max() - rangeData.a_min()) * (rangeData.s_max() - rangeData.s_min());
                                        System.out.println("x < A" + result);
                                        break;
                                    case "R":
                                        System.out.println("x < R");
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), workflowRule.value() - 1, rangeData.m_min(), rangeData.m_max(), rangeData.a_min(), rangeData.a_max(), rangeData.s_min(), rangeData.s_max(), workflowRule.output()));
                                        break;
                                }
                                break;
                            }
                        case 'm':
                            if(workflowRule.operator() == '>') {
                                if (rangeData.m_max() < workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - rangeData.x_min()) * (rangeData.m_max() - Math.max(0, workflowRule.value() + 1)) * (rangeData.a_max() - rangeData.a_min()) * (rangeData.s_max() - rangeData.s_min());
                                        System.out.println("m > A" + result);
                                        break;
                                    case "R":
                                        System.out.println("m > R");
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), rangeData.x_max(), workflowRule.value() + 1, rangeData.m_max(), rangeData.a_min(), rangeData.a_max(), rangeData.s_min(), rangeData.s_max(), workflowRule.output()));
                                        System.out.println("m > sub new m_max " + (workflowRule.value() + 1) + " m_min " + rangeData.m_max());
                                        break;
                                }
                                break;
                            } else {
                                if (rangeData.m_min() > workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - rangeData.x_min()) * Math.max(0, workflowRule.value() - 1 - rangeData.m_min()) * (rangeData.a_max() - rangeData.a_min()) * (rangeData.s_max() - rangeData.s_min());
                                        System.out.println("m < A" + result);
                                        break;
                                    case "R":
                                        System.out.println("m < R");
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), rangeData.x_max(), rangeData.m_min(), workflowRule.value() - 1, rangeData.a_min(), rangeData.a_max(), rangeData.s_min(), rangeData.s_max(), workflowRule.output()));
                                        System.out.println("m < sub new m_min " + rangeData.m_min() + " m_max " + (workflowRule.value() - 1));
                                        break;
                                }
                                break;
                            }
                        case 'a':
                            if(workflowRule.operator() == '>') {
                                if (rangeData.a_max() < workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - rangeData.x_min()) * (rangeData.m_max() - rangeData.m_min()) * (rangeData.a_max() - Math.max(0, workflowRule.value() + 1)) * (rangeData.s_max() - rangeData.s_min());
                                        break;
                                    case "R":
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), rangeData.x_max(), rangeData.m_min(), rangeData.m_max(), workflowRule.value() + 1, rangeData.a_max(), rangeData.s_min(), rangeData.s_max(), workflowRule.output()));
                                        break;
                                }
                                break;
                            } else {
                                if (rangeData.a_min() > workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - rangeData.x_min()) * (rangeData.m_max() - rangeData.m_min()) * Math.max(0, workflowRule.value() - 1 - rangeData.a_min()) * (rangeData.s_max() - rangeData.s_min());
                                        break;
                                    case "R":
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), rangeData.x_max(), rangeData.m_min(), rangeData.m_max(), rangeData.a_min(), workflowRule.value() - 1, rangeData.s_min(), rangeData.s_max(), workflowRule.output()));
                                        break;
                                }
                                break;
                            }
                        case 's':
                            if(workflowRule.operator() == '>') {
                                if (rangeData.s_max() < workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - rangeData.x_min()) * (rangeData.m_max() - rangeData.m_min()) * (rangeData.a_max() - rangeData.a_min()) * (rangeData.s_max() - Math.max(0, workflowRule.value() + 1));
                                        break;
                                    case "R":
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), rangeData.x_max(), rangeData.m_min(), rangeData.m_max(), rangeData.a_min(), rangeData.a_max(), workflowRule.value() + 1, rangeData.s_max(), workflowRule.output()));
                                        break;
                                }
                                break;
                            } else {
                                if (rangeData.s_min() > workflowRule.value())
                                    break;
                                switch (workflowRule.output()) {
                                    case "A":
                                        result += (rangeData.x_max() - rangeData.x_min()) * (rangeData.m_max() - rangeData.m_min()) * (rangeData.a_max() - rangeData.a_min()) * Math.max(0, workflowRule.value() - 1 - rangeData.s_min());
                                        break;
                                    case "R":
                                        break;
                                    default:
                                        rangeDataList.add(new RangeData(rangeData.x_min(), rangeData.x_max(), rangeData.m_min(), rangeData.m_max(), rangeData.a_min(), rangeData.a_max(), rangeData.s_min(), workflowRule.value() - 1, workflowRule.output()));
                                        break;
                                }
                                break;
                            }
                    }
                }

            }
        }
        System.out.println(result);
    }
}
