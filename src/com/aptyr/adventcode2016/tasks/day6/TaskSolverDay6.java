package com.aptyr.adventcode2016.tasks.day6;
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
import java.util.stream.Stream;

public class TaskSolverDay6 extends TaskSolver {

    private char[] column1 = new char[123];
    private char[] column2 = new char[123];
    private char[] column3 = new char[123];
    private char[] column4 = new char[123];
    private char[] column5 = new char[123];
    private char[] column6 = new char[123];
    private char[] column7 = new char[123];
    private char[] column8 = new char[123];

    public TaskSolverDay6() {
        init();
    }

    @Override
    public void solvePart1() {
        String result = String.format("%c%c%c%c%c%c%c%c",
                getMax(column1), getMax(column2), getMax(column3), getMax(column4), getMax(column5), getMax(column6), getMax(column7), getMax(column8));
        System.out.println("Solve part1 = " + result);
    }

    @Override
    public void solvePart2() {
        String result = String.format("%c%c%c%c%c%c%c%c",
                getMin(column1), getMin(column2), getMin(column3), getMin(column4), getMin(column5), getMin(column6), getMin(column7), getMin(column8));
        System.out.println("Solve part2 = " + result);
    }

    private char getMax(char[] data) {
        int max = 0;
        char charmax = 0;

        for (int i = 97; i < 123; i++) {
            if (max < data[i]) {
                max = data[i];
                charmax = (char) i;
            }
        }
        return charmax;
    }

    private char getMin(char[] data) {
        int min = data[97];
        char charmin = 97;

        for (int i = 97; i < 123; i++) {
            if (min > data[i]) {
                min = data[i];
                charmin = (char) i;
            }
        }
        return charmin;
    }

    @Override
    protected void init() {
        try (Stream<String> stream = Files.lines(Paths.get("/Users/artur/Development/IdeaProjects/AdevntCode2016/src/com/aptyr/adventcode2016/tasks/day6/input"))) {
            stream.forEach(line -> {
                column1[line.charAt(0)]++;
                column2[line.charAt(1)]++;
                column3[line.charAt(2)]++;
                column4[line.charAt(3)]++;
                column5[line.charAt(4)]++;
                column6[line.charAt(5)]++;
                column7[line.charAt(6)]++;
                column8[line.charAt(7)]++;
            });
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
