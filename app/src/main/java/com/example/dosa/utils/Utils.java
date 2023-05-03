package com.example.dosa.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.Word;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.ui.Activity.ReadingActivity;
import com.example.dosa.ui.Adapter.AdapterTranslation;
import com.example.dosa.ui.Fragment.FragmentTraTuDecription;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
}
