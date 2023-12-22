package com.appscraftbd.needasocal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CodeIdentifyANDaction {
    SpannableString spannableString;
    String spspostText;
    Context context;

    public  void inANDout(Context context,String spostText, TextView postText_id){

        this.spspostText = spostText;
        this.context = context;

        ArrayList<Integer> array = new ArrayList<Integer>();
        spannableString = new SpannableString(spostText);

        tag_identify(spostText,array);
        tag_setColor_setButton(array);

        postText_id.setHighlightColor(Color.TRANSPARENT);
        postText_id.setText(spannableString);
        postText_id.setMovementMethod(LinkMovementMethod.getInstance());


    }
    ////////////////////////////////////////////////////
    ///!!!!!!!!!!!!!  Method create  !!!!!!!!!!!!!!!!///
    ////////////////////////////////////////////////////
    private void tag_identify(String s_text, ArrayList array){


        for (int x = 0; x < s_text.length(); x++) {

            try {
                if (s_text.charAt(x) == new Character('-')
                        && s_text.charAt(x + 1) == new Character('-')
                        && s_text.charAt(x + 2) == new Character('-')
                        && s_text.charAt(x + 3) == new Character('>')) {


                    array.add(x);

                }

            } catch (RuntimeException R) {

            }
        }

    }//--------------------tag identify----------------


    private void tag_setColor_setButton(ArrayList array){

        int arraycount = array.size()/2;
        for (int z=0; z<arraycount; z++){

            int c1 = 2*z;

            if(z<1){
                int f1 = (int) array.get(z);
                int f2 = (int) array.get(z+1);
                int f3 = f2 + 4;

                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {

                        NEED_code_copy(f1,f2);
                        widget.invalidate();
                        


                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.GRAY);
                        ds.setUnderlineText(false);
                        ds.setTypeface(Typeface.create(spspostText, Typeface.ITALIC));

                                
                    }


                };


                spannableString.setSpan(clickableSpan, f1-1, f3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);



            }else {// z==1++ same work
                int f1 = (int) array.get(c1);
                int f2 = (int) array.get(c1+1);
                int f3 = f2 + 4;

                ClickableSpan clickableSpan = new ClickableSpan() {

                    @Override
                    public void onClick(View widget) {

                        NEED_code_copy(f1,f2);
                        widget.invalidate();

                    }
                    @SuppressLint("NewApi")
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.GRAY);
                        ds.setUnderlineText(false);

                        ds.setTypeface(Typeface.create(spspostText, Typeface.ITALIC));
//                        ds.setTypeface(Typeface.create(spspostText, Typeface.BOLD));


                    }
                };
                spannableString.setSpan(clickableSpan, f1-1, f3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }


    }//------------tag_setColor_setButton-------------
    public void NEED_code_copy(int f1, int f2){
        String tampString = "";
        for (f1=f1+5; f1<f2-1; f1++){
               tampString = tampString+spspostText.charAt(f1);


        }
//            new AlertDialog.Builder(context)
//                    .setMessage(""+tampString)
//                    .show();
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.code_dialog_box);
        TextView textView = dialog.findViewById(R.id.dialogmsg);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        textView.setText(""+tampString);
        dialog.show();

        String finalTampString = tampString;
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(finalTampString);
                Toast.makeText(context,"copy",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }


}
