package com.aptyr.adventcode2016.tasks.day1;
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskSolverDay1 extends TaskSolver {

    private Position position = new Position();

    private enum Direction {
        N, E, S, W
    }

    private enum Turn {
        R("R"), L("L");

        private final String val;

        Turn(String val) {
            this.val = val;
        }

        public String getValue() {
            return val;
        }
    }

    public TaskSolverDay1() {
        input = "R4, R5, L5, L5, L3, " +
                "R2, R1, R1, L5, R5, " +
                "R2, L1, L3, L4, R3, " +
                "L1, L1, R2, R3, R3, " +
                "R1, L3, L5, R3, R1, " +
                "L1, R1, R2, L1, L4, " +
                "L5, R4, R2, L192, R5, " +
                "L2, R53, R1, L5, R73, " +
                "R5, L5, R186, L3, L2, " +
                "R1, R3, L3, L3, R1, L4, " +
                "L2, R3, L5, R4, R3, R1, " +
                "L1, R5, R2, R1, R1, R1, " +
                "R3, R2, L1, R5, R1, L5, " +
                "R2, L2, L4, R3, L1, R4, " +
                "L5, R4, R3, L5, L3, R4, " +
                "R2, L5, L5, R2, R3, R5, " +
                "R4, R2, R1, L1, L5, L2, " +
                "L3, L4, L5, L4, L5, L1, " +
                "R3, R4, R5, R3, L5, L4, " +
                "L3, L1, L4, R2, R5, R5, " +
                "R4, L2, L4, R3, R1, L2, " +
                "R5, L5, R1, R1, L1, L5, " +
                "L5, L2, L1, R5, R2, L4, " +
                "L1, R4, R3, L3, R1, R5, " +
                "L1, L4, R2, L3, R5, R3, " +
                "R1, L3";

        solve();
    }

    @Override
    protected void solve() {
        String[] moves = input.split(", ");
        Pattern pattern = Pattern.compile("([A-Z])([0-9]{1,})");

        for (String move : moves) {
            Matcher matcher = pattern.matcher(move);

            int distance = 0;
            Turn turn = null;
            if (matcher.find()) {
                distance = Integer.parseInt(matcher.group(2));
                turn = Turn.valueOf(matcher.group(1));
            }

            position.move(distance, turn);

        }
    }

    @Override
    public void solvePart1() {
        System.out.println("Solve part1 = " + (Math.abs(position.x) + Math.abs(position.y)));
    }

    @Override
    public void solvePart2() {
        System.out.println("Solve part2 = " + (Math.abs(position.firstRepeatPosition.x) + Math.abs(position.firstRepeatPosition.y)));
    }

    private class Position {
        final int step = 1;

        int x = 0;
        int y = 0;
        List<Position> steps = new ArrayList<>();
        Direction direction = Direction.N;
        Position firstRepeatPosition;

        Position() {
        }

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void move(int distance, Turn turn) {
            if (turn == Turn.R) {
                if (direction == Direction.N) {
                    direction = Direction.E;
                    makeStepsRight(distance);
                } else if (direction == Direction.S) {
                    direction = Direction.W;
                    makeStepsLeft(distance);
                } else if (direction == Direction.W) {
                    direction = Direction.N;
                    makeStepsTop(distance);
                } else if (direction == Direction.E) {
                    direction = Direction.S;
                    makeStepsBottom(distance);
                }
            } else {
                if (direction == Direction.N) {
                    direction = Direction.W;
                    makeStepsLeft(distance);
                } else if (direction == Direction.S) {
                    direction = Direction.E;
                    makeStepsRight(distance);
                } else if (direction == Direction.W) {
                    direction = Direction.S;
                    makeStepsBottom(distance);
                } else if (direction == Direction.E) {
                    direction = Direction.N;
                    makeStepsTop(distance);
                }
            }
        }

        void makeStepsRight(int count) {
            for (int i = 0; i < count; i++) {
                checkStep(new Position(x + (i + 1), y));
            }
            x += count;
        }

        void makeStepsLeft(int count) {
            for (int i = 0; i < count; i++) {
                checkStep(new Position(x - (i + 1), y));
            }
            x -= count;
        }

        void makeStepsTop(int count) {
            for (int i = 0; i < count; i++) {
                checkStep(new Position(x, y + (i + 1)));
            }
            y += count;
        }

        void makeStepsBottom(int count) {
            for (int i = 0; i < count; i++) {
                checkStep(new Position(x, y - (i + 1)));
            }
            y -= count;
        }

        void checkStep(Position position) {
            if (firstRepeatPosition == null && steps.contains(position)) {
                firstRepeatPosition = position;
            } else {
                steps.add(position);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (x != position.x) return false;
            return y == position.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
