package SufixTree;


import java.util.ArrayList;
import java.util.List;

public class PrefixTreeNode {
    private PrefixTreeNode parent;
    private PrefixTreeNode[] children;
    private boolean isLeaf;     //Quick way to check if any children exist
//    private boolean isWord;     //represents the last character of a word
    private String definition;     //represents the definition of a word
    private char character;     //The character this node represents

//    Constructor to initialize the tree
    public PrefixTreeNode() {
        children = new PrefixTreeNode[26];
        isLeaf = true;
//        isWord = false;
    }

//    Constructor to initialize the other nodes
    public PrefixTreeNode(char character) {
        this();
        this.character = character;
    }

    protected void addWord(String word, String definition) {
        isLeaf = false;
        int charPos = word.charAt(0) - 'a';
        System.out.println("Char Position in the alphabet = " + (word.charAt(0) - 'a'));
        if (children[charPos] == null) {
            System.out.println("Add char " + word.charAt(0) + " at index of children: " + charPos);
            children[charPos] = new PrefixTreeNode(word.charAt(0));
            System.out.println("Set parent to this node");
            children[charPos].parent = this;
        }

        if (word.length() > 1) {
            System.out.println("Word size is " + word.length() + " so call the method recursively in the substring " + word.substring(1));
            children[charPos].addWord(word.substring(1), definition);
        } else {
            System.out.println("The word lenght is " + word.length() + " so flag as isWord and get out");
//            children[charPos].isWord = true;
            children[charPos].definition = definition;
            System.out.println("printing the word with the toString() recursive method: " + children[charPos]);
        }
    }

    protected List getWords() {
        //Create a list to return
        List words = new ArrayList();
        //If this node represents a word, add it
        if (definition != null) {
            words.add(toString());
        }
        //If any children
        if (!isLeaf) {
            //Add any words belonging to any children
            for (PrefixTreeNode aChildren : children) {
                if (aChildren != null) {
                    words.addAll(aChildren.getWords());
                }
            }
        }
        return words;
    }

    protected PrefixTreeNode getNode(char c)
    {
        return children[c - 'a'];
    }

    public String toString()
    {
        if (parent == null)
        {
            return "";
        } else
        {
            return parent.toString() + new String(new char[]{character});
        }
    }
}