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

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int ROWOFFSET = pageOffset; ROWOFFSET < pageOffset + rowsPerPage; ROWOFFSET++){
            for (int columnIndex = 0; columnIndex < columnsPerPage; columnIndex++)
              if (ROWOFFSET + columnIndex * rowsPerPage <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[ROWOFFSET + columnIndex * rowsPerPage]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + rowsPerPage* columnsPerPage;
        }
    }
}

      
