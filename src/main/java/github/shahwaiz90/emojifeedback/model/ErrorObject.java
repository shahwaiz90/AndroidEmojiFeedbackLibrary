package github.shahwaiz90.emojifeedback.model;

import java.io.Serializable;

/**
 * Created by Ahmad Shahwaiz on 11/9/2017.
 * Github: https://www.github.com/shahwaiz90
 * LinkedIn: https://www.linkedin.com/in/ahmadshahwaiz/
 */
public class ErrorObject implements Serializable {
    private String errorMessage;
    private int emoRating;
    private boolean isSelected;

    public int getEmoRating() {
        return emoRating;
    }

    public void setEmoRating(int emoRating) {
        this.emoRating = emoRating;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
