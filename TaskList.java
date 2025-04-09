import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Node head;
    private int size;

    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public TaskList() {
        head = null;
        size = 0;
    }

    public void add(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    public Task remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node removed;
        if (index == 0) {
            removed = head;
            head = head.next;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            removed = prev.next;
            prev.next = removed.next;
        }
        size--;
        return removed.task;
    }

    public List<Task> toList() {
        List<Task> tasks = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            tasks.add(curr.task);
            curr = curr.next;
        }
        return tasks;
    }

    public Node getHead() {
        return head;
    }
}
