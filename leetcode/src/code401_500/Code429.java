package code401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * 1
 * /  |  \
 * 3   2   4
 * / \
 * 2   6
 * <p>
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 * <p>
 * <p>
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 *
 * @author leon
 * @date 2019-03-27
 */
public class Code429 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (root == null) {
                return res;
            }
            dfs(root, 0, res);
            return res;
        }

        private void dfs(Node node, int level, List<List<Integer>> list) {
            if (node == null) {
                return;
            }

            //fisrt init node
            if (list.size() == level) {
                List<Integer> current = new ArrayList<>();
                current.add(node.val);
                list.add(current);
            } else {
                list.get(level).add(node.val);
            }
            for (Node childNode : node.children) {
                dfs(childNode, level + 1, list);
            }
        }
    }


}
