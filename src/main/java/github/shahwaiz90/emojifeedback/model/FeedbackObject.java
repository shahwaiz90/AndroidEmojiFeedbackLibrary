package github.shahwaiz90.emojifeedback.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ahmad Shahwaiz on 11/10/2017.
 * Github: https://www.github.com/shahwaiz90
 * LinkedIn: https://www.linkedin.com/in/ahmadshahwaiz/
 */

public class FeedbackObject implements Serializable {
    private int emoRating;
    private ArrayList<ErrorObject> errorObject;

    public ArrayList<ErrorObject> getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(ArrayList<ErrorObject> errorObject) {
        this.errorObject = errorObject;
    }

    public int getEmoSelected() {
        return emoRating;
    }

    public void setEmoSelected(int emoSelected) {
        this.emoRating = emoSelected;
    }

}
