# Day 10: Memory Management Lab Notes

## 1. Core Concepts (Heap vs Stack & GC)
* **Stack Memory:** Stores primitive local variables and method call stack frames. Each thread has its own stack, and variables are automatically cleaned up when the method finishes.
* **Heap Memory:** A shared runtime data area where all objects, arrays, and instance variables are allocated. It is managed by the Garbage Collector (GC).
* **Garbage Collection (GC):** Objects become eligible for GC when they are no longer reachable from root references. The heap is divided into the **Young Generation** (frequent Minor GCs) and **Old/Tenured Generation** (frequent Major/Full GCs for long-lived objects).
* **Common Memory Pitfalls:** Growing static collections, unclosed resources, string concatenation (`+`) inside loops, and integer cache boundaries (`-128` to `127`).

---

## 2. Memory Lab Experiments & Results Table

| Experiment | Configuration / Action | Observed Result | Explanation (In My Own Words) |
|---|---|---|---|
| **1. StackOverflowError** | Tested recursion depth with default `-Xss` vs `-Xss256k`. | Default crashed at ~10,400 frames; `-Xss256k` crashed at ~2,300 frames. | Recursive calls push new stack frames. Smaller stack sizes allocate less memory per thread, exhausting stack space much quicker. |
| **2. OutOfMemoryError** | Populated a list with large objects repeatedly under `-Xmx64m`. | `java.lang.OutOfMemoryError: Java heap space` thrown after a few seconds. | Capping heap at 64MB restricts growth. Once Eden/Old generations fill up and GC cannot free reachable space, the JVM crashes. |
| **3. String Concatenation vs StringBuilder** | Appended strings 50,000 times using `String +=` vs `StringBuilder.append()`. | `String +=` took ~3,200 ms; `StringBuilder` completed in ~4 ms. | Strings are immutable. `+=` creates a new String object on every iteration, wasting CPU and memory. `StringBuilder` mutates a single internal buffer efficiently. |
| **4. GC Logging Output** | Executed heavy object allocation with `-Xlog:gc*` enabled. | Generated logs showing frequent Young Gen Minor GCs and promotions to Tenured generation. | Provided real-time metrics on GC pause times, memory reclamation efficiency, and generational transitions. |
| **5. Integer Cache Pitfall** | Compared integer instances using `==` for values inside vs. outside cache range. | `100 == 100` evaluated to `true`; `200 == 200` evaluated to `false`. | Java caches `Integer` objects between `-128` and `127`. Within this range, `==` compares cached reference addresses; outside it, separate heap objects are created. |

---

## 3. Project Code Review Notes
* **Static Collections:** Checked that no static maps or lists hold onto object references indefinitely without boundaries.
* **Resource Handling:** Verified that files and streams utilize `try-with-resources` to prevent resource leaks.
* **String Manipulations:** Ensured efficient string handling instead of heavy concatenation in loops.

---

## 4. Stack/Heap Diagram Concept
* **Stack:** Holds method local variables, primitives, and object references for active execution threads.
* **Heap:** Stores actual instantiated objects and collections. When methods exit, stack frames are popped, and unreferenced heap objects become eligible for GC.