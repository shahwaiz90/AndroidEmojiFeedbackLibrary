package github.shahwaiz90.emojifeedback.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import github.shahwaiz90.emojifeedback.R;
import github.shahwaiz90.emojifeedback.model.FeedbackObject;
import github.shahwaiz90.emojifeedback.utils.SystemBarTintManager;
import github.shahwaiz90.emojifeedback.model.ErrorObject;

/**
 * Created by Ahmad Shahwaiz on 11/9/2017.
 * Github: https://www.github.com/shahwaiz90
 * LinkedIn: https://www.linkedin.com/in/ahmadshahwaiz/
 */

public class FeedbackScreen extends AppCompatActivity {

    ImageView mainEmoji, firstEmoji, secondEmoji, thirdfirstEmoji, fourthEmoji, fifthEmoji;
    TextView firstErrorMessage, secondErrorMessage, thirdErrorMessage, fourthErrorMessage, fifthErrorMessage, sixthErrorMessage, emojiReason, emojiExplain;
    LinearLayout errorMessages, errorMessagesLayer2;
    boolean firstFlag = true, secondFlag = true, thirdFlag = true, fourthFlag = true, fifthFlag = true, sixthFlag = true;
    Button sendButton;
    Animation mAnimation1;
    ArrayList<ErrorObject> errorObjectList;
    FeedbackObject feedbackObject;
    ErrorObject errorObject1, errorObject2, errorObject3, errorObject4, errorObject5, errorObject6;
    RelativeLayout feedbackScreenBackground;
    String TAG = "FeedbackScreen";
    Map<Integer, String> headerTextMessages;
    Map<Integer, String> subTitleTextMessages;

    /**
     * This function is called when activity is created.
     * initializations are done here.
     * @param savedInstanceState
     * savedInstanceState is given here by android
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_screen);
        initializeArrays();
        registerViews();
        registerFonts();
        setDefaultMessages();
        setCustomMessages();
        initializeErrorObjects();
        initializeAnimations();
        setBarColors(FeedbackScreen.this, R.color.md_blue_grey_900);
    }

    /**
     * HeaderTextMessages and subTitleTextMessages are the Hasmap Data Structure to Store messages set by user.
     * We used Hashmap to avoid redundancy. Sparse array can also be used here.
     */
    private void initializeArrays() {
        headerTextMessages       =      new HashMap<Integer, String>();
        subTitleTextMessages     =      new HashMap<Integer, String>();
    }

    /**
     * If user hasn't set any custom messages then default messages will be shown which are stored in strings
     */
    private void setDefaultMessages() {
        headerTextMessages.put(0, getString(R.string.yourfeedbackmatters));
        headerTextMessages.put(1, getString(R.string.wearesosorry));
        headerTextMessages.put(2, getString(R.string.notfeelingiteh));
        headerTextMessages.put(3, getString(R.string.justkindameh));
        headerTextMessages.put(4, getString(R.string.notbad));
        headerTextMessages.put(5, getString(R.string.emojislovely));

        subTitleTextMessages.put(0, getString(R.string.whatwentwrong));
        subTitleTextMessages.put(1, getString(R.string.whysobad));
        subTitleTextMessages.put(2, getString(R.string.whywasitnotgreat));
        subTitleTextMessages.put(3, getString(R.string.howcanweimprove));

        emojiExplain.setText(headerTextMessages.get(0));
    }

    /**
     * Custom messages which are set by user and sent via bundle intent will be set here.
     * User can set errors messages, title texts, and subtitle texts.
     */
    private void setCustomMessages() {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("feedbackemoji_error_messages") !=null) {
            ArrayList<String> obj = (ArrayList<String>) bundle.getSerializable("feedbackemoji_error_messages");
            if(obj != null) {
                for (int i = 0; i < obj.size(); i++) {
                    if (i == 0 && !obj.get(i).isEmpty()) {
                        firstErrorMessage.setText(obj.get(i));
                    } else if (i == 1 && !obj.get(i).isEmpty()) {
                        secondErrorMessage.setText(obj.get(i));
                    } else if (i == 2 && !obj.get(i).isEmpty()) {
                        thirdErrorMessage.setText(obj.get(i));
                    } else if (i == 3 && !obj.get(i).isEmpty()) {
                        fourthErrorMessage.setText(obj.get(i));
                    } else if (i == 4 && !obj.get(i).isEmpty()) {
                        fifthErrorMessage.setText(obj.get(i));
                    } else if (i == 5 && !obj.get(i).isEmpty()) {
                        sixthErrorMessage.setText(obj.get(i));
                    }
                }
            }
        }else{
                Log.i(TAG, "feedbackemoji_error_messages null");
        }

        if(bundle != null && bundle.getSerializable("feedbackemoji_title_text_messages") !=null) {
            ArrayList<String> titleMessages = (ArrayList<String>) bundle.getSerializable("feedbackemoji_title_text_messages");
            if(titleMessages != null) {
                for (int i = 0; i < titleMessages.size(); i++) {
                    if (i == 0 && !titleMessages.get(i).isEmpty()) {
                        headerTextMessages.put(i, titleMessages.get(i));
                        emojiExplain.setText(headerTextMessages.get(0));
                    } else if (i == 1 && !titleMessages.get(i).isEmpty()) {
                        headerTextMessages.put(i, titleMessages.get(i));
                    } else if (i == 2 && !titleMessages.get(i).isEmpty()) {
                        headerTextMessages.put(i, titleMessages.get(i));
                    } else if (i == 3 && !titleMessages.get(i).isEmpty()) {
                        headerTextMessages.put(i, titleMessages.get(i));
                    } else if (i == 4 && !titleMessages.get(i).isEmpty()) {
                        headerTextMessages.put(i, titleMessages.get(i));
                    } else if (i == 5 && !titleMessages.get(i).isEmpty()) {
                        headerTextMessages.put(i, titleMessages.get(i));
                    }
                }
            }
        }else{

            Log.i(TAG, "feedbackemoji_title_text_messages null");
        }

        if(bundle != null && bundle.getSerializable("feedbackemoji_subtitle_text_messages") !=null) {
            ArrayList<String> subTitleMessages = (ArrayList<String>) bundle.getSerializable("feedbackemoji_subtitle_text_messages");
            if(subTitleMessages != null) {
                for (int i = 0; i < subTitleMessages.size(); i++) {
                    if (i == 0 && !subTitleMessages.get(i).isEmpty()) {
                        subTitleTextMessages.put(i, subTitleMessages.get(i));
                    } else if (i == 1 && !subTitleMessages.get(i).isEmpty()) {
                        subTitleTextMessages.put(i, subTitleMessages.get(i));
                    } else if (i == 2 && !subTitleMessages.get(i).isEmpty()) {
                        subTitleTextMessages.put(i, subTitleMessages.get(i));
                    } else if (i == 3 && !subTitleMessages.get(i).isEmpty()) {
                        subTitleTextMessages.put(i, subTitleMessages.get(i));
                    }
                }
            }
        }else{
               Log.i(TAG, "feedbackemoji_subtitle_text_messages null");
        }
    }

    /**
     * Main Emoji Custom Animation
     */
    private void initializeAnimations() {
        mAnimation1 = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.15f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f);
        mAnimation1.setDuration(200);
        mAnimation1.setRepeatCount(-1);
        mAnimation1.setRepeatMode(Animation.REVERSE);
        mAnimation1.setInterpolator(new LinearInterpolator());
        mainEmoji.setAnimation(mAnimation1);
    }

    /**
     * Initialize Error object list and Error objects which will be added in it.
     */
    private void initializeErrorObjects() {

        errorObjectList             =       new ArrayList<>();
        feedbackObject              =       new FeedbackObject();
        errorObject1                =       new ErrorObject();
        errorObject2                =       new ErrorObject();
        errorObject3                =       new ErrorObject();
        errorObject4                =       new ErrorObject();
        errorObject5                =       new ErrorObject();
        errorObject6                =       new ErrorObject();

        errorObject1.setErrorMessage(firstErrorMessage.getText().toString());
        errorObject2.setErrorMessage(secondErrorMessage.getText().toString());
        errorObject3.setErrorMessage(thirdErrorMessage.getText().toString());
        errorObject4.setErrorMessage(fourthErrorMessage.getText().toString());
        errorObject5.setErrorMessage(fifthErrorMessage.getText().toString());
        errorObject6.setErrorMessage(sixthErrorMessage.getText().toString());
        errorObject1.setSelected(false);
        errorObject2.setSelected(false);
        errorObject3.setSelected(false);
        errorObject4.setSelected(false);
        errorObject5.setSelected(false);
        errorObject6.setSelected(false);
    }

    /**
     *
     * @param activity
     * Activity of this class
     * @param statusbarcolor
     * Gets R.color.white int type input
     */
    public static void setBarColors(Activity activity, int statusbarcolor){

        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, statusbarcolor));
        }
        else{
            SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintResource(statusbarcolor);
        }
    }

    /**
     * Settings Fonts used in this Screen
     */
    private void registerFonts() {
        firstErrorMessage.setTypeface(getFont_RobotoRegular(), Typeface.BOLD);
        secondErrorMessage.setTypeface(getFont_RobotoRegular(), Typeface.BOLD);
        thirdErrorMessage.setTypeface(getFont_RobotoRegular(), Typeface.BOLD);
        fourthErrorMessage.setTypeface(getFont_RobotoRegular(), Typeface.BOLD);
        fifthErrorMessage.setTypeface(getFont_RobotoRegular(), Typeface.BOLD);
        sixthErrorMessage.setTypeface(getFont_RobotoRegular(), Typeface.BOLD);

        emojiExplain.setTypeface(getFont_RobotoRegular(), Typeface.NORMAL);
        emojiReason.setTypeface(getFont_RobotoRegular(), Typeface.NORMAL);
    }

    /**
     * @return
     * Return Font which is placed in asset folder; return type is Typeface
     */
    public Typeface getFont_RobotoRegular(){
        return Typeface.createFromAsset(FeedbackScreen.this.getAssets(), "roboto.regular.ttf");
    }

    /**
     * Registering all views in this function.
     */
    private void registerViews() {
        mainEmoji                       =       (ImageView) findViewById(R.id.mainEmoji);
        firstEmoji                      =       (ImageView) findViewById(R.id.firstEmoji);
        secondEmoji                     =       (ImageView) findViewById(R.id.secondEmoji);
        thirdfirstEmoji                 =       (ImageView) findViewById(R.id.thirdEmoji);
        fourthEmoji                     =       (ImageView) findViewById(R.id.fourthEmoji);
        fifthEmoji                      =       (ImageView) findViewById(R.id.fifthEmoji);
        firstErrorMessage               =       (TextView)  findViewById(R.id.firstErrorMessage);
        secondErrorMessage              =       (TextView)  findViewById(R.id.secondErrorMessage);
        thirdErrorMessage               =       (TextView)  findViewById(R.id.thirdErrorMessage);
        fourthErrorMessage              =       (TextView)  findViewById(R.id.fourthErrorMessage);
        fifthErrorMessage               =       (TextView)  findViewById(R.id.fifthErrorMessage);
        sixthErrorMessage               =       (TextView)  findViewById(R.id.sixthErrorMessage);
        emojiExplain                    =       (TextView)  findViewById(R.id.emojiExplain);
        emojiReason                     =       (TextView)  findViewById(R.id.emojiReason);
        errorMessages                   =       (LinearLayout) findViewById(R.id.errorMessages);
        errorMessagesLayer2             =       (LinearLayout) findViewById(R.id.errorMessagesLayer2);
        sendButton                      =       (Button) findViewById(R.id.sendButton);
        feedbackScreenBackground        =       (RelativeLayout) findViewById(R.id.feedbackScreenBackground);
    }

    /**
     * When this image is clicked, mainemoji,titleText (emojiExplain) and subtitleText (emojiReason) are changed.
     * @param view
     * View of ImageView when user clicks on that image
     */
    public void firstEmoji(View view) {
        feedbackObject.setEmoSelected(1);
        firstEmoji.setBackgroundResource(R.drawable.face_a_05);
        secondEmoji.setBackgroundResource(R.drawable.face_b_06);
        thirdfirstEmoji.setBackgroundResource(R.drawable.face_b_11);
        fourthEmoji.setBackgroundResource(R.drawable.face_b_03);
        fifthEmoji.setBackgroundResource(R.drawable.face_b_02);
        mainEmoji.setBackgroundResource(R.drawable.face_a_09);
        emojiExplain.setText(headerTextMessages.get(1));
        emojiReason.setText(subTitleTextMessages.get(0));
        errorMessages.setVisibility(View.VISIBLE);
        emojiReason.setVisibility(View.VISIBLE);
        errorMessagesLayer2.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
    }

    /**
     * When this image is clicked, mainemoji,titleText (emojiExplain) and subtitleText (emojiReason) are changed.
     * @param view
     * View of ImageView when user clicks on that image
     */
    public void secondEmoji(View view) {
        feedbackObject.setEmoSelected(2);
        firstEmoji.setBackgroundResource(R.drawable.face_a_06);
        secondEmoji.setBackgroundResource(R.drawable.face_a_06);
        thirdfirstEmoji.setBackgroundResource(R.drawable.face_b_11);
        fourthEmoji.setBackgroundResource(R.drawable.face_b_03);
        fifthEmoji.setBackgroundResource(R.drawable.face_b_02);
        mainEmoji.setBackgroundResource(R.drawable.face_a_10);
        emojiExplain.setText(headerTextMessages.get(2));
        emojiReason.setText(subTitleTextMessages.get(1));
        emojiReason.setVisibility(View.VISIBLE);
        errorMessages.setVisibility(View.VISIBLE);
        errorMessagesLayer2.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
    }

    /**
     * When this image is clicked, mainemoji,titleText (emojiExplain) and subtitleText (emojiReason) are changed.
     * @param view
     * View of ImageView when user clicks on that image
     */
    public void thirdEmoji(View view) {
        feedbackObject.setEmoSelected(3);
        firstEmoji.setBackgroundResource(R.drawable.face_a_11);
        secondEmoji.setBackgroundResource(R.drawable.face_a_11);
        thirdfirstEmoji.setBackgroundResource(R.drawable.face_a_11);
        fourthEmoji.setBackgroundResource(R.drawable.face_b_03);
        fifthEmoji.setBackgroundResource(R.drawable.face_b_02);
        emojiExplain.setText(headerTextMessages.get(3));
        emojiReason.setText(subTitleTextMessages.get(2));
        mainEmoji.setBackgroundResource(R.drawable.face_a_04);
        errorMessages.setVisibility(View.VISIBLE);
        emojiReason.setVisibility(View.VISIBLE);
        errorMessagesLayer2.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
    }

    /**
     * When this image is clicked, mainemoji, titleText (emojiExplain) and subtitleText (emojiReason) are changed.
     * @param view
     * View of ImageView when user clicks on that image
     */
    public void fourthEmoji(View view) {
        feedbackObject.setEmoSelected(4);
        firstEmoji.setBackgroundResource(R.drawable.face_a_03);
        secondEmoji.setBackgroundResource(R.drawable.face_a_03);
        thirdfirstEmoji.setBackgroundResource(R.drawable.face_a_03);
        fourthEmoji.setBackgroundResource(R.drawable.face_a_03);
        fifthEmoji.setBackgroundResource(R.drawable.face_b_02);
        mainEmoji.setBackgroundResource(R.drawable.face_a_01);
        emojiReason.setText(subTitleTextMessages.get(3));
        emojiExplain.setText(headerTextMessages.get(4));

        errorMessages.setVisibility(View.VISIBLE);
        emojiReason.setVisibility(View.VISIBLE);
        errorMessagesLayer2.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
    }

    /**
     * When this image is clicked, mainemoji,titleText (emojiExplain) and subtitleText (emojiReason) are changed.
     * If user gives 5 star rating, it means he is happy, set false all the error objects.
     * @param view
     * View of ImageView when user clicks on that image
     */
    public void fifthEmoji(View view) {
        feedbackObject.setEmoSelected(5);
        firstEmoji.setBackgroundResource(R.drawable.face_a_02);
        secondEmoji.setBackgroundResource(R.drawable.face_a_02);
        thirdfirstEmoji.setBackgroundResource(R.drawable.face_a_02);
        fourthEmoji.setBackgroundResource(R.drawable.face_a_02);
        fifthEmoji.setBackgroundResource(R.drawable.face_a_02);
        mainEmoji.setBackgroundResource(R.drawable.face_a_08);
        emojiExplain.setText(headerTextMessages.get(5));

        errorMessages.setVisibility(View.GONE);
        emojiReason.setVisibility(View.GONE);
        errorMessagesLayer2.setVisibility(View.GONE);
        sendButton.setVisibility(View.VISIBLE);

        errorObject1.setSelected(false);
        errorObject2.setSelected(false);
        errorObject3.setSelected(false);
        errorObject4.setSelected(false);
        errorObject5.setSelected(false);
        errorObject6.setSelected(false);
    }

    /**
     * Highlight of box and errorObjects are handled.
     * @param view
     * TextView view is given in the function
     */
    public void firstErrorMessage(View view) {

        if(firstFlag){
            firstErrorMessage.setBackgroundResource(R.drawable.feedback_border_selected);
            firstFlag = false;
            errorObject1.setSelected(true);
        }else{
            errorObject1.setSelected(false);
            firstErrorMessage.setBackgroundResource(R.drawable.feedback_border);
            firstErrorMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.feedback_colorBgSelected));
            firstFlag = true;
        }
    }

    /**
     * Highlight of box and errorObjects are handled.
     * @param view
     * TextView view is given in the function
     */
    public void secondErrorMessage(View view) {

        if(secondFlag){
            secondErrorMessage.setBackgroundResource(R.drawable.feedback_border_selected);
            secondFlag = false;
            errorObject2.setSelected(true);
        }else{
            errorObject2.setSelected(false);
            secondErrorMessage.setBackgroundResource(R.drawable.feedback_border);
            secondErrorMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.feedback_colorBgSelected));
            secondFlag = true;
        }
    }

    /**
     * Highlight of box and errorObjects are handled.
     * @param view
     * TextView view is given in the function
     */
    public void thirdErrorMessage(View view) {
        if(thirdFlag){
            thirdErrorMessage.setBackgroundResource(R.drawable.feedback_border_selected);
            thirdFlag = false;
            errorObject3.setSelected(true);
        }else{
            errorObject3.setSelected(false);
            thirdErrorMessage.setBackgroundResource(R.drawable.feedback_border);
            thirdErrorMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.feedback_colorBgSelected));
            thirdFlag = true;
        }
    }

    /**
     * Highlight of box and errorObjects are handled.
     * @param view
     * TextView view is given in the function
     */
    public void fourthErrorMessage(View view) {
        if(fourthFlag){
            fourthErrorMessage.setBackgroundResource(R.drawable.feedback_border_selected);
            fourthFlag = false;
            errorObject4.setSelected(true);
        }else{
            errorObject4.setSelected(false);
            fourthErrorMessage.setBackgroundResource(R.drawable.feedback_border);
            fourthErrorMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.feedback_colorBgSelected));
            fourthFlag = true;
        }
    }

    /**
     * Highlight of box and errorObjects are handled.
     * @param view
     * TextView view is given in the function
     */
    public void fifthErrorMessage(View view) {
        if(fifthFlag){
            fifthErrorMessage.setBackgroundResource(R.drawable.feedback_border_selected);
            fifthFlag = false;
            errorObject5.setSelected(true);
        }else{
            errorObject5.setSelected(false);
            fifthErrorMessage.setBackgroundResource(R.drawable.feedback_border);
            fifthErrorMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.feedback_colorBgSelected));
            fifthFlag = true;
        }
    }

    /**
     * Highlight of box and errorObjects are handled.
     * @param view
     * TextView view is given in the function
     */
    public void sixthErrorMessage(View view) {
        if(sixthFlag){
            sixthErrorMessage.setBackgroundResource(R.drawable.feedback_border_selected);
            sixthFlag = false;
            errorObject6.setSelected(true);
        }else{
            errorObject6.setSelected(false);
            sixthErrorMessage.setBackgroundResource(R.drawable.feedback_border);
            sixthErrorMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.feedback_colorBgSelected));
            sixthFlag = true;
        }
    }

    /**
     * All Error Objects are added in the ErrorObjectList and then sent to previous activity via serializable intent.
     * @param view
     * Button view is given in the function
     */
    public void sendButton(View view) {
        errorObjectList.add(errorObject1);
        errorObjectList.add(errorObject2);
        errorObjectList.add(errorObject3);
        errorObjectList.add(errorObject4);
        errorObjectList.add(errorObject5);
        errorObjectList.add(errorObject6);
        feedbackObject.setErrorObject(errorObjectList);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("feedbackemoji_user_selection", feedbackObject);
        intent.putExtras(bundle);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
