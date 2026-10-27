import java.util.List;
import java.util.ArrayList;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){
          List<Integer> l1 = parse(traversal1);
          List<Integer> l2 = parse(traversal2);
          List<Integer> l3 = parse(traversal3);

          if (l1 == null || l2 == null || l3 == null) {
               return "Invalid traversals";
          }
          if (l1.size() != l2.size() || l1.size() != l3.size()) {
               return "Invalid traversals";
          }

          String result;
          result = verify(l1, l2, l3, 0, 1, 2);
          if (result != null) {
               return result;
          }
          result = verify(l1, l3, l2, 0, 2, 1);
          if (result != null) {
               return result;
          }
          result = verify(l2, l1, l3, 1, 0, 2);
          if (result != null) {
               return result;
          }
          result = verify(l2, l3, l1, 1, 2, 0);
          if (result != null) {
               return result;
          }
          result = verify(l3, l1, l2, 2, 0, 1);
          if (result != null) {
               return result;
          }
          result = verify(l3, l2, l1, 2, 1, 0);
          if (result != null) {
               return result;
          }

          return "Invalid traversals";
     }

     private static List<Integer> parse(String s) {
          if (s == null || s.trim().isEmpty()) {
               return null;
          }
          String[] parts = s.split("-");
          List<Integer> result = new ArrayList<>();
          try{
               for (String part : parts) {
                    result.add(Integer.parseInt(part.trim()));
               }
          } catch (NumberFormatException e) {
               return null; // Invalid input
          }
          return result;
     }

     private static String verify(List<Integer> preCandidate, List<Integer> inCandidate, List<Integer> postCandidate, int preIndex, int inIndex, int postIndex) {
          BST bst = new BST();
          for (int value : preCandidate) {
               bst.insert(value);
          }

          if (bst.getInorderTraversal().equals(inCandidate) && bst.getPreorderTraversal().equals(preCandidate) && bst.getPostorderTraversal().equals(postCandidate)) {
               String[] roles = new String[3];
               roles[preIndex] = "Preorder";
               roles[inIndex] = "Inorder";
               roles[postIndex] = "Postorder";
               return "Traversal 1 - " + roles[0] + ", Traversal 2 - " + roles[1] + ", Traversal 3 - " + roles[2];
          }

          return null;
     }

     static class Node {
          int value;
          Node left, right;
          Node(int value) {
               this.value = value;
               this.left = null;
               this.right = null;
          }
     }

     static class BST {
          Node root;

          void insert(int value) {
               root = insertRecursive(root, value);
          }

          Node insertRecursive(Node root, int value) {
               if (root == null) {
                    return new Node(value);
               }

               if (value < root.value) {
                    root.left = insertRecursive(root.left, value);
               }
               else if (value > root.value) {
                    root.right = insertRecursive(root.right, value);
               }

               return root;
          }

          List<Integer> getPreorderTraversal() {
               List<Integer> result = new ArrayList<>();
               preorderTraversal(root, result);
               return result;
          }

          void preorderTraversal(Node root, List<Integer> result) {
               if (root != null) {
                    result.add(root.value);
                    preorderTraversal(root.left, result);
                    preorderTraversal(root.right, result);
               }
          }

          List<Integer> getPostorderTraversal() {
               List<Integer> result = new ArrayList<>();
               postorderTraversal(root, result);
               return result;
          }

          void postorderTraversal(Node root, List<Integer> result) {
               if (root != null) {
                    postorderTraversal(root.left, result);
                    postorderTraversal(root.right, result);
                    result.add(root.value);
               }
          }

          List<Integer> getInorderTraversal() {
               List<Integer> result = new ArrayList<>();
               inorderTraversal(root, result);
               return result;
          }

          void inorderTraversal(Node root, List<Integer> result) {
               if (root != null) {
                    inorderTraversal(root.left, result);
                    result.add(root.value);
                    inorderTraversal(root.right, result);
               }
          }
     }
}