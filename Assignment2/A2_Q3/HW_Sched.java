package A2_Q3;

import java.util.*;

public class HW_Sched {
    ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
    int m;
    int lastDeadline = 0;

    protected HW_Sched(int[] weights, int[] deadlines, int size) {
        for (int i=0; i<size; i++) {
            Assignment homework = new Assignment(i, weights[i], deadlines[i]);
            this.Assignments.add(homework);
            if (homework.deadline > lastDeadline) {
                lastDeadline = homework.deadline;
            }
        }
        m =size;
    }


    /**
     *
     * @return Array where output[i] corresponds to the assignment
     * that will be done at time i.
     */
    public int[] SelectAssignments() {
        //TODO Implement this


        //Sort assignments
        //Order will depend on how compare function is implemented
        Collections.sort(Assignments, new Assignment());

        // If homeworkPlan[i] has a value -1, it indicates that the
        // i'th timeslot in the homeworkPlan is empty
        //homeworkPlan contains the homework schedule between now and the last deadline
        int[] homeworkPlan = new int[lastDeadline];
        for (int i=0; i < homeworkPlan.length; ++i) {
            homeworkPlan[i] = -1;
        }

        for (int i = 0; i < Assignments.size(); i++) {
            // Check if the spot is empty
            int assIndex = (Assignments.get(i).deadline -1);
            int assNumber = Assignments.get(i).number;

            while (homeworkPlan[assIndex] != -1){
                assIndex--;
                if (assIndex<0){
                    break;
                }
            }
            if (assIndex > -1){
                homeworkPlan[assIndex] = assNumber;
            }

        }

        for(int i = 0; i< Assignments.size(); i++){
            Assignment a = Assignments.get(i);
            System.out.println(a.weight);
            System.out.println(a.deadline);
            System.out.println(a.number + "\n");
        }

        return homeworkPlan;
    }

    public static void main(String[] args) {

        //This is the typical kind of input you will be tested with. The format will always be the same
        //Each index represents a single homework. For example, homework zero has weight 23 and deadline t=3.
        int[] weights = new int[] {23, 60, 14, 25, 7};
        int[] deadlines = new int[] {3, 1, 2, 1, 3};
        int m = weights.length;

        //This is the declaration of a schedule of the appropriate size
        HW_Sched schedule =  new HW_Sched(weights, deadlines, m);

        //This call organizes the assignments and outputs homeworkPlan
        int[] res = schedule.SelectAssignments();
        System.out.println(Arrays.toString(res));
    }


}



