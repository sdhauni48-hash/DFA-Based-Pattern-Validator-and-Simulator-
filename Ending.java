package dfa.ending;

import dfa.exceptions.InvalidCharacterException;
import dfa.model.Result;

public class Ending {

    public Result DFA(String a, String pattern, String s) throws InvalidCharacterException {

        StringBuilder table = new StringBuilder();

        String[] arr = a.split(" ");
        int l = pattern.length();
        int states = l + 1;

        int[][] t = new int[states][arr.length];

        for (int i = 0; i < states; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == l) t[i][j] = 0;
                else if (arr[j].charAt(0) == pattern.charAt(i)) t[i][j] = i + 1;
                else t[i][j] = 0;
            }
        }

        table.append("State\t");
        for (String c : arr) table.append(c + "\t");
        table.append("\n");

        for (int i = 0; i < states; i++) {
            if (i == 0) table.append("->q0\t");
            else if (i == l) table.append("*q" + i + "\t");
            else table.append("q" + i + "\t");

            for (int j = 0; j < arr.length; j++)
                table.append("q" + t[i][j] + "\t");

            table.append("\n");
        }

        int p = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j;

            for (j = 0; j < arr.length; j++)
                if (arr[j].charAt(0) == c) break;

            if (j == arr.length)
                throw new InvalidCharacterException("Invalid character: " + c);

            p = t[p][j];
        }

        return new Result(table.toString(),
                p == pattern.length(),
                t, arr, states, pattern.length());
    }
}
