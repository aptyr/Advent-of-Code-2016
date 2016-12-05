package com.aptyr.adventcode2016.tasks.day5;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TaskSolverDay5 extends TaskSolver {

    private String input = "ffykfhsq";
    private int index = 0;
    private String password = "";
    private char[] password2 = new char[8];

    @Override
    public void solvePart1() {

        while (password.length() < 8) {
            String hash = md5(input + index++);
            if (hash.substring(0, 5).equals("00000")) {
                password += hash.charAt(5);
            }
        }

        System.out.println("Solve part1 = " + password);

    }

    @Override
    public void solvePart2() {
        int size = 0;
        while (size < 8) {
            String hash = md5(input + index++);
            if (hash.substring(0, 5).equals("00000") && hash.charAt(5) >= 48 && hash.charAt(5) <= 55) {
                int position = Integer.valueOf(hash.charAt(5) + "");
                if(password2[position] == 0) {
                    password2[position] = hash.charAt(6);
                    size++;
                }
            }
        }
        System.out.println("Solve part2 = " + String.valueOf(password2));
    }

    @Override
    protected void init() {

    }

    private String md5(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1, m.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
