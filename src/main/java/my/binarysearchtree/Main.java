package my.binarysearchtree;

/**
 * @author leon
 * @since 2019-01-11
 */
public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
//        int[] nums = new int[]{2, 3, 4, 1, 5, 7, 9};
//        for (int num : nums) {
//            bst.add(num);
//        }
//
//        System.out.println("------------inOrder--------------");
//        bst.inOrder();
//
//        System.out.println("remove min:");
//        bst.removeMin();
//        bst.inOrder();
//
//        System.out.println("------------preOrderNR--------------");
//        bst.preOrderNR();
//
//        System.out.println("----------toString---------------");
//        System.out.println(bst);

//        Random random = new Random();
//        List<Integer> list = new ArrayList();
//        //测试removeMin
//        for (int i = 0; i < 1000; i++) {
//            bst.add(random.nextInt(10000));
//        }
//
//        while (!bst.isEmpty()){
//            Integer min = bst.removeMin();
//            list.add(min);
//        }
//
//        for (int i = 0; i < list.size()-1; i++) {
//            System.out.print(list.get(i)+" ");
//            if (list.get(i) > list.get(i+1)){
//                throw new RuntimeException("验证失败");
//            }
//        }

        int[] nums = new int[]{5, 3, 4, 1, 2, 7, 6};
        for (int num : nums) {
            bst.add(num);
        }

        bst.removeElement(5);

    }
}
