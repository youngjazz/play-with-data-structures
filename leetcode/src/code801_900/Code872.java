package code801_900;

import java.util.ArrayList;
import java.util.List;

/**叶子相似的树
 *
 * 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 * 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * @author leon
 * @date 2019-03-27
 */
public class Code872 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {

            List<Integer> leafList = new ArrayList<>();
            List<Integer> leafList2 = new ArrayList<>();
            leafList(root1, leafList);
            leafList(root2, leafList2);

            if(leafList.size() != leafList2.size()){
                return false;
            }

            for (int i = 0; i < leafList.size(); i++) {
                if(leafList.get(i) != leafList2.get(i)){
                    return false;
                }
            }

            return true;
        }


        private void leafList(TreeNode node,List<Integer> leafList){
            if(node == null){
                return;
            }
            if(node.left == null && node.right == null){
                leafList.add(node.val);
            }else {
                leafList(node.left, leafList);
                leafList(node.right, leafList);
            }
        }
    }
}
