package src;

public class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;	
   public static final int MAX_WIDTH = 65;
   private boolean[][] imageData;
   
   /**
    * Default Constructor
    */ 
   public BarcodeImage() 
   {
      defaultImage();
   }

   /**
    * Contructor that takes an array of strings
    */  
   public BarcodeImage(String[] strData) 
   {
      // Check if the string array is null or too large
      if(!checkSize(strData))
      {
         System.out.println("Null or too big");
         return;
      }

      defaultImage();
   
      for(int imageRow = MAX_HEIGHT - 1, strRow = strData.length - 1; strRow >= 0; imageRow--, strRow--)
      {
         for(int col = 0; col < strData[strRow].length(); col++)
         {
            if(strData[strRow].charAt(col) == ' ')
               imageData[imageRow][col] = false;
            else 
               imageData[imageRow][col] = true;
         }

      }  
   }

   private void defaultImage()
   {
      imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];

      // loop through the array, making all booleans false (blank)
      for(int row = 0; row < MAX_HEIGHT; row++)
      {
         for(int col = 0; col < MAX_WIDTH; col++)
         {
            imageData[row][col] = false;
         }
      }
   }
   
   /**
    * Accessor for each bit in the image
    */
   public boolean getPixel(int row, int col) 
   {
      // return false if this pixel is out of bounds
      if(row > MAX_HEIGHT - 1 || col > MAX_WIDTH - 1)
         return false;

      // locate the pixel and return its value
      return imageData[row][col];     
   }

   // Mutator for each bit in the image
   public boolean setPixel(int row, int col, boolean value)
   {
      // return false if this pixel is out of bounds
      if(row > MAX_HEIGHT - 1 || col > MAX_WIDTH - 1)
         return false;

      // set this location with value
      imageData[row][col] = value;
      return true;
   }
   
   // Optional 
   // A private utility method is highly recommended, but not required:  
   private boolean checkSize(String[] data)  
   {
      if(data == null || data.length > MAX_HEIGHT)
      {
         return false;
      }
      for(String row: data)
      {
         if(row.length() > MAX_WIDTH)
            return false;
      }
      return true;
   }
   
   public void displayToConsole() 
   {
      for(int top = 0; top < MAX_WIDTH + 2; top++)
      {
         System.out.print("-");
      }
      System.out.println();
      // loop through the array, printing all the values
      for(int row = 0; row < MAX_HEIGHT; row++)
      {
         System.out.print("|");
         for(int col = 0; col < MAX_WIDTH ; col++)
         {
            if(imageData[row][col])
            {
               System.out.print("*");
            }
            else
            {
               System.out.print(" ");
            }
         }
         System.out.print("|\n");
      }
      for(int bottom = 0; bottom < MAX_WIDTH + 2; bottom++)
      {
         System.out.print("-");
      }
      System.out.println();
   }
   
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      BarcodeImage copy = (BarcodeImage)super.clone();

      // loop through the array, copying values
      for(int row = 0; row < MAX_HEIGHT; row++)
      {
         for(int col = 0; col < MAX_WIDTH; col++)
         {
            copy.setPixel(row, col, this.getPixel(row, col));
         }
      }
      
      return copy;

   }

}