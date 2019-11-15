/**
 * BarcodeImage.java
 * 
 * @author Ricardo Barbosa 
 * @version November 15, 2019
 * 
 * BarcodeImage Class
 */
public class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   
   private boolean[][] imageData = new boolean[MAX_WIDTH][MAX_HEIGHT];
   
   //default constructor
   public BarcodeImage()
   {
      initImageData();
   }
   
   public BarcodeImage(String[] strData)
   {
      this();
      
     if (checkSize(strData))
     {
        imageData = new boolean[MAX_WIDTH][MAX_HEIGHT];

        //loops through each character in the image 
        for (int row = 0; row < strData.length; ++row)
        {
           for (int col = 0; col < strData[row].length(); ++col)
           {
              if (strData[row].charAt(col) == DataMatrix.BLACK_CHAR)
                  setPixel(col, row, true);
              else
                  setPixel(col, row, false);
           }
        }
     }
     else
     {
        System.out.println("Barcode image must fall within a 30x65 dimensional space");
     }
   }
   
   private boolean checkSize(String[] data)
   {
      if (data.length < MAX_HEIGHT)
      {
         int widthCount = 0;
         char[][] dataChar = new char[data.length][];
         for (int i = 0; i < data.length; ++i)
         {
            widthCount = 0;
            dataChar[i] = data[i].toCharArray();
            for (int j = 0; j < dataChar[i].length; ++j)
            {
               widthCount++;
               if (widthCount >= MAX_WIDTH)
               {
                  return false;
               }
            }
         }
         return true;
      }
      return false;
   }
   
   private void initImageData()
   {
      for(int row = 0; row < MAX_WIDTH; row++)
      {
         for(int col = 0; col < MAX_HEIGHT; col++)
         {
            setPixel(row, col, false);
         }
      }
   }
   
   // Accessor
   public boolean getPixel(int col, int row)
   {
      if((row >= 0 && row < MAX_HEIGHT) && (col >= 0 && col < MAX_WIDTH))
      {
         return imageData[col][row];
      }
      return false;
   }
   
   // Mutator
   public boolean setPixel(int col, int row, boolean value)
   {
      if((row >= 0 && row < MAX_HEIGHT) && (col >= 0 && col < MAX_WIDTH))
      {
         imageData[col][row] = value;
         return true;
      }
      return false;
   }
   
   @Override
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      BarcodeImage cloneObject = new BarcodeImage();

      //duplicate filed data
      for (int i = 0; i < MAX_WIDTH; ++i)
      {
         for (int j = 0; j < MAX_HEIGHT; ++j)
         
            cloneObject.imageData[i][j] = this.imageData[i][j];
         
      }
      return cloneObject;
   }
   
   public String displayToConsole()
   {
      String displayData = "";

      for (int i = 0; i < MAX_WIDTH; ++i)
      {
         //will print out the rows
         displayData += "i: " + i + " "; 

         //will print out cols
         for (int j = 0; j < MAX_HEIGHT; ++j)
         {
            displayData += "\t[" + i + "]" + "[" + j + "]:" +
                                 imageData[i][j] + "\n";
         }
         displayData += "\n";
      }
      return displayData;
   }
}
