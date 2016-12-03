package com.aptyr.adventcode2016.tasks.day3;

import com.aptyr.adventcode2016.tasks.TaskSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by artur on 03/12/2016.
 */
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
