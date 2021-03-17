package com.android.tictactoe;


/**
 * {@link XO} make the ArrayList Hold ImageView
 */
public class XO {

    /**
     * Image resource ID for the X or O
     */
    private int mImageResourceId;


    /**
     * Create a new XO object.
     *
     * @param imageResourceId is the drawable resource ID for the image associated with the x/o
     */
    public XO(int imageResourceId) {

        mImageResourceId = imageResourceId;
    }

    /**
     * Return the image resource ID of the x/o.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }
    public void setImageResourceId(int mImageResourceId) { this.mImageResourceId = mImageResourceId;}

}
