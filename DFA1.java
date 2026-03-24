import java.util.*;
import dfa.starting.Starting;
import dfa.ending.Ending;
import dfa.containing.Contain;

public class DFA1 { 
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter alphabet:");
        String a = sc.nextLine();   

        int rule;
        System.out.println("Enter the choice:");
        System.out.println("1 - starting with ");
        System.out.println("2 - ending with ");
        System.out.println("3 - containing ");
        rule = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter pattern:");
        String pattern = sc.nextLine();

        System.out.println("Enter the string:");
        String s = sc.nextLine();

        if(rule == 1) {
            Starting s_d = new Starting();
            s_d.DFA(a, pattern, s);
        }
        else if(rule == 2) {
            Ending e_d = new Ending();
            e_d.DFA(a, pattern, s);
        }
        else if(rule == 3) {
            Contain c_d = new Contain();
            c_d.DFA(a, pattern, s);
        }
        else {
            System.out.println("Invalid choice");
        }

        sc.close();
    }
}