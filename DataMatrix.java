
public class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';  
   private BarcodeImage image;
   private String text;
   private int actualWidth; 
   private int actualHeight;
   

   // Constructors.  Three minimum, but you could have more:

   // Default Constructor
   
   public DataMatrix(BarcodeImage image) 
   {

   }

   public DataMatrix(String text) 
   {

   }
   
   public readText(String text) 
   {

   }
   
   public scan(BarcodeImage image)
   {


   }

   // Accessors for actualWidth and actualHeight but no mutators!

   private int computeSignalWidth() 
   {

   }
   
   private int computeSignalHeight() 
   {

   }
   
   // Implementation of all BarcodeIO methods:
   
   public boolean generateImageFromText() 
   {

   }
   
   public boolean translateImageToText() 
   {

   }
   
   public void displayTextToConsole() 
   {

   }
   
   public void displayImageToConsole() 
   {

   }
   
   // PRIVATE METHODS
   
   private void cleanImage() 
   {

   }

   // Optional 
   public void displayRawImage() 
   {

   }
   
   // Optional 
   private void clearImage() 
   {

   }

}


