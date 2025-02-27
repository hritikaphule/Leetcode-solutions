class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-heap (PriorityQueue) to store the smallest elements from each list
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Insert the head of each linked list into the min-heap
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        // Dummy node to simplify list creation
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Process the min-heap until it is empty
        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll(); // Extract the smallest element
            current.next = smallest; // Add it to the result list
            current = current.next;

            if (smallest.next != null) {
                pq.add(smallest.next); // Add the next element of the extracted node
            }
        }

        return dummy.next;
    }
}