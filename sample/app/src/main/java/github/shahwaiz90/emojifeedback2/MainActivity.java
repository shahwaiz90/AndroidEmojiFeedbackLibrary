package github.shahwaiz90.emojifeedback2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import github.shahwaiz90.emojifeedback.model.FeedbackObject;
import github.shahwaiz90.emojifeedback.views.FeedbackScreen;

/**
 * Sample Class for User. From here developer will call that feedback screen.
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    int EmojiFeedbackRequestCode = 786;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *  Optional Params (if user wants to change custom messages or handle different locales):
     *      feedbackemoji_error_messages
     *      feedbackemoji_title_text_messages
     *      feedbackemoji_subtitle_text_messages
     *  Color Customization - Just replace strings color into your values/colors and change value of those color variables.
     */

    public void sendButton(View view) {
        Intent intent = new Intent(this, FeedbackScreen.class);
        Bundle customBundle = new Bundle();

        ArrayList<String> errorMessages         =       new ArrayList<>();
        ArrayList<String> titleTexts            =       new ArrayList<>();
        ArrayList<String> subtitleTexts         =       new ArrayList<>();

        errorMessages.add("Lots of Bugs");
        errorMessages.add("Slow");
        errorMessages.add("Confusing");
        errorMessages.add("Not User Friendly");
        errorMessages.add("Can be better");
        errorMessages.add("Awesome!");

        titleTexts.add("Your Feedback Matters");
        titleTexts.add("We are sorry...");
        titleTexts.add("Not Feeling it?");
        titleTexts.add("Just Meh");
        titleTexts.add("Glad to hear!");
        titleTexts.add("Aww! Love you too!");

        subtitleTexts.add("What went wrong?");
        subtitleTexts.add("Too Bad?");
        subtitleTexts.add("Why was it not great?");
        subtitleTexts.add("How can we improve?");

        customBundle.putSerializable("feedbackemoji_error_messages", errorMessages);
        customBundle.putSerializable("feedbackemoji_title_text_messages", titleTexts);
        customBundle.putSerializable("feedbackemoji_subtitle_text_messages", subtitleTexts);
        intent.putExtras(customBundle);
        startActivityForResult(intent, EmojiFeedbackRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MainActivity ", "Request Code: "+requestCode);
        // Check which request we're responding to
        if (requestCode == EmojiFeedbackRequestCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if(bundle.getSerializable("feedbackemoji_user_selection") !=null) {
                    FeedbackObject feedbackObject =  (FeedbackObject) bundle.getSerializable("feedbackemoji_user_selection");
                    if(feedbackObject != null) {
                        for (int i = 0; i < feedbackObject.getErrorObject().size(); i++) {
                            Toast.makeText(getApplicationContext(),"Emoji Feedback Number: "+feedbackObject.getEmoSelected()+ " Error Message: " + feedbackObject.getErrorObject().get(i).getErrorMessage()+" isSelectedStatus: "+feedbackObject.getErrorObject().get(i).isSelected(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Log.i("MainActivity ", "Result Code: NULL");
                }
            }
        }
    }
}
