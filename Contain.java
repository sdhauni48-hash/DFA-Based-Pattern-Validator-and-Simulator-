package dfa.containing;

public class Contain {
	public void DFA(String a, String pattern, String s) {

        String[] arr;
        arr = a.split(" ");

        int l = pattern.length();
        int states = l + 2;
        int dead = states - 1;

        System.out.println("Number of states: " + states);

        int [][] tran_table = new int [states][arr.length];

        for(int i = 0 ; i < states ; i++){
            for(int j = 0 ; j < arr.length ; j++){

                if(i == dead) {
                    tran_table[i][j] = dead;
                }
                else if(i == pattern.length()) {
                    tran_table[i][j] = i; 
                }
                else if(arr[j].charAt(0) == pattern.charAt(i)) {
                    tran_table[i][j] = i + 1;
                } 
                else {
                    tran_table[i][j] = dead;
                }
            }
        }

        System.out.println("\nTransition Table:");

        System.out.print("State\t");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

        for(int i = 0; i < states; i++) {

            if(i == 0)
                System.out.print("->q" + i + "\t");     
            else if(i == pattern.length())
                System.out.print("* q" + i + "\t");    
            else if(i == dead)
                System.out.print("  qd\t");    
            else
                System.out.print("  q" + i + "\t");    

            for(int j = 0; j < arr.length; j++) {
                if(tran_table[i][j] == dead)
                    System.out.print("qd\t");
                else
                    System.out.print("q" + tran_table[i][j] + "\t");
            }

            System.out.println();
        }

        int p = 0;

        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);

            for(int j=0;j<arr.length;j++) {
                if(arr[j].charAt(0) == c) {
                    p = tran_table[p][j];
                    break;
                }
            }
        }

        System.out.println("\nFinal State: " + (p == dead ? "qd" : "q"+p));

        if(p == pattern.length()) {
            System.out.println("String Accepted");
        } else {
            System.out.println("String Rejected");
        }
    }
}