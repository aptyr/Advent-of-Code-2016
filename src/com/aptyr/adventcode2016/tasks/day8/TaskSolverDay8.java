package com.aptyr.adventcode2016.tasks.day8;
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

public class TaskSolverDay8 extends TaskSolver {
    private Pattern pattern = Pattern.compile("\\b(?:(rect)\\s([0-9]+)x([0-9]+))|(?:(rotate)\\s+(row|column)\\s+[x|y]=([0-9]+)\\s+by\\s+([0-9]+))\\b");
    private List<DisplayAction> actions = new ArrayList<>();

    private char[][] display = new char[6][50];

    public TaskSolverDay8() {
        init();
    }

    @Override
    public void solvePart1() {
        actions.stream().forEach(displayAction -> {
            if (displayAction.action.equals("rect")) {
                for (int y = 0; y < displayAction.b; y++) {
                    for (int x = 0; x < displayAction.a; x++) {
                        display[y][x] = '*';
                    }
                }
            } else {
                if (displayAction.direction.equals("row")) {
                    shiftRight(displayAction.a, displayAction.b);
                } else {
                    shiftDown(displayAction.a, displayAction.b);
                }
            }
        });

        int count = 0;
        for (int y = 0; y < display.length; y++) {
            for (int x = 0; x < display[y].length; x++) {
                if (display[y][x] == '*')
                    count++;
            }
        }

        System.out.println("Solve part1 = " + count);
    }

    @Override
    public void solvePart2() {
        System.out.println("Solve part2 = ");
        printDisplay();
    }

    @Override
    protected void init() {
        try (Stream<String> stream = Files.lines(Paths.get("/Users/artur/Development/IdeaProjects/AdevntCode2016/src/com/aptyr/adventcode2016/tasks/day8/input"))) {
            stream.forEach(line -> {
                actions.add(new DisplayAction(line));
            });
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shiftRight(int a, int b) {
        for (int reps = 0; reps < b; reps++) {
            char last = display[a][display[a].length - 1];
            for (int index = display[a].length - 2; index >= 0; index--) {
                display[a][index + 1] = display[a][index];
            }
            display[a][0] = last;
        }
    }

    private void shiftDown(int a, int b) {
        for (int reps = 0; reps < b; reps++) {
            char last = display[display.length - 1][a];
            for (int index = display.length - 2; index >= 0; index--) {
                display[index + 1][a] = display[index][a];
            }
            display[0][a] = last;
        }
    }

    private void printDisplay() {
        for (int y = 0; y < display.length; y++) {
            for (int x = 0; x < display[y].length; x++) {
                if (display[y][x] == 0) {
                    display[y][x] = ' ';
                }
                System.out.print(display[y][x]);

            }
            System.out.println();
        }
        System.out.println();
    }

    private class DisplayAction {

        String action;
        String direction;
        int a;
        int b;
        List<String> params = new ArrayList<>();

        DisplayAction(String data) {
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {

                    if (matcher.group(i) != null) {
                        params.add(matcher.group(i));
                    }
                }
            }

            action = params.get(0);
            if (params.size() == 3) {
                a = Integer.valueOf(params.get(1));
                b = Integer.valueOf(params.get(2));
            } else {
                direction = params.get(1);
                a = Integer.valueOf(params.get(2));
                b = Integer.valueOf(params.get(3));
            }

        }
    }
}
