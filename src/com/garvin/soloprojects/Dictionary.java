package com.garvin.soloprojects;

import java.util.Scanner;

public class Dictionary {

    Node root;
    Dictionary(){
        root=null;
    }

    void createNode() {
        System.out.println("Enter The Number Of Nodes:\t");
        Scanner sc=new Scanner(System.in);
        int num= sc.nextInt();
        for(int i=0;i<=num;i++)
        {
            String data;
            System.out.println("Value for node no. "+i+"\t");
            data=sc.nextLine();
            root = insertNode(root,data);
        }
        System.out.println("1)InOrder\n");
        System.out.println("2)PreOrder\n");
        System.out.println("3)PostOrder\n");
        System.out.println("4)Search\n");
        System.out.println("5)Delete\n");
        int feature=sc.nextInt();
        switch (feature) {
            case 1 -> inOrder(root);
            case 2 -> preOrder(root);
            case 3 -> postOrder(root);
            case 4 -> {
                System.out.println("Enter data to be searched:");
                String temp = sc.nextLine();
                searchNode(root, temp);
            }
            case 5 -> {
                System.out.println("Enter data to be deleted:");
                String tempDel = sc.nextLine();
                deleteNode(root, tempDel);
            }
            default -> {
            }
        }
        inOrder(root);
    }

    void searchNode(Node root,String temp){
        if (root==null)
        {
            System.out.println("Not Found");
            return;
        }
        if (root.data.equals(temp))
        {
            System.out.println("Found");
        }
        else if (AsciiConvert(root.data) > AsciiConvert(temp))
        {
            searchNode(root.left, temp);
        }
        else if (AsciiConvert(root.data) < AsciiConvert(temp))
        {
            searchNode(root.right, temp);
        }
    }
    Node deleteNode(Node root,String temp){
        if (root==null)
        {
            return null;
        }
        if (AsciiConvert(root.data) > AsciiConvert(temp))
        {
            deleteNode(root.left, temp);
        }
        else if (AsciiConvert(root.data) < AsciiConvert(temp))
        {
            deleteNode(root.right, temp);
        }
        else{
            if (root.left!=null && root.right==null)
            {
                return root.left;
            }
            else if (root.right!=null && root.left==null)
            {
                return root.right;
            }
            root.data = minValue(root.right);
            root.right = deleteNode(root.right, root.data);
        }

        return root;
    }

    String minValue(Node root)  {
        //initially minval = root
        String minval = root.data;
        //find minval
        while (root.left != null)  {
            minval = root.left.data;
            root = root.left;
        }
        return minval;
    }
    int AsciiConvert(String str){
        int convert=0;
        for(int i=0;i<=str.length()-1;i++)
        {
            convert=str.charAt(i);
        }
        return convert;
    }
    Node insertNode(Node temproot, String data){
        if (temproot==null)
        {
            Node temp = new Node(data);
            return temp;
        }
        if (AsciiConvert(data)>AsciiConvert(temproot.data))
        {
            temproot.right=insertNode(temproot.right,data);
        }
        else if (AsciiConvert(data)<AsciiConvert(temproot.data))
        {
            temproot.left=insertNode(temproot.left,data);
        }
        return temproot;
    }
    void inOrder(Node root){
        if (root==null)
        {
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }
    void preOrder(Node root){
        if (root==null)
        {
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
    void postOrder(Node root){
        if (root==null)
        {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }
}