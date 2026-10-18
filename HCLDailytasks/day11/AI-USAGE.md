# AI Usage & Prompt Engineering Log (`AI-USAGE.md`)

## Entry 1: Student POJO Generation (Vague vs. Specific Prompts)
* **Prompt Type:** Vague
* **Prompt Used:** *"Write a java class for student"*
* **AI Output Flaws:** Generated raw types, public mutable fields violating encapsulation, no validation, and missing `equals()`/`hashCode()`.
* **Prompt Type:** Specific
* **Prompt Used:** *"Generate a Java 17 immutable-friendly Student POJO with private fields (id, name, email), private ArrayList courses, proper encapsulation, defensive copying in getters, and standard equals/hashCode implementation."*
* **Resolution:** Accepted the specific prompt output because it adhered to clean OOP principles right away.

---

## Entry 2: StudentService & Collection Review
* **Prompt Used:** *"Generate a Java service class to manage students and their grades using a HashMap."*
* **AI Output Flaws:** Returned internal maps directly, swallowed exceptions using empty catch blocks, and lacked null checks.
* **Review & Fixes Applied:**
    1. Replaced direct map/list returns with unmodifiable collections (`Collections.unmodifiableList`).
    2. Replaced swallowed exceptions with proper custom exception logging and propagation.
    3. Added strict `Objects.requireNonNull()` validation checks.

---

## Entry 3: E-Commerce Entity & Repository Generation (`Category` & `CategoryRepository`)
* **Prompt Used:** *"Generate a Spring Data JPA Category entity and repository for an e-commerce platform with id, name, description, and audit timestamps, ensuring proper JPA annotations."*
* **AI Output Flaws:** Missed cascading strategies and used primitive types instead of wrapper classes for IDs.
* **Review & Fixes Applied:** Switched `long` to `Long`, added `@PrePersist` hooks for timestamps, and reviewed repository method signatures for query optimization.