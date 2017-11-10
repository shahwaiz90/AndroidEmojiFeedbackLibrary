
 * Created by Ahmad Shahwaiz on 11/9/2017.
 * Github: https://www.github.com/shahwaiz90
 * LinkedIn: https://www.linkedin.com/in/ahmadshahwaiz/
 
 
 # Android-Emoji-Feedback
Fully customizable, easy to integrate, developer friendly and animated **Emoji Feedback library** to integrate.

![alt text](https://i.imgflip.com/1z2z2r.gif) 

 ## What is it?
Highly customizable feedback library with interactive emojis. Developers can use this library to get feedback from users about their application or after some specific activity or happy moments. It is designed by keeping in point the user behaviour. 
		
 ## How Developers can initiate the Call?
Developers can utilize this feedback screen and backend logic by writing following commands from their activity class.
	  
	/**
	 *	Make this call from your activity
	 *  Optional Params (if user wants to change custom messages or handle different locales):
	 *      feedbackemoji_error_messages
	 *      feedbackemoji_title_text_messages
	 *      feedbackemoji_subtitle_text_messages
	 *  Color Customization - Just replace strings color into your values/colors and change value of those color variables.
	 */
	 
	 private void showFeedbackScreen(){
		Intent intent = new Intent(this, FeedbackScreen.class);
		
		//If you want to change custom messages then you can make this custom bundle and add params in it. Otherwise, default strings will be used.
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
		
		startActivityForResult(intent, 786); 
	}
 		
## What and How to retrieve Objects when user exits the Feedback Screen?

You will receive an ArrayList of Objects which were available in the feedback screen.

	1. Emoji Rating. (int - 1 to 5)
	2. Error Message (String - "Lots of bugs")
	3. Error Message isSelected (boolean - true/false)
	
A Callback mechanism is developed. You can get your call-back response by writing the following code in that class from which you called startActivityForResult. 
"feedbackemoji_user_selection" is the variable you are looking for which will be sent to you via serialiazable bundled in intent.
		
	 // Write this code in that **same activity** from which you first initiated the call **startActivityForResult**.
	 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MainActivity ", "Request Code: "+requestCode);
        // Check which request we're responding to
        if (requestCode == 786) {
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
