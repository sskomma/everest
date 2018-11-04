package com.algorithms.design;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class LogSystem {

/*    private TreeNode root;
    public LogSystem() {

    }

    public void put(int id, String timestamp) {
        String[] times = timestamp.split(":");
        TreeNode leafNode = retrieveNode(times);
        leafNode.setData(id);
    }
    private TreeNode retrieveNode(String[] times) {
        TreeNode traverser = root;
        for(String child: times) {
            traverser = traverser.getOrCreateChild(child);
        }
        return traverser;
    }



    public List<Integer> retrieve(String s, String e, String gra) {
        return null;
    }

    private List<Integer> retrieveData(String[] times) {
        TreeNode traverser = root;
        for(String child: times) {
            traverser = traverser.getChild(child);
            if(traverser == null)
                break;
        }
        if(traverser == null)
            return Collections.emptyList();

        List<Integer> data = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(traverser);
        while (!stack.isEmpty()) {
        *//*
            TreeNode current = stack.pop();
            if(current.getChildren() )
        }
        *//*


    }

    static class TreeNode {
        private String name;
        private Map<String, TreeNode> children;
        private int data;

        private TreeNode(String name) {
            this.name = name;
            children = new HashMap<>();
        }

        public String getName(){return name;}

        public void setData(int data){this.data = data;}

        public int getData() {return data;}

        public Collection<TreeNode> getChildren() {return children.values();}

        public TreeNode getChild(String child) {
            return children.get(child);
        }

        public TreeNode getOrCreateChild(String child) {
            if(!children.containsKey(child)) {
                addChild(child);
            }
            return children.get(child);
        }
        public TreeNode addChild(String child){
            if(!children.containsKey(child)){
                children.put(child, new TreeNode(child));
            }
            return children.get(child);
        }

    }*/
}
