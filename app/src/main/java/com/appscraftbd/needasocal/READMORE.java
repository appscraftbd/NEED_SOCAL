package com.appscraftbd.needasocal;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

public class READMORE {

    private boolean isExpanded = false;
    String text_text;
    Context context;
    public static TextView post_text;
    public static TextView readmore;

    CodeIdentifyANDaction codeIdentifyANDaction = new CodeIdentifyANDaction();


    public void Readmore(Context context, String text, TextView post_text, TextView readmore){

        this.text_text=text;
        this.context = context;

        this.post_text=post_text;
        this.readmore=readmore;



        codeIdentifyANDaction.inANDout(context,text,post_text,readmore);


        post_text.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                post_text.getViewTreeObserver().removeOnPreDrawListener(this);
                if (post_text.getLineCount() > post_text.getMaxLines()) {
                    readmore.setVisibility(View.VISIBLE);
                    readmore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggleReadMore(post_text,readmore);
                        }
                    });
                }else {
                    post_text.setTextIsSelectable(true);
                }
                return true;

            }
        });



    }
    public void toggleReadMore(TextView post_text,TextView readmore) {
        if (isExpanded) {
            post_text.setMaxLines(5); // Change the number of lines you want to show initially
            readmore.setText("Read More");
            codeIdentifyANDaction.inANDout(context,text_text,post_text,readmore);
            post_text.setTextIsSelectable(false);

        } else {
            post_text.setMaxLines(Integer.MAX_VALUE);
            readmore.setText("Read Less");
            codeIdentifyANDaction.inANDout(context,text_text,post_text,readmore);
            post_text.setTextIsSelectable(true);

        }
        isExpanded = !isExpanded;
    }



}
