package com.komma.ik.recurssion.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.komma.ik.common.TreeNode;

public class BuildTreeFromPreOrder {


    static TreeNode build_binary_search_tree(List<Integer> preorder) {
        if(preorder == null || preorder.isEmpty()) {
            return null;
        }
        List<Integer> inorder = new ArrayList<>(preorder);
        Collections.sort(inorder);
        Map<Integer, Integer> ioLookup = new HashMap<>();
        for(int i = 0; i < inorder.size(); i++ ) {
            ioLookup.put(inorder.get(i), i);
        }
        return build_binary_search_tree_helper_inorder(preorder, 0, 0, ioLookup);
    }

    private static TreeNode build_binary_search_tree_helper_inorder(List<Integer> preorder, int poStart,
                                                                    int ioBegin, Map<Integer, Integer> ioLookup) {
        //Base Case
        if(poStart <= ioBegin) {
            return null;
        }

        TreeNode root = new TreeNode(preorder.get(poStart));
        int ioIndex = ioLookup.get(preorder.get(poStart));
        int leftSize = ioIndex - ioBegin;
        root.left_ptr = build_binary_search_tree_helper_inorder(preorder, poStart + 1, ioBegin, ioLookup);
        root.right_ptr = build_binary_search_tree_helper_inorder(preorder, (poStart + 1+ leftSize), ioIndex+1, ioLookup);
        return root;
    }



    public static void main(String[] args) {
        System.out.println(build_binary_search_tree(Arrays.asList(1,0,2)));
    }

}
