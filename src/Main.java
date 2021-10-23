import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        int population = 3;
        int boatCapacity = 2;
        int maxCrosses = 11;

        if (args.length == 3) {
            population = Integer.parseInt(args[0]);
            boatCapacity = Integer.parseInt(args[1]);
            maxCrosses = Integer.parseInt(args[2]);
        }

        State initialState = new State(population, boatCapacity);
        SpaceSearcher searcher = new SpaceSearcher(maxCrosses);

        long start = System.currentTimeMillis();
        State terminalState = searcher.BFSClosedSet(initialState);
        long end = System.currentTimeMillis();

        if (terminalState == null) {
            System.out.println("Could not find solution in " + maxCrosses + " crosses.");
            System.out.println("Search time: " + (double) (end - start) / 1000 + " seconds.");
        } else {
            State temp = terminalState;
            ArrayList<State> path = new ArrayList<>();
            while (temp.getFather() != null) {
                path.add(temp.getFather());
                temp = temp.getFather();
            }
            Collections.reverse(path);
            for (State item :
                    path) {
                item.print();
            }
            terminalState.print();
            System.out.println("Search time: " + (double) (end - start) / 1000 + " seconds, " + terminalState.getCrossNumber() + " total crosses.");
        }
    }
}
