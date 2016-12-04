package com.aptyr.adventcode2016.tasks.day4;
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

public class TaskSolverDay4 extends TaskSolver {

    private List<Room> rooms = new ArrayList<>();
    private int sum = 0;

    public TaskSolverDay4() {
        init();
    }

    @Override
    public void solvePart1() {

        rooms.stream().filter(room -> {
            return room.isReal();
        }).forEach(room -> {
            sum += room.secureID;
        });

        System.out.println("Solve part1 = " + sum);


    }

    @Override
    public void solvePart2() {
        Room result = rooms.stream().filter(room -> {
            return room.isReal();
        }).filter(room -> {
            return room.decryptedName().contains("north");
        }).findFirst().get();

        System.out.println("Solve part2 = " + result.secureID);
    }

    @Override
    protected void init() {
        Pattern pattern = Pattern.compile("([a-z-]+)-([0-9]+)\\[([a-z]+)\\]");

        try (Stream<String> stream = Files.lines(Paths.get("/Users/artur/Development/IdeaProjects/AdevntCode2016/src/com/aptyr/adventcode2016/tasks/day4/input"))) {
            stream.forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String name = matcher.group(1);
                    int id = Integer.valueOf(matcher.group(2));
                    String csum = matcher.group(3);
                    rooms.add(new Room(name, csum, id));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class Room {
        private String encryptedName;
        private int secureID;
        private String checksum;
        private String encryptedNameWithoutDash;

        Room(String encryptedName, String checksum, int secureID) {
            this.encryptedName = encryptedName;
            encryptedNameWithoutDash = encryptedName.replaceAll("-", " ");
            this.checksum = checksum;
            this.secureID = secureID;
        }

        boolean isReal() {

            return checksum.equals(getMax());
        }

        String getMax() {
            int[] charcnt = new int[123];

            for (int i = encryptedName.length() - 1; i >= 0; i--) {
                char ch = encryptedName.charAt(i);
                ++charcnt[ch];
            }

            String a = "";
            List<Integer> d = new ArrayList<>();

            while (d.size() < 5) {
                int max = 0;
                int maxchar = ' ';

                for (int i = 97; i < 123; i++) {
                    if (!d.contains(i) && max < charcnt[i]) {
                        max = charcnt[i];
                        maxchar = i;
                    }
                }
                d.add(maxchar);
            }
            for (Integer integer : d) {
                a += (char) integer.intValue();

            }
            return a;
        }

        String decryptedName() {
            String result = "";
            int shift = secureID % 26; //a-z

            for (int i = 0; i < encryptedNameWithoutDash.length(); ++i) {
                char c = encryptedNameWithoutDash.charAt(i);

                if (c >= 97 && c <= 122) {
                    if (c + shift <= 122) {
                        c += shift;
                    } else {
                        c = (char) (97 + (shift - (123 - c)));
                    }
                }

                result += c;

            }
            return result;
        }
    }
}
