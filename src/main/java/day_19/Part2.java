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
            RangeData rangeData = rangeDataList.get(0);
            for (WorkflowRule workflowRule: entry.getValue().rules()) {
                switch (workflowRule.key()) {
                    case 'x':
                        if(workflowRule.operator() == '>')
                            switch (workflowRule.output()) {
                                case "A":
                                    result += Math.max(0, Math.max(0, rangeData.x_max() - workflowRule.value() + 1) - rangeData.m_max() + rangeData.a_max() + rangeData.s_max();
                                    break;
                                case "R":
                                    rangeData1.setX_max(workflowRule.value() - 1);
                                    break;
                                default:
                                    rangeData1.setX_min(workflowRule.value() + 1);
                                    rangeData1.setX_max(workflowRule.value() - 1);
                                    break;
                            }
                            if(workflowRule.output().equals("A") || workflowRule.equals("R"))

                            else
                                rangeData.add(new RangeData(workflowRule.value() + 1, rangeData1.x_max(), rangeData1.m_min(), rangeData1.m_max(), rangeData1.a_min(), rangeData1.a_max(), rangeData1.s_min(), rangeData1.s_max(), workflowRule.output()));
                        else
                            rangeData.add(new RangeData(rangeData1.x_min(), workflowRule.value() - 1, rangeData1.m_min(), rangeData1.m_max(), rangeData1.a_min(), rangeData1.a_max(), rangeData1.s_min(), rangeData1.s_max(), workflowRule.output()));
                        break;
                }
            }
        }
    }
}
