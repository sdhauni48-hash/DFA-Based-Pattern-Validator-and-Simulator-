package dfa.model;

public class Result {
    public String table;
    public boolean accepted;

    public int[][] t;
    public String[] alph;
    public int states;
    public int finalState;

    public Result(String table, boolean accepted,
                  int[][] t, String[] alph,
                  int states, int finalState) {

        this.table = table;
        this.accepted = accepted;
        this.t = t;
        this.alph = alph;
        this.states = states;
        this.finalState = finalState;
    }
}
