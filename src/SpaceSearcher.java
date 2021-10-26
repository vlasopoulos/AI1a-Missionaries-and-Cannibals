import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {
    private ArrayList<State> frontier;
    private HashSet<State> closedSet;
    private int maxCrosses;

    SpaceSearcher(int k) {
        this.frontier = new ArrayList<>();
        this.closedSet = new HashSet<>();
        this.maxCrosses = k;
    }

    State AStarClosedSet(State initialState) {
        if (initialState.isFinal()) return initialState;
        frontier.add(initialState);

        while (frontier.size() > 0) {
            State currentState = frontier.remove(0);
            if (currentState.getCrossNumber() <= maxCrosses) {
                if (currentState.isFinal()) return currentState;
                if (!closedSet.contains(currentState)) {
                    closedSet.add(currentState);
                    frontier.addAll(currentState.getChildren());
                    Collections.sort(frontier);
                }
            }
        }
        return null;
    }

//    State BFSClosedSet(State initialState) {
//        if (initialState.isFinal()) return initialState;
//        frontier.add(initialState);
//
//        while (frontier.size() > 0) {
//            State currentState = frontier.remove(0);
//            if (currentState.getCrossNumber() <= maxCrosses) {
//                if (currentState.isFinal()) return currentState;
//                if (!closedSet.contains(currentState)) {
//                    closedSet.add(currentState);
//                    frontier.addAll(currentState.getChildren());
//                }
//            }
//        }
//        return null;
//    }
//
//    State DFSClosedSet(State initialState) {
//        if (initialState.isFinal()) return initialState;
//        frontier.add(initialState);
//
//        while (frontier.size() > 0) {
//            State currentState = frontier.remove(0);
//            if (currentState.getCrossNumber() <= maxCrosses) {
//                if (currentState.isFinal()) return currentState;
//                if (!closedSet.contains(currentState)) {
//                    closedSet.add(currentState);
//                    frontier.addAll(0, currentState.getChildren());
//                }
//            }
//        }
//        return null;
//    }
}
