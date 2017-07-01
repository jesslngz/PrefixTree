package SufixTree;

import java.util.ArrayList;
import java.util.List;

public class PrefixTree {
   private PrefixTreeNode root;

   public PrefixTree()
   {
      root = new PrefixTreeNode();
   }

   public void addWord(String word, String definition)
   {
      root.addWord(word.toLowerCase(),definition);
   }

   public PrefixTreeNode getRoot() {
      return root;
   }

   public void setRoot(PrefixTreeNode root) {
      this.root = root;
   }

   public List getWords(String prefix)
   {
      //Finds the node that represents the last letter of the entered prefix
      PrefixTreeNode lastNode = root;
      for (int i=0; i<prefix.length(); i++)
      {
      lastNode = lastNode.getNode(prefix.charAt(i));

      //if last node null (no words match it) return empty array
      if (lastNode == null) return new ArrayList();
      }

      //Return the words that are child of the node that represents the last letter of the prefix
      return lastNode.getWords();
   }
}