# Day 11: AI Code Review Checklist & Issues Log

## AI Code Review Checklist
1. **Encapsulation:** Are fields private? Are setters safe?
2. **Internal Collections:** Do getters return direct references or defensive copies/unmodifiable wrappers?
3. **Identity (`equals` / `hashCode`):** Are they implemented correctly based on business keys rather than memory addresses?
4. **Null Safety:** Are parameters checked for null using `Objects.requireNonNull` or Optional?
5. **Collection Choice:** Are appropriate data structures used (`ArrayList`, `HashSet`, `HashMap`) instead of raw types or legacy classes?
6. **Exception Handling:** Are exceptions logged and propagated properly instead of being swallowed (`e.printStackTrace()`)?

---

## 5 Genuine AI Issues Found & Fixed (StudentService Exercise)

1. **Returning Mutable Internal Lists (Encapsulation Leak):**
    * *AI Issue:* `public List<Course> getCourses() { return this.courses; }`
    * *Fix:* `public List<Course> getCourses() { return Collections.unmodifiableList(this.courses); }`
2. **Swallowed Exceptions:**
    * *AI Issue:* `catch (Exception e) { }` (Empty catch block hiding runtime failures).
    * *Fix:* Logged the error and threw a custom runtime exception: `catch (DataAccessException e) { logger.error("Fetch failed", e); throw new ServiceException("Error loading data", e); }`
3. **Missing `equals()` and `hashCode()` on Domain Models:**
    * *AI Issue:* Entity classes relied on default object reference equality.
    * *Fix:* Added `@EqualsAndHashCode` equivalent standard overrides focusing on unique identifiers (`id` / `email`).
4. **Raw Types & Legacy Collections:**
    * *AI Issue:* Used `Vector` and raw `List` objects.
    * *Fix:* Refactored to generics-safe `ArrayList<>` and `HashMap<>`.
5. **Unchecked Null Pointers on Parameters:**
    * *AI Issue:* Methods accepted arguments without checking if they were null.
    * *Fix:* Implemented `Objects.requireNonNull(student, "Student cannot be null");` at the top of service methods.