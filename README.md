# Boyer-Moore String Matching Algorithm

- Explore the Boyer-Moore String Matching Algorithm with the Bad Character Heuristic.
- Skips unnecessary comparisons.

<img width="569" alt="Screenshot 2024-08-19 at 5 39 06 PM" src="https://github.com/user-attachments/assets/e339324e-def8-4b78-a01c-15fa47a31d7a">

## Prerequisites

- Java Development Kit (JDK). [Download from Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).

## How To Use

1. **Clone the repository**
   
   ```bash
   git clone https://github.com/Lithia22/CPT212_Assignment_1.git
   ```
   
2. **Compile the program**
   
   ```bash 
   javac BoyerMoore.java
   ```
   
3. **Run the program**
   
   ```bash
   java BoyerMoore
   ```

## Sample Output

### Case 1: Pattern Found

**Scenario 1: Non-Repetitive Pattern**

- **Text:** BAD CHARACTER TABLE
- **Pattern:** TABLE

  Output:
  - `All characters match, so the pattern "TABLE" is found at index 14 in the text.`
  - `Total occurences of the pattern in the text: 1`

**Scenario 2: Repetitive Pattern**

- **Text:** AABAACAADAABAABA
- **Pattern:** AABA

  Output:
  - `All characters match, so the pattern "AABA" is found at index 0 in the text.`
  - `All characters match, so the pattern "AABA" is found at index 9 in the text.`
  - `All characters match, so the pattern "AABA" is found at index 12 in the text.`
  - `Total occurences of the pattern in the text: 3`

### Case 2: No Pattern Found

- **Text:** Boyer Moore
- **Pattern:** Algo

  Output: `No such pattern is found in the text`
