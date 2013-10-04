public class PrintPrimes {
  
  /* Class variables */ 
  int numberOfPrimes;
  int rowsPerPage;
  int columnsPerPage;
  int comparisonMax;
  int listOfPrimes[];

  /* Constructor */
  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int comparisonMax) {
    this.numberOfPrimes = numberOfPrimes;
    this.rowsPerPage = rowsPerPage;
    this.columnsPerPage = columnsPerPage;
    this.comparisonMax = comparisonMax;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }

  /* Main Method: initializes a printPrimes object, and calls the calculatePrimes & 
   * printPrimesScreen methods to calculate the first 300 primes and print them */
  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimesScreen();
  }

  /* Method calculatePrimes() that implemements the Sieve of Eratostheme algorithm for finding
   * prime numbers. Also, uses helper method calculateOddPrimes() */
  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }
  
  /* Helper method of calculatePrimes() */
  private void calculateOddPrimes() {
      
      // declare local variables
      boolean isPrime;
      int primeIndex;
      int multipleOfPrime[] = new int[comparisonMax + 1];
      
      // initialize local variables
      int currentOddNumber = 1;
      int comparisonIndex = 2;
      int square = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          // only check odd numbers since even numbers are not prime
          currentOddNumber = currentOddNumber + 2;
          
          if (currentOddNumber == square) {
            comparisonIndex = comparisonIndex + 1;
            square = listOfPrimes[comparisonIndex] * listOfPrimes[comparisonIndex];
            multipleOfPrime[comparisonIndex - 1] = currentOddNumber;
          }
          
          primeIndex = 2;
          isPrime = true;
          
          while (primeIndex < comparisonIndex && isPrime) {
            
            while (multipleOfPrime[primeIndex] < currentOddNumber){
              multipleOfPrime[primeIndex] = multipleOfPrime[primeIndex] + listOfPrimes[primeIndex] + listOfPrimes[primeIndex];
            }
            
            if (multipleOfPrime[primeIndex] == currentOddNumber){
              isPrime= false;
            }
            
            primeIndex = primeIndex + 1;
          }
        } while (!isPrime); 
        
        listOfPrimes[primesFoundSoFar] = currentOddNumber;
      }
    }
  
  
  /* Method printPrimesScreen that prints the prime numbers found in calculatePrimes() */
    public void printPrimesScreen() {
      
      //Initilaze local variables
      int pageNumber = 1;
      int pageOffset = 1;
        
        // While loop to print the title of the page at every new page
        while (pageOffset <= numberOfPrimes) {
          
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          
          
          /* Loop to print the array listOfPrime[] 
           * Every page will print numberOfPrimes primes, in rowsPerPage rows and 
           * columnsPerPage columns
           * The columns of every row are printed first before printing the next row */
          
          // for every row
          for (int rowIndex = pageOffset; rowIndex < (pageOffset + rowsPerPage); rowIndex++){
            // for every column
            for (int columnIndex = 0; columnIndex < columnsPerPage; columnIndex++){
              // print only the adequate number of primes 
              if (rowIndex + (columnIndex * rowsPerPage) <= numberOfPrimes)
                // print the prime at the index
                System.out.format("%10d", listOfPrimes[rowIndex + (columnIndex * rowsPerPage)]);
            }
            System.out.println("");
          }
          
          // Change the page, update pageNumber and pageOffset
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + rowsPerPage* columnsPerPage;
        }
    }
}

      
