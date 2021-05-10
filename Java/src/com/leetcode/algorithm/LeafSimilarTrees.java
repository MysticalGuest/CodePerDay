 package com.leetcode.algorithm;

import java.util.ArrayList;

/**
 * @author MysteryGuest
 * @date 2021/05/10
 */
public class LeafSimilarTrees {
    
    /**
     * 判断两棵树的叶子节点的值顺序是否相同
     * 
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了94.26%的用户
     * */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> leafNodes1 = new ArrayList<>();
        readLeafNode(root1, leafNodes1);
//        System.out.println("leafNodes1.toString():  "+leafNodes1.toString());
        ArrayList<Integer> leafNodes2 = new ArrayList<>();
        readLeafNode(root2, leafNodes2);
//        System.out.println("leafNodes2.toString():  "+leafNodes2.toString());
        if (leafNodes1.size()!=leafNodes2.size()) {
            return false;
        }
        else {
            for (int i = 0; i < leafNodes1.size(); i++) {
                if (leafNodes1.get(i) != leafNodes2.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * 递归构建二叉树
     * */
    public void createTree(TreeNode root, String []arr, int index) {
        if(!arr[index].equals(null)) {
//            if (root==null) {
//                System.out.println("create node");
//                root = new TreeNode();
//            }
            root.val=Integer.parseInt(arr[index]);
//            System.out.println("root.val: "+root.val);
            // leftIndex 左孩子下标
            int leftIndex = 2*index+1;
            if (leftIndex>=arr.length) {
//                System.err.println("left");
                root.left=null;
            }
            else if (arr[leftIndex]==null) {
//                System.err.println("left");
                root.left=null;
            }
            else {
                /*
                 * 直接使用createTree(root.left, arr, leftIndex);无法创建树，为什么？
                 * */
                TreeNode leftNode = new TreeNode();
                root.left = leftNode;
                createTree(leftNode, arr, leftIndex);
            }
            // rightIndex 右孩子下标
            int rightIndex = 2*index+2;
            if (rightIndex>=arr.length) {
//                System.err.println("right");
                root.right = null;
            }
            else if (arr[rightIndex]==null) {
//                System.err.println("right");
                root.right = null;
            }
            else {
//                System.err.println("right create");
                TreeNode rightNode = new TreeNode();
                root.right = rightNode;
                createTree(rightNode, arr, rightIndex);
            }
        }
    }
    
    /**
     * 先序遍历
     * */
    public void preOrder(TreeNode root, ArrayList<Integer> pre) {
//        if (!root.equals(null)) {
//            无法使用这种形式判空，因为root为null时，就是null.equqls(null)，会出现空指针错误
//        }
        if (root!=null) {
            pre.add(root.val);
            preOrder(root.left, pre);
            preOrder(root.right, pre);
        }
    }
    
    /**
     * 中序遍历
     * */
    public void inOrder(TreeNode root, ArrayList<Integer> in) {
        if (root!=null) {
            inOrder(root.left, in);
            in.add(root.val);
            inOrder(root.right, in);
        }
    }
    
    /**
     * 后序遍历
     * */
    public void postOrder(TreeNode root, ArrayList<Integer> post) {
        if (root!=null) {
            postOrder(root.left, post);
            postOrder(root.right, post);
            post.add(root.val);
        }
    }
    
    /**
     * 将二叉树画出来
     * */
    public void printTree(TreeNode root, int level) {
        int l = level+1;
        if (root!=null) {
            printTree(root.right, l);
            for (int i = 0; i < l; i++) {
                System.out.print("   ");
            }
            System.out.println(root.val);
            
            printTree(root.left, l);
        }
    }
    
    /**
     * 利用先序遍历将叶子节点顺序读出存在动态数组中
     * */
    public void readLeafNode(TreeNode root, ArrayList<Integer> pre) {
        if (root!=null) {
            // 是叶子节点
            if (root.left==null && root.right==null) {
                pre.add(root.val);
            }
            readLeafNode(root.left, pre);
            readLeafNode(root.right, pre);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        LeafSimilarTrees leafSimilarTrees = new LeafSimilarTrees();
        
        // 用例
        /*
         * Input: root1 = [1,2], root2 = [2,2]
         * Output: true
         * */
//        String[] arr_root1 = {"1", "2"};
//        String[] arr_root2 = {"2", "2"};
        /*
         * Input: root1 = [1], root2 = [1]
         * Output: true
         * */
//        String[] arr_root1 = {"1"};
//        String[] arr_root2 = {"1"};
        
        /*
         * Input: root1 = [1,2,3], root2 = [1,3,2]
         * Output: false
         * */
        String[] arr_root1 = {"1", "2", "3"};
        String[] arr_root2 = {"1", "3", "2"};
        
        /*
         * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
         * Output: true
         * */
//        String[] arr_root1 = {"3", "5", "1", "6", "2", "9", "8", null, null, "7", "4"};
//        String[] arr_root2 = {"3", "5", "1", "6", "7", "4", "2", null, null, null, null, null, null, "9", "8"};
        
        // 操作
        TreeNode root1 = new TreeNode();
        TreeNode root2 = new TreeNode();
        // 创建两棵树
        leafSimilarTrees.createTree(root1, arr_root1, 0);
        leafSimilarTrees.createTree(root2, arr_root2, 0);
        
        // 打印树
//        System.out.println("第一棵树：");
//        leafSimilarTrees.printTree(root1, 0);
//        System.out.println("第二棵树：");
//        leafSimilarTrees.printTree(root2, 0);
        // 二叉树的3个序列
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        System.out.println("第一棵树的三个序列：");
//        leafSimilarTrees.preOrder(root1, arrayList);
//        System.out.println("preOrder: "+arrayList.toString());
//        arrayList.clear();
//        leafSimilarTrees.inOrder(root1, arrayList);
//        System.out.println("inOrder: "+arrayList.toString());
//        arrayList.clear();
//        leafSimilarTrees.postOrder(root1, arrayList);
//        System.out.println("postOrder: "+arrayList.toString());
        
        System.out.println(leafSimilarTrees.leafSimilar(root1, root2));
    }

}

/**
 * Definition for a binary tree node.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
