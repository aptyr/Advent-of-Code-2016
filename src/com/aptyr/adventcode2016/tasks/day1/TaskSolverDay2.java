package com.aptyr.adventcode2016.tasks.day1;

import com.aptyr.adventcode2016.tasks.TaskSolver;

/**
 * Created by artur on 02/12/2016.
 */
public class TaskSolverDay2 extends TaskSolver {

    String data[] = new String[5];

    int keypad[][] = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    char keypad2[][] = {
            {0, 0, '1', 0, 0},
            {0, '2', '3', '4', 0},
            {'5', '6', '7', '8', '9'},
            {0, 'A', 'B', 'C', 0},
            {0, 0, 'D', 0, 0}};

    int x = 1;
    int y = 1;

    int x2 = 0;
    int y2 = 2;

    String code = "";

    public TaskSolverDay2() {
        data[0] = "ULUULLUULUUUUDURUUULLDLDDRDRDULULRULLRLULRUDRRLDDLRULLLDRDRRDDLLLLDURUURDUDUUURDRLRLLURUDRDULURRUDLRDRRLLRDULLDURURLLLULLRLUDDLRRURRLDULRDDULDLRLURDUDRLLRUDDRLRDLLDDUURLRUDDURRLRURLDDDURRDLLDUUDLLLDUDURLUDURLRDLURURRLRLDDRURRLRRDURLURURRRULRRDLDDDDLLRDLDDDRDDRLUUDDLDUURUULDLUULUURRDRLDDDULRRRRULULLRLLDDUDRLRRLLLLLDRULURLLDULULLUULDDRURUDULDRDRRURLDRDDLULRDDRDLRLUDLLLDUDULUUUUDRDRURDDULLRDRLRRURLRDLRRRRUDDLRDDUDLDLUUDLDDRRRDRLLRLUURUDRUUULUDDDLDUULULLRUDULULLLDRLDDLLUUDRDDDDRUDURDRRUUDDLRRRRURLURLD";
        data[1] = "LDLUDDLLDDRLLDLDRDDDDDUURUDDDUURLRLRLDULLLDLUDDDULLDUDLRUUDDLUULLDRLDDUDLUDDLURRRLDUURDDRULLURLLRLLUUDRLDDDLDLDRDUDLRDURULDLDRRDRLDLUURRRRLUDDULDULUUUDULDDRLLDDRRUULURRUURRLDUUUDDDDRUURUDRRRDDDDLRLURRRRUUDDDULRRURRDLULRURDDRDRLUDLURDDRDURRUURDUDUDRRDDURRRDURDLUUUURRUDULLDDRLLLURLDUDRRLDDLULUDUDDDDUDLUUULUURUDRURUUDUUURRLDUUDRDRURLLDLLLLLRLLUDURDRRLULRRDDDRLDRDDURLRDULULLDDURURLRRDRULDULUUUURLDURUDUDUDDLUDRRDURULRDULLLDRRDLDLUDURDULULLDDURDDUDRUUUDUDRLDUURDUUUDUURURUDRULRURLDLRDDURDLUU";
        data[2] = "DDLDRLLDRRDRRLLUUURDDULRDUDRDRUDULURLLDDLRRRUDRDLDLURRRULUDRDLULLULLDUUDRLRUDDLRRURRUULRLDLLLDLRLLLURLLLURLLRDDLULLDUURLURDLLDLDUDLDRUUUDDLLDRRRRRUDRURUURRRDRUURDRDDRLDUUULUDUDRUDLLLLDRDRURRRDUUURLDLRLRDDDRLUDULDRLLULRDLDURDLDURUUDDULLULRDDRLRUURLLLURDRUURUUDUUULRDUDDRDURRRDUUDRRRUDRDLRURDLLDDDURLLRRDDDDLDULULDRLDRULDDLRRRLUDLLLLUDURRRUURUUULRRLDUURDLURRLRLLRDLRDDRDDLRDLULRUUUDDDUDRRURDDURURDDUDLURUUURUUUUDURDDLDRDULDRLDRLLRLRRRLDRLLDDRDLDLUDDLUDLULDLLDRDLLRDULDUDDULRRRUUDULDULRRURLRDRUDLDUDLURRRDDULRDDRULDLUUDDLRDUURDRDR";
        data[3] = "URDURRRRUURULDLRUUDURDLLDUULULDURUDULLUDULRUDUUURLDRRULRRLLRDUURDDDLRDDRULUUURRRRDLLDLRLRULDLRRRRUDULDDURDLDUUULDURLLUDLURULLURRRDRLLDRRDULUDDURLDULLDURLUDUULRRLLURURLDLLLURDUDRLDDDRDULLUDDRLDDRRRLDULLLLDUURULUDDDURUULUUUDURUDURDURULLLDRULULDRRLDRLDLRLRUDUDURRLURLRUUDRRDULULDLLDRDRRRDUDUURLDULLLURRDLUDDLDDRDDUDLDDRRRUDRULLURDDULRLDUDDDRULURLLUDLLRLRRDRDRRURUUUURDLUURRDULLRDLDLRDDRDRLLLRRDDLDDDDLUDLRLULRRDDRDLDLUUUDLDURURLULLLDDDULURLRRURLDDRDDLD";
        data[4] = "UDUULLRLUDLLUULRURRUUDDLLLDUURRURURDDRDLRRURLLRURLDDDRRDDUDRLLDRRUDRDRDDRURLULDDLDLRRUDDULLRLDDLRURLUURUURURDLDUDRLUUURRRLUURUDUDUUDDLDULUULRLDLLURLDRUDRLLRULURDLDDLLULLDRRUUDDLRRRUDDLRDRRRULDRDDRRULLLUDRUULURDUDRDLRRLDLRLRLDDULRRLULUUDDULDUDDULRRURLRDRDURUDDDLLRLDRDRULDDLLRLLRDUDDDDDDRLRLLDURUULDUUUDRURRLLRLDDDDRDRDUURRURDRDLLLUDDRDRRRDLUDLUUDRULURDLLLLLRDUDLLRULUULRLULRURULRLRRULUURLUDLDLLUURDLLULLLDDLRUDDRULRDLULRUURLDRULRRLULRLRULRDLURLLRURULRDRDLRRLRRDRUUURURULLLDDUURLDUDLLRRLRLRULLDUUUULDDUUU";
    }

    @Override
    public void solvePart1() {
        code = "";
        for (String s : data) {
            for (char c : s.toCharArray()) {
                if (c == 'U') {
                    y--;
                } else if (c == 'D') {
                    y++;
                } else if (c == 'L') {
                    x--;
                } else if (c == 'R') {
                    x++;
                }

                if (x < 0) {
                    x = 0;
                }
                if (x > 2) {
                    x = 2;
                }
                if (y < 0) {
                    y = 0;
                }
                if (y > 2) {
                    y = 2;
                }
            }

            code += keypad[y][x];
        }
        System.out.println("Solve part1 = " + code);
    }

    @Override
    public void solvePart2() {
        code = "";
        for (String s : data) {
            for (char c : s.toCharArray()) {
                if (c == 'U') {
                    if (y2 - 1 >= 0 && keypad2[y2 - 1][x2] != 0)
                        y2--;
                } else if (c == 'D') {
                    if (y2 + 1 < 5 && keypad2[y2 + 1][x2] != 0)
                        y2++;
                } else if (c == 'L') {
                    if (x2 - 1 >= 0 && keypad2[y2][x2 - 1] != 0)
                        x2--;
                } else if (c == 'R') {
                    if (x2 + 1 < 5 && keypad2[y2][x2 + 1] != 0)
                        x2++;
                }
            }
            code += keypad2[y2][x2];
        }
        System.out.println("Solve part2 = " + code);

    }

    @Override
    protected void solve() {

    }
}