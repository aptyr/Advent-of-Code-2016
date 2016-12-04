package com.aptyr.adventcode2016.tasks.day3;
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

public class TaskSolverDay3 extends TaskSolver {

    private List<Triangle> triangles = new ArrayList<>();
    private List<Triangle> triangles2 = new ArrayList<>();

    public TaskSolverDay3() {
        init();
    }

    @Override
    public void solvePart1() {
        System.out.println("Solve part1 = " + triangles.stream().filter(triangle -> {
            return triangle.isValid();
        }).count());
    }

    @Override
    public void solvePart2() {
        System.out.println("Solve part2 = " + triangles2.stream().filter(triangle -> {
            return triangle.isValid();
        }).count());
    }

    @Override
    protected void init() {
        Pattern pattern = Pattern.compile("[ ]+([0-9]+)[ ]+([0-9]+)[ ]+([0-9]+)");

        try (Stream<String> stream = Files.lines(Paths.get("/Users/artur/Development/IdeaProjects/AdevntCode2016/src/com/aptyr/adventcode2016/tasks/day3/input"))) {
            stream.forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int a = Integer.valueOf(matcher.group(1));
                    int b = Integer.valueOf(matcher.group(2));
                    int c = Integer.valueOf(matcher.group(3));
                    triangles.add(new Triangle(a, b, c));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < triangles.size(); i+=3) {
            Triangle t1 = triangles.get(i);
            Triangle t2 = triangles.get(i+1);
            Triangle t3 = triangles.get(i+2);

            triangles2.add(new Triangle(t1.a, t2.a, t3.a));
            triangles2.add(new Triangle(t1.b, t2.b, t3.b));
            triangles2.add(new Triangle(t1.c, t2.c, t3.c));
        }
    }

    private class Triangle {
        private int a;
        private int b;
        private int c;

        Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        boolean isValid() {
            return a + b > c && a + c > b && b + c > a;
        }
    }
}
