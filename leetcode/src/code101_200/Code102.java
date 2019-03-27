package code101_200;

import java.util.ArrayList;
import java.util.List;

/**
 * 102. 二叉树的层次遍历
 * <p>
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @author leon
 * @date 2019-03-24
 */
public class Code102 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> res = new ArrayList<>();

            if (root == null) {
                return res;
            }

            dfs(root, 0, res);
            return res;
        }

        private void dfs(TreeNode node, int level, List<List<Integer>> list){
            if(node == null) {
                return;
            }
            //第一层或者第一次添加需要初始化节点
            if(list.size() == level){
                List<Integer> current = new ArrayList<>();
                current.add(node.val);
                list.add(current);
            }else {
                list.get(level).add(node.val);
            }

            dfs(node.left, level+1, list);
            dfs(node.right, level+1, list);
        }
    }
}
