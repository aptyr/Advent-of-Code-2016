package com.aptyr.adventcode2016;
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

import com.aptyr.adventcode2016.tasks.Task;
import com.aptyr.adventcode2016.tasks.day1.TaskSolverDay1;
import com.aptyr.adventcode2016.tasks.day2.TaskSolverDay2;
import com.aptyr.adventcode2016.tasks.day3.TaskSolverDay3;
import com.aptyr.adventcode2016.tasks.day4.TaskSolverDay4;
import com.aptyr.adventcode2016.tasks.day5.TaskSolverDay5;
import com.aptyr.adventcode2016.tasks.day6.TaskSolverDay6;
import com.aptyr.adventcode2016.tasks.day7.TaskSolverDay7;
import com.aptyr.adventcode2016.tasks.day8.TaskSolverDay8;


public class Main {

    private Task day1 = new TaskSolverDay1();
    private Task day2 = new TaskSolverDay2();
    private Task day3 = new TaskSolverDay3();
    private Task day4 = new TaskSolverDay4();
    private Task day5 = new TaskSolverDay5();
    private Task day6 = new TaskSolverDay6();
    private Task day7 = new TaskSolverDay7();
    private Task day8 = new TaskSolverDay8();

    public static void main(String[] args) {
        Main main = new Main();

//        main.day1.solvePart1();
//        main.day1.solvePart2();
//        main.day2.solvePart1();
//        main.day2.solvePart2();
//        main.day3.solvePart1();
//        main.day3.solvePart2();
//        main.day4.solvePart1();
//        main.day4.solvePart2();
//        main.day5.solvePart1();
//        main.day5.solvePart2();
//        main.day6.solvePart1();
//        main.day6.solvePart2();
//        main.day7.solvePart1();
//        main.day7.solvePart2();
        main.day8.solvePart1();
        main.day8.solvePart2();

    }
}
