/**********************************
 * Phase 3
 * 
 * DataMatrix.java
 * 
 * To implement the data matrix 
 *********************************/

 public class DataMatrix implements BarcodeIO
 {
     public static final char BLACK_CHAR = '*';
     public static final char WHITE_CHAR =  ' ';
     private BarcodeImage image; 
     private String text;
     private int actualWidth;
     private int actualHeight;

     /**
      * Default constructor 
      */
      public DataMatrix()
      {
          this.image = new BarcodeImage();
          this.text = "";
          this.actualHeight = 0;
          this.actualWidth = 0;
      }

      /**
       * Image constructor 
       */
      public DataMatrix (BarcodeImage image)
      {
          this();
          this.scan(image);
      }

      /**
       * Text constructor
       */
      public DataMatrix(String text)
      {
          this();
          this.readText(text);
      }

      /**
       * Accessor
       * gets actual width
       */
      public int getActualWidth()
      {
          return this.actualWidth;
      }

      /**
       * Accessor 
       * gets actual height
       */
      public int getActualHeight()
      {
          return this.actualHeight;
      }

      /**
       * Max width of image defined 
       */
      private int computeSignalWidth()
      {
          int width = 0;
          while (this.image.getPixel(width, this.image.MAX_HEIGHT - 1))
          {
              width++;
          }
          return width;
      }

      /**
       * Max height of image defined 
       */
      private int computeSignalHeight()
      {
          int height = this.image.MAX_HEIGHT - 1;
          while (this.image.getPixel(0, height))
          {
              height --;
          }
          return (this.image.MAX_HEIGHT - 1) - height;

      }

      /**
       * Sets text and width
       */
      public boolean readText(String text)
      {
          if (text.length > this.image.MAX_WIDTH)
          {
              return false;
          }

          this.text = text;
          this.actualWidth = text.length();
          return true;
      }

      /**
       * Sets text and actual width and height 
       */
      public boolean scan(BarcodeImage image)
      {
          try
          {
              this.image = image.clone();
          } catch (CloneNotSupportedException exception)
          {
              return false;
          }

          this.cleanImage();
          this.actualHeight = this.computeSignalHeight();
          this.actualWidth = this.computeSignalWidth();

          return true;
      }

      /**
       * Helper method 
       * reads a character from a column
       */
 

      /**
       * Helper method 
       * writes a character to a column
       */
     

      /**
       * To generate an image from the text 
       */
     
       

      /**
       * Converting set image into text
       */
      

      /**
       * Text logged to the console
       */
      

      /**
       * Prints the image to the console
       */
      

 }