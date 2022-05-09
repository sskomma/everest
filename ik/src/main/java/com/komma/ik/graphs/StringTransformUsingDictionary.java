package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringTransformUsingDictionary {


    /*
     * Complete the function below.
     */
    static String[] string_transformation(String[] words, String start, String stop) {
        String[] result = new String[1];
        result[0] = "-1";
        if(words == null || words.length == 0 || start.equals(stop)) {
            return result;
        }

        List<String> sampleSet = new ArrayList<>();
        sampleSet.add(start);
        sampleSet.addAll(Arrays.asList(words));
        sampleSet.add(stop);
        // Build adj map.
        Map<String, List<String>> adjMap = buildAdjMap(sampleSet);

        // DFS Intitialize

        Map<String, Boolean> visited = new HashMap<>();
        for(String sample: sampleSet) {
            visited.put(sample, false);
        }
        visited.put(start, true);


        // BFS
        Map<String, String> parent = new HashMap<>();
        if(bfs_path(start, stop, adjMap, visited, parent)) {
            Deque<String> path = new LinkedList<>();
            String traveller = stop;
            path.push(traveller);
            while(!traveller.equals(start)) {
                traveller = parent.get(traveller);
                path.push(traveller);
            }

            return new ArrayList<>(path).toArray(new String[path.size()]);

        }


        // return result

        return result;

    }

    static boolean bfs_path(String start, String stop, Map<String, List<String>> adjMap,
                            Map<String, Boolean> visited, Map<String, String> parent) {
        Deque<String> queue = new LinkedList<>();
        queue.addLast(start);
        visited.put(start, true);

        while(!queue.isEmpty()) {
            String node = queue.removeFirst();
            List<String> neighbors = adjMap.get(node);
            for(String neighbor: neighbors) {
                if(visited.get(neighbor)) continue;

                visited.put(neighbor, true);
                queue.addLast(neighbor);
                parent.put(neighbor, node);
                if(neighbor.equals(stop)) {
                    return true;
                }
            }
        }
        return false;
    }

    static Map<String, List<String>> buildAdjMap(List<String> sampleSet) {
        // Intitialize.
        Map<String, List<String>> adjMap = new HashMap<>();

        for (int i = 0; i < sampleSet.size(); i++) {
            adjMap.put(sampleSet.get(i), new ArrayList<>());
        }

        int numChars = sampleSet.get(0).length();

        // Fill adjMap
        for (int i = 0; i < sampleSet.size(); i++) {

            String word_one = sampleSet.get(i);
            for (int j = i + 1; j < sampleSet.size(); j++) {

                String word_two = sampleSet.get(j);
                int unCommonChars = 0;
                for (int k = 0; k < numChars; k++) {
                    unCommonChars = word_one.charAt(k) == word_two.charAt(k) ? unCommonChars: unCommonChars+1;
                    if(unCommonChars > 1) {
                        k = numChars;
                    }
                }
                if (unCommonChars<=1) {
                    adjMap.get(word_one).add(word_two);
                    adjMap.get(word_two).add(word_one);
                }
            }
        }
        return adjMap;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "hat", "bad", "had"};
        //String[] words = {"aaa"};
        String[] sol = string_transformation(words, "bat", "had");
        for (String word : sol) {
            System.out.println(word);
        }
    }


}
