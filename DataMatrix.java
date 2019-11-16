
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
    public void scan(BarcodeImage image)
    {
       try {
          this.image = image.clone();
       } catch (CloneNotSupportedException e) {
       }
       cleanImage();
       actualWidth = computeSignalWidth(); 
       actualHeight = computeSignalHeight(); 
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
      // use readCharFromCol(int col) and WriteCharToCol(int col, int code)
      // Not technically an I/O operation, this method looks at the internal text stored in the implementing class and produces a companion BarcodeImage, internally (or an image in whatever format the implementing class uses).  After this is called, we expect the implementing object to contain a fully-defined image and text that are in agreement with each other.

   }
   
   public boolean translateImageToText() 
   {
      // use readCharFromCol(int col) and WriteCharToCol(int col, int code)
      // Not technically an I/O operation, this method looks at the internal image stored in the implementing class, and produces a companion text string, internally.  After this is called, we expect the implementing object to contain a fully defined image and text that are in agreement with each other.

   }
   
    // Use for generateImageFromText() and translateImageToText()
    private char readCharFromCol(int col) 
    {
    
    }
    // Use for generateImageFromText() and translateImageToText()
    private boolean WriteCharToCol(int col, int code)
    {
    
    }
   public void displayTextToConsole() 
   {
      // prints out the text string to the console.
   }
   
   public void displayImageToConsole() 
   {
      // should display only the relevant portion of the image, clipping the excess blank/white from the top and right.
      //  prints out the image to the console.  In our implementation, we will do this in the form of a dot-matrix of blanks and asterisks 
   }

   /****************************************END*****OF*******PERSON2************************************/
   
   // PRIVATE METHODS

   private int computeSignalWidth() 
   {
      /* 
      Assuming that the image is correctly situated in the lower-left corner of the larger boolean array, these methods use the "spine" of the array (left and bottom BLACK) to determine the actual size.
      */
   }
   private int computeSignalHeight() 
   {
      /* 
      Assuming that the image is correctly situated in the lower-left corner of the larger boolean array, these methods use the "spine" of the array (left and bottom BLACK) to determine the actual size.
      */
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

}


