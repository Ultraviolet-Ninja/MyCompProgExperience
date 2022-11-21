package competition.kattis.the_class.notes.week3;

import java.util.*;

public class Cryptarithmetic {

    /**
     * Converts a string to an integer given a character -> value assignment map
     * @param str - String to convert
     * @param assignments - character -> value assignment map
     * @return - the string as an integer
     */
    public static int stringToInt(String str, Map<Character, Integer> assignments) {
        int value = 0;
        int magnitude = 1;
        for(int i = str.length()-1; i >= 0; i--) {
            value += assignments.get(str.charAt(i)) * magnitude;
            magnitude *= 10;
        }
        return value;
    }

    /**
     * Checks a character -> value assignment map to see if it gives the correct
     *    answer to the problem
     * @param operand1 - first operand
     * @param operation - operation (must be either "+" or "*")
     * @param operand2 - second operand
     * @param answer - answer
     * @param assignments - character -> value assignment map
     * @return - true if arithmetic is correct - false if not
     */
    public static boolean checkAnswer(String operand1, String operation, String operand2, String answer, Map<Character, Integer> assignments) {
        int op1Int = stringToInt(operand1, assignments);
        int op2Int = stringToInt(operand2, assignments);
        int ansInt = stringToInt(answer, assignments);

        if(operation.equals("+")) {
            return (op1Int + op2Int == ansInt);
        } else if(operation.equals("*")) {
            return (op1Int * op2Int == ansInt);
        }
        return false;
    }

    /**
     * Check if there are any characters that do not have an assigned numeric value
     * @param assignments - character -> value map
     * @return - a character from the map that does not have an assignment
     *           or null if all characters have an assignment
     */
    public static Character checkAssignment(Map<Character, Integer> assignments) {
        for (Map.Entry<Character, Integer> entry : assignments.entrySet()) {
            if(entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Initialize the open list for each unique character in the problem
     * @param operand1 - first operand
     * @param operand2 - second operand
     * @param answer - answer
     * @return - the initialized open list with no values assigned to any character
     */
    public static Deque<Map<Character, Integer>> initOpenList(String operand1, String operand2, String answer) {

        // Create a new list
        Deque<Map<Character, Integer>> openList = new ArrayDeque<>();

        // Find unique characters from all operands and answer
        HashMap<Character, Integer> chars = new HashMap<>();
        for(int i = 0; i < operand1.length(); i++) {
            chars.put(operand1.charAt(i), null);
        }
        for(int i = 0; i < operand2.length(); i++) {
            chars.put(operand2.charAt(i), null);
        }
        for(int i = 0; i < answer.length(); i++) {
            chars.put(answer.charAt(i), null);
        }

        // Add the characters to the open list
        //    NOTE: No numerical assignments are given yet, that will come during search
        openList.addFirst(chars);
        return openList;
    }

    /**
     * Given a character that has not been assigned a potential set of values (0-9)
     *    build items for the open list with each possible value assigned
     *    Skip a value if another member in the parent 'assignment' already has that value
     * @param c - the character to give values
     * @param assignment - the parent node assignment
     * @param openList = The current open list
     */
    public static void appendAssignments(Character c, Map<Character, Integer> assignment, Deque<Map<Character, Integer>> openList) {
        Collection<Integer> values = assignment.values();
        for(int i = 0; i < 10; i++) {
            if(!values.contains(i)) {
                HashMap<Character, Integer> newAssignment = new HashMap<>(assignment);
                newAssignment.put(c, i);
                openList.addFirst(newAssignment);
            }
        }
    }

    /**
     * Solve a cryparithmetic puzzle using search
     * @param operand1 - first operand
     * @param operation - operation (must be either "+" or "*")
     * @param operand2 - second operand
     * @param answer - answer
     * @return - the assignments to all characters in operands and answer
     *           or null if a solution cannot be found
     */
    public static Map<Character, Integer> solve(String operand1, String operation, String operand2, String answer) {

        // Initialize the open list
        Deque<Map<Character, Integer>> openList = initOpenList(operand1, operand2, answer);

        // For each entry search for a solution
        while(openList.size() > 0) {

            // Grab the first entry in the open list
            Map<Character, Integer> currentAssignment = openList.removeFirst();

            // Check of all characters in the item have a numerical value assignemnt
            Character unassigned = checkAssignment(currentAssignment);

            // At least one character does not have an assignment - add more elements to the open list
            if(unassigned != null) {
                appendAssignments(unassigned, currentAssignment, openList);
            } else {

                // All characters have an assignment, check arithmetic and return if correct
                if(checkAnswer(operand1, operation, operand2, answer, currentAssignment)) {
                    return currentAssignment;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Map<Character, Integer> solution = solve("SEND", "+", "MORE", "MONEY");
        if(solution != null) {
            System.out.println(solution);
        } else {
            System.out.println("No Solution");
        }
    }
}
