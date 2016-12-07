package com.aptyr.adventcode2016.tasks.day7;
/*
 * Copyright (C) 2016 Artur Matusiak (github.com/aptyr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.aptyr.adventcode2016.tasks.TaskSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class TaskSolverDay7 extends TaskSolver {

    private List<Ip> data = new ArrayList<>();
    private Pattern pattern = Pattern.compile("([a-z]+)?(\\[[a-z]+\\])?([a-z]+)?(\\[[a-z]+\\])?([a-z]+)?([a-z]+)?(\\[[a-z]+\\])?([a-z]+)?(\\[[a-z]+\\])?([a-z]+)?(\\[[a-z]+\\])?([a-z]+)?");

    public TaskSolverDay7() {
        init();
    }

    @Override
    public void solvePart1() {
        long count = data.stream().filter(ip -> {
            return ip.supportsTLS();
        }).count();

        System.out.println("Solve part1 = " + count);
    }

    @Override
    public void solvePart2() {
        long count = data.stream().filter(ip -> {
            return ip.supportsSSL();
        }).count();

        System.out.println("Solve part2 = " + count);
    }

    @Override
    protected void init() {
        try (Stream<String> stream = Files.lines(Paths.get("/Users/artur/Development/IdeaProjects/AdevntCode2016/src/com/aptyr/adventcode2016/tasks/day7/input"))) {
            stream.forEach(line -> {
                data.add(new Ip(line));
            });
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Ip {
        String address;
        List<String> noBrackets = new ArrayList<>();
        List<String> withBrackets = new ArrayList<>();

        boolean anyNoBracketHas;
        boolean anyWithBracketHas;
        List<String> bracketABAs = new ArrayList<>();
        List<String> noBracketABAs = new ArrayList<>();

        public Ip(String address) {
            this.address = address;
            Matcher matcher = pattern.matcher(address);
            if (matcher.find()) {
                for (int i = 1; i < matcher.groupCount(); i++) {
                    if (matcher.group(i) != null) {
                        if (matcher.group(i).contains("[")) {
                            withBrackets.add(matcher.group(i));
                        } else {
                            noBrackets.add(matcher.group(i));
                        }
                    }
                }
            }
        }

        boolean supportsTLS() {
            anyNoBracketHas = false;
            anyWithBracketHas = false;

            noBrackets.stream().forEach(address -> {
                anyNoBracketHas |= hasABBA(address);
            });

            withBrackets.stream().forEach(address -> {
                anyWithBracketHas |= hasABBA(address);
            });

            return anyNoBracketHas & !anyWithBracketHas;
        }

        boolean supportsSSL() {
            noBrackets.stream().forEach(address -> {
                noBracketABAs.addAll(getABA(address));
            });

            withBrackets.stream().forEach(address -> {
                bracketABAs.addAll(getABA(address));
            });

            for (String bracketABA : bracketABAs) {
                for (String noBracketABA : noBracketABAs) {
                    boolean check = compareABA(bracketABA, noBracketABA);
                    if (check) {
                        return true;
                    }
                }
            }


            return false;
        }

        private boolean hasABBA(String data) {
            int i = 0;
            while (i < data.length() - 3) {
                char a = data.charAt(i);
                char b = data.charAt(i + 1);
                char c = data.charAt(i + 2);
                char d = data.charAt(i + 3);
                if (a == d && b == c && a != b) {
                    return true;
                }
                i++;
            }
            return false;
        }

        private List<String> getABA(String data) {
            int i = 0;
            List<String> result = new ArrayList<>();
            while (i < data.length() - 2) {
                char a = data.charAt(i);
                char b = data.charAt(i + 1);
                char c = data.charAt(i + 2);
                if (a == c && a != b) {
                    result.add(String.format("%c%c%c", a, b, c));
                }
                i++;
            }
            return result;
        }

        private boolean compareABA(String aba, String bab) {
            return aba.charAt(0) == bab.charAt(1) && aba.charAt(1) == bab.charAt(2);
        }
    }
}
