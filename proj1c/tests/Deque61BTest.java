import org.junit.jupiter.api.*;
import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import deque.*;
import java.util.Iterator;
//import deque.Deque61B;
//import deque.ArrayDeque61B;
//import deque.LinkedListDeque61B;

public class Deque61BTest {
    /** array deque iterator test*/
    @Test
    public void ArrayIteratorTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        //****
        for (String s : lld1) {
            System.out.println(s);
        }
        assertThat(lld1).containsExactly("front", "middle", "back");
    }
    /** List deque iterator test*/
    @Test
    public void LinklistDeque61BIteratorTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        //****
        for (String s : lld1) {
            System.out.println(s);
        }
        assertThat(lld1).containsExactly("front", "middle", "back");
    }

    @Test
    public void testListEqualDeques61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);

        lld2.addLast("dhw");
        assertThat(lld1.equals(lld2)).isFalse();
    }

    @Test
    public void ArrayEqualDeques61BTest() {
        Deque61B<String> q1 = new ArrayDeque61B<>();
        Deque61B<String> q2 = new ArrayDeque61B<>();

        q1.addLast("front");
        q1.addLast("middle");
        q1.addLast("back");

        q2.addLast("front");
        q2.addLast("middle");
        q2.addLast("back");

        assertThat(q1).isEqualTo(q2);

        q2.addLast("dhw");
        assertThat(q1.equals(q2)).isFalse();
    }

    @Test
    public void ListAndArrayDequetostringTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);

        Deque61B<String> q1 = new ArrayDeque61B<>();

        q1.addLast("dhw");
        q1.addLast("love");
        q1.addLast("fjy");

        System.out.println(q1);
    }




}
