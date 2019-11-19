package src;

public class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';  

   private BarcodeImage image;
   private String text;
   private int actualWidth; // dependent on the data in the image. Can change as image changes
   private int actualHeight; // and can be computed from the "spine" of the image.

   /****************************************** PERSON 1 ****************************************/
   /**
    *  Default constructor that takes no arguments and creates an blank image and empty text
    */ 
   public DataMatrix()
   {
      // Create empty default member variables 
      image = new BarcodeImage();
      text = "";
      actualWidth = 0;
      actualHeight = 0;
   }

   /**
    * Constructor that takes a BarcodeImage object as an argument
    * @param image
    */
   public DataMatrix(BarcodeImage image) 
   {
      if(image == null){
         return;
      }
      scan(image); // read in the image
      text = "";
   }

   /**
    * Constructor that takes in a text String
    * @param text
    */
   public DataMatrix(String text) 
   {
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
      readText(text); // read in the text
   }

   /**
    * Method to read in a text String
    * @param text 
    */
   public boolean readText(String text) 
   {
      if(text == null)
         return false;

      this.text = text;
      return true;
   }

   /**
    * Method to read in the image, make a copy of it and clean it up
    * @param image
    */
   public boolean scan(BarcodeImage image) 
   {
      try {
         this.image = image.clone();
         cleanImage();
         actualWidth = computeSignalWidth(); 
         actualHeight = computeSignalHeight(); 
         return true;
      } catch (CloneNotSupportedException e) {
         return false;
      }
      
   }

   /**
    * Accessor for actualWidth
    */ 
   public int getActualWidth()
   {
      return actualWidth;
   }

   /**
    * Accessor for actualHeight
    */ 
   public int getActualHeight()
   {
      return actualHeight;
   }
   /**************************************** END OF PERSON 1 ************************************/

   /******************************************PERSON******2**************************************/
   public boolean generateImageFromText() 
   {
      /**
       * use readCharFromCol(int col) and WriteCharToCol(int col, int code)
       * Not technically an I/O operation, this method looks at the internal text stored in the 
       * implementing class and produces a companion BarcodeImage, internally 
       * (or an image in whatever format the implementing class uses).  After this is called, 
       * we expect the implementing object to contain a fully-defined image and text that are in 
       * agreement with each other.
       */ 
      image = new BarcodeImage();

      this.actualHeight = 10;
      this.actualWidth = text.length() + 2;

      // Add top and bottom astrix border
      for(int col = 0; col < actualWidth; col++)
      {
         // Add border at top
         if(col % 2 == 0)
            image.setPixel(0, col, true);
         // Add border at bottom
         image.setPixel(actualHeight - 1, col, true);
      }

      // Add side astrix borders
      for(int row = 0; row < actualHeight; row++)
      {
         // Add border on right
         if(row % 2 == 0)
            image.setPixel(row, actualWidth - 1, true);
         // Add border on left
         image.setPixel(row, 0, true);
      }

      // Loop through the text, one character at a time, adding it to the image
      for(int charIndex = 0; charIndex < text.length(); charIndex++)
      {
         // Get the characters ascii value
         int asciiValue = text.charAt(charIndex);

         // Retrieve each value and write into the correct column (i.e. 128, 32, 16, etc.)
         int value = 128;
         while (value > 0)
         {
            // Check which column should be filled with an astrix
            if(asciiValue - value >= 0)
            {
               writeCharToCol(charIndex + 1, value);
               asciiValue -= value;
            }
            value /= 2; // Move on to the next value
         }
      }
      cleanImage();
      return true;
   }

   public boolean translateImageToText() 
   {
      /**
       * use readCharFromCol(int col) and WriteCharToCol(int col, int code)
       * Not technically an I/O operation, this method looks at the internal text stored in the 
       * implementing class and produces a companion BarcodeImage, internally 
       * (or an image in whatever format the implementing class uses).  After this is called, 
       * we expect the implementing object to contain a fully-defined image and text that are in 
       * agreement with each other.
       */ 

      char[] textArray = new char[actualWidth - 2];
      for(int col = 1; col < textArray.length + 1; col++)
      {
         textArray[col - 1] = readCharFromCol(col);
      }
      this.text = new String(textArray);
      return true;
   }

   // Helper for translateImageToText method  
   private char readCharFromCol(int col) 
   {
      // Find where the image starts 
      int startRow = 0;
      while(!image.getPixel(startRow, 0))
         startRow++;

      // Get the ascii value of the given column
      int asciiValue = 0;
      int value = 128;
      for(int row = startRow + 1; row < startRow + actualHeight - 1; row++)
      {
         if(image.getPixel(row, col))
            asciiValue += value;
         value /= 2;
      }
      return (char)asciiValue;
   }

   // Helper for generateImageFromText method
   private boolean writeCharToCol(int col, int code)
   {
      switch(code)
      {
         case 128:
            image.setPixel(1, col, true); 
            break;
         case 64:
            image.setPixel(2, col, true); 
            break;
         case 32:
            image.setPixel(3, col, true); 
            break;
         case 16:
            image.setPixel(4, col, true); 
            break;
         case 8:
            image.setPixel(5, col, true); 
            break;
         case 4:
            image.setPixel(6, col, true); 
            break;
         case 2:
            image.setPixel(7, col, true); 
            break;
         case 1:
            image.setPixel(8, col, true); 
            break;
      }
      return true;
      
   }
   public void displayTextToConsole() 
   {
      // prints out the text string to the console.
      System.out.println(this.text);
   }

   public void displayImageToConsole() 
   {
      /**
       * should display only the relevant portion of the image, 
       * clipping the excess blank/white from the top and right.
       * prints out the image to the console.  
       * In our implementation, we will do this in the form of a dot-matrix 
       * of blanks and asterisks
       */
      image.displayToConsole();


   }

   /****************************************END*****OF*******PERSON2************************************/

   // PRIVATE METHODS

   /**
    * Computes the width of the signal assuming it's already been shifted to
    * the lower left corner
    * @return The width of the signal.
    */
   private int computeSignalWidth() 
   {
      /* 
       Assuming that the image is correctly situated in the lower-left corner of the larger boolean array, these methods use the "spine" of the array (left and bottom BLACK) to determine the actual size.
       */
      int counter = 0;
      for(int col = 0; col < image.MAX_WIDTH; col++)
      {
         if(image.getPixel(image.MAX_HEIGHT - 1, col))
            counter++;
      }
      return counter; 
   }
   /*
    * Computes the height of the signal assuming it's already been shifted
    * to the lower left corner.
    * @return The height of the signal
    */
   private int computeSignalHeight() 
   {
      /* 
       Assuming that the image is correctly situated in the lower-left corner of the larger boolean array, these methods use the "spine" of the array (left and bottom BLACK) to determine the actual size.
       */
      int counter = 0;
      int firstCol = 0;
      for(int row = 0; row < image.MAX_HEIGHT; row++)
         if(image.getPixel(row,firstCol))
            counter++;
      return counter;  
   }

   private void cleanImage() 
   {
      /*
       This private method will make no assumption about the placement of the "signal" within a passed-in BarcodeImage.  In other words, the in-coming BarcodeImage may not be lower-left justified. 

       The cleanImage() method would be called from within scan() and would move the signal to the lower-left of the larger 2D array.  And, since scan() is called by the constructor, that implies that the image gets adjusted upon construction.  This kind of standardization represents the many other image processing tasks that would be implemented in the scan() method.  Error correction would be done at this point in a real class design. 
       */
      moveImageToLowerLeft();
   }

   // Method to help with manipulation in cleanImage()
   private void moveImageToLowerLeft()
   {
      int downOffset = countBlankRows();
      if(downOffset != 0)
         shiftImageDown(countBlankRows());

      int leftOffset = countBlankColumns();
      if(leftOffset != 0)
         shiftImageLeft(countBlankColumns());
   } 
   /**
    * Counts the number of blank rows from the bottom
    * @return Int count of blank rows from the bottom.
    */
   private int countBlankRows()
   {
      boolean blankRow = false;
      int countRow = 0;
      for(int row = image.MAX_HEIGHT - 1; row >= 0; row--)
      {
         for(int col = 0; col < image.MAX_WIDTH; col++)
         {
            if(!image.getPixel(row,col))
               blankRow = true;
            if(image.getPixel(row,col))
            {
               blankRow = false;
               return countRow;
            }            
         }
         if(blankRow)
            countRow++;
      }   
      return countRow;
   }

   /**
    * Counts the number of blank columns from the left.
    * @return Int count of blank columns from the left.
    */
   private int countBlankColumns()
   {
      boolean blankCol = false;
      int countCol = 0;
      for(int col = 0; col < image.MAX_WIDTH; col++)
      {
         for(int row = 0; row < image.MAX_HEIGHT; row++)
         {
            if(!image.getPixel(row,col))
               blankCol = true;
            if(image.getPixel(row,col))
            {
               blankCol = false;
               return countCol;
            }            
         }
         if(blankCol)
            countCol++;
      }   
      return countCol;
   }

   /**
    * Shift the array to the left most column
    * @param offset
    */
   private void shiftImageDown(int offset)
   {
      int lastRow = image.MAX_HEIGHT - 1;
      int shiftBy = lastRow - offset;

      for(int row = lastRow; row >= 0; row--)
      {
         for(int col = 0; col < image.MAX_WIDTH; col++)
         {
            image.setPixel(row, col, image.getPixel(shiftBy,col));
            image.setPixel(shiftBy,col,false);
         }
         shiftBy--;
         if(shiftBy == 0)
            break;
      }
   }
   /**
    * Shifts the Array the bottom most row.
    * @param offset
    */
   private void shiftImageLeft(int offset)
   {
      for(int col = 0; col < image.MAX_WIDTH; col++,offset++)
      {
         for(int row = 0; row < image.MAX_HEIGHT; row++)
         {
            image.setPixel(row,col,image.getPixel(row,offset));
            image.setPixel(row,offset,false);
         }
      }
   }


   // Optional 
   public void displayRawImage() 
   {
      /*
       Can be implemented to show the full image data including the blank top and right.  It is a useful debugging tool.
       */
      image.displayToConsole();
   }


   // Optional 
   private void clearImage() 
   {
      // a nice utility that sets the image to white =  false.
   }

}


