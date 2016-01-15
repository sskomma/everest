package com.questions.linkedlist;

public class LinkedList
{
    private Node head;
    
    /**Adds a node ad the end of the linked list. If the list is empty, it will create a new head. 
     * 
     * @param n number to be placed in the new node. 
     */
    public void addNode(int n)
    {
       if(head == null)
           head = new Node(n);
       else
       {
           Node traverse = head;
           while(traverse.hasNext())
           {
               traverse = traverse.getNext();
           }
           traverse.next = new Node(n);
       }
    }

    /**Removes all nodes that have a given value. 
     * 
     * @param number
     */
    public void removeNode(int number)
    {
        if(head == null)
            return;
        
        if(head.val == number)
        {
            if(head.hasNext())
            {
                head = head.getNext();
                removeNode(number);
            }
            else{
                head = null;
            }
            return;
        }
        
        Node traverser = head;
        Node previous = null;
        while(traverser.hasNext())
        {
            previous = traverser;
            traverser = traverser.getNext(); 
            if(traverser.val == number)
            {
                previous.next = traverser.next;
                traverser = previous;
            }
        }
        return;
    }
    
    /**Prints out the linked list
     * 
     */
    public void printList()
    {
        Node node = head;
        if(node != null)
        {
            do
            {
                System.out.print(node.val);
                if(node.hasNext()) System.out.print("->");
                node = node.next;
            }while(node != null);
        }
        else
            System.out.println("Empty List");
        System.out.println("");
    }
    
    public static void main(String[] args)
    {
        LinkedList list = new LinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(5);
        list.addNode(5);
        list.addNode(3);
        System.out.println("Linked list before printing");
        list.printList();
        list.removeNode(5);
        System.out.println("Linked list after printing");
        list.printList();

    }

}
