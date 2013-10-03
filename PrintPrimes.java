public class PrintPrimes {
  
  /* Class variables */ 
  int numberOfPrimes;
  int rowsPerPage;
  int columnsPerPage;
  int ORDMAX;
  int listOfPrimes[];

  /* Constructor */
  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.ORDMAX = ORDMAX;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }

  /* Main Method: initializes a printPrimes Object, and calls the calculatePrimes & 
   * printPrimes methods to calculate the first 300 primes and print them */
  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
      boolean isPrime;
      int N;
      int MULT[] = new int[ORDMAX + 1];
      
      int currentOddNumber = 1;
      int ORD = 2;
      int SQUARE = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentOddNumber = currentOddNumber + 2;
          if (currentOddNumber == SQUARE) {
            ORD = ORD + 1;
            SQUARE = listOfPrimes[ORD] * listOfPrimes[ORD];
            MULT[ORD - 1] = currentOddNumber;
          }
          N = 2;
          isPrime = false;
          while (N < ORD && !(isPrime)) {
            while (MULT[N] < currentOddNumber)
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            if (MULT[N] == currentOddNumber)
              isPrime= true;
            N = N + 1;
          }
        } while (isPrime); 
        listOfPrimes[primesFoundSoFar] = currentOddNumber;
      }
    }
  
  /* Method printPrimes that prints the prime numbers found in calculatePrimes() */
    public void printPrimes() {
      
      //Initilaze local variables
      int pageNumber = 1;
      int pageOffset = 1;
        
        // While loop to print the title of the page at every new page
        while (pageOffset <= numberOfPrimes) {
          
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          
          
          /* Loop to print the array listOfPrime[] on each page
           * The columns of every row are printed first before printing the next row */
          
          // for every row
          for (int rowIndex = pageOffset; rowIndex < (pageOffset + rowsPerPage); rowIndex++){
            // for every column
            for (int columnIndex = 0; columnIndex < columnsPerPage; columnIndex++)
              // print only the adequate number of prime 
              if (rowIndex + (columnIndex * rowsPerPage) <= numberOfPrimes)
                // print the prime at the index
                System.out.format("%10d", listOfPrimes[rowIndex + (columnIndex * rowsPerPage)]);
            System.out.println("");
          }
          
          // Change the page, update pageNumber and pageOffset
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + rowsPerPage* columnsPerPage;
        }
    }
}

      
