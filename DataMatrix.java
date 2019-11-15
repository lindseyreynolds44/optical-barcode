
public class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';  

   private BarcodeImage image;
   private String text;
   private int actualWidth; // dependent on the data in the image. Can change as image changes
   private int actualHeight; // and can be computed from the "spine" of the image.

   // Default Constructor
   public DataMatrix()
   {
      /*
      constructs an empty, but non-null, image and text value.  The initial image should be all white, however, actualWidth and actualHeight should start at 0, so it won't really matter what's in this default image, in practice.  The text can be set to blank, "", or something like "undefined".
      */
      // Create a new empty image
      // Create a new empty text ""
      // actualWidth and actualHeight should start at 0

   }
   
   // 
   public DataMatrix(BarcodeImage image) 
   {
      // sets the image but leaves the text at its default value.  Call scan() and avoid duplication of code here.
   }

   public DataMatrix(String text) 
   {
      // sets the text but leaves the image at its default value. Call readText() and avoid duplication of code here.
   }
   
   public readText(String text) 
   {
      // a mutator for text.  Like the constructor;  in fact it is called by the constructor.
   }
   
   public scan(BarcodeImage image)
   {
      /*
      a mutator for image.  Like the constructor;  in fact it is called by the constructor.  Besides calling the clone() method of the BarcodeImage class, this method will do a couple of things including calling cleanImage() and then set the actualWidth and actualHeight.  Because scan() calls clone(), it should deal with the CloneNotSupportedException by embeddingthe clone() call within a try/catch block.  Don't attempt to hand-off the exception using a "throws" clause in the function header since that will not be compatible with the underlying BarcodeIO interface.  The catches(...) clause can have an empty body that does nothing.
      */
   }

   // Accessor for actualWidth
   public int getActualWidth()
   {

   }

   // Accessor for actualHeight
   public int getActualHeight()
   {

   }
   
   // Implementation of all BarcodeIO methods:
   
   public boolean generateImageFromText() 
   {
      // use readCharFromCol(int col) and WriteCharToCol(int col, int code)

   }
   
   public boolean translateImageToText() 
   {
      // use readCharFromCol(int col) and WriteCharToCol(int col, int code)

   }
   
   public void displayTextToConsole() 
   {

   }
   
   public void displayImageToConsole() 
   {
      // should display only the relevant portion of the image, clipping the excess blank/white from the top and right. 
   }
   
   // PRIVATE METHODS

   private int computeSignalWidth() 
   {
      /* 
      Assuming that the image is correctly situated in the lower-left corner of the larger boolean array, these methods use the "spine" of the array (left and bottom BLACK) to determine the actual size.
      */

   }
   
   private int computeSignalHeight() 
   {

   }

   private void cleanImage() 
   {
      /*
      This private method will make no assumption about the placement of the "signal" within a passed-in BarcodeImage.  In other words, the in-coming BarcodeImage may not be lower-left justified. 

      The cleanImage() method would be called from within scan() and would move the signal to the lower-left of the larger 2D array.  And, since scan() is called by the constructor, that implies that the image gets adjusted upon construction.  This kind of standardization represents the many other image processing tasks that would be implemented in the scan() method.  Error correction would be done at this point in a real class design. 
      */
   }

   // Method to help with manipulation in cleanImage()
   private void moveImageToLowerLeft()
   {

   } 
   // Method to help with manipulation in cleanImage()
   private void shiftImageDown(int offset)
   {

   }
   // Method to help with manipulation in cleanImage()
   private void shiftImageLeft(int offset)
   {

   }

   // Use for generateImageFromText() and translateImageToText()
   private char readCharFromCol(int col) 
   {
   
   }
   // Use for generateImageFromText() and translateImageToText()
   private boolean WriteCharToCol(int col, int code)
   {
   
   }

   // Optional 
   public void displayRawImage() 
   {
      /*
      Can be implemented to show the full image data including the blank top and right.  It is a useful debugging tool.
      */

   }

   
   // Optional 
   private void clearImage() 
   {
      // a nice utility that sets the image to white =  false.
   }

   // adding a change

}


