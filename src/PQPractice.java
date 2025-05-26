import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PQPractice {

    private record GraphicNovel(String name, int interest) implements Comparable<GraphicNovel> {
        public int compareTo(GraphicNovel other) {
            return Integer.compare(this.interest, other.interest);
        }        
    }

    public static void main(String[] args) {
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // pq.add(7);
        // pq.add(33);
        // pq.add(2);
        // pq.add(99);

        // System.out.println(pq.poll());

        PriorityQueue<GraphicNovel> pq = new PriorityQueue<>(Comparator.reverseOrder());

        pq.add(new GraphicNovel("Witcher", 55));
        pq.add(new GraphicNovel("I am legend", 77));
        pq.add(new GraphicNovel("Dracula", 22));
        pq.add(new GraphicNovel("Edge of tomorrow", 100));

        System.out.println(pq.poll());



        List<Integer> nums = List.of(22, 3, 26, 77, 93, 46, 24, 44);
        System.out.println(topK(nums, 4));
        System.out.println(topKEfficient(nums, 4));
    }

    // return top K elements in the list, original list isn't modified
    public static List<Integer> topK(List<Integer> nums, int k) {
        List<Integer> copy = new ArrayList<>(nums);
        Collections.sort(copy);

        return copy.subList(nums.size() - k, nums.size());
    }

    public static List<Integer> topKEfficient(List<Integer> nums, int k) {
        PriorityQueue<Integer> best = new PriorityQueue<>();

        for (int num : nums) {
            if (best.size() < k) {
                best.add(num);
            } else if (num > best.peek()) {
                best.poll();
                best.add(num);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!best.isEmpty()) {
            result.add(best.poll());
        }

        return result;
    }
}