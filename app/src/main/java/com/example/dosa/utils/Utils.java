package com.example.dosa.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dosa.ui.Activity.ReadingActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {


    // set word clickable
    // reference: https://github.com/abhishekBhartiProjects/HighlightText

    public static Spannable setSpannableText(TextView textView, String content, Context context){

        if(!TextUtils.isEmpty(content)){
            String definition = content.trim();
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(definition, TextView.BufferType.SPANNABLE);

            Spannable spans = (Spannable) textView.getText();
            BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
            iterator.setText(definition);
            int start = iterator.first();

            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
                String clickedWord = definition.substring(start, end);
                if (Character.isLetterOrDigit(clickedWord.charAt(0))) {
                    ClickableSpan clickSpan = getClickableSpan(textView, clickedWord, content, context);
                    spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            return spans;
        }
        return null;
    }

    public static ClickableSpan getClickableSpan(final TextView textView, final String word, final String content, Context context) {
        return new ClickableSpan() {
            final String clickedWord;
            {
                clickedWord = word;
            }

            @Override
            public void onClick(View widget) {
                highlightSearchedTerm(textView, clickedWord, content, context);
                ((ReadingActivity) context).lookupWords.add(clickedWord);
                ((ReadingActivity) context).updateDialog(word);
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    public static void highlightSearchedTerm(TextView textView, String clickedWord, String content, Context context) {
        Spannable spannable = new SpannableString(content);

        for (int i = -1; (i = content.toLowerCase(Locale.US).indexOf(clickedWord.toLowerCase(Locale.US), i + 1)) != -1; ) {
            int startPos = i;
            int endPos = startPos + clickedWord.length();

            if (startPos != -1) {
                spannable.setSpan(new RoundedBackgroundSpan(context), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        textView.setText(spannable);
    }

    public static void highlightWord(TextView textView, int start, int end, String content, Context context) {
        Spannable spannable = setSpannableText(textView, content, context);
        spannable.setSpan(new RoundedBackgroundSpan(context), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }

    public static void saveLookupHistory(String word, Context context) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = currentUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> lookup = new HashMap<>();
        lookup.put("userID", userID);
        lookup.put("word", word);
        lookup.put("timestamp", Timestamp.now());

        // Add a new document with a generated ID
        db.collection("LookupHistory")
                .add(lookup)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener()    {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
