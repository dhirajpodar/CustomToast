package com.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.Gravity.*;

public class MainActivity extends AppCompatActivity {
  Button showToast;
  TextView messageText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    final View view1 = initView1();

    showToast.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showToast(view1,displayMetrics);
      }
    });
  }

  private View initView1() {
    showToast = findViewById(R.id.btn_toast);
    final View view = LayoutInflater.from(this).inflate(R.layout.custom_toast1, null, false);
    messageText = view.findViewById(R.id.message_text);
    LinearLayout linearLayout = view.findViewById(R.id.ll_toast);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      linearLayout.setBackground(getDrawable(R.drawable.shape));
    }
    String message = "I am the message.";
    messageText.setText(message);
    return view;
  }

  private void showToast(View view, DisplayMetrics displayMetrics) {
    int displayDpi = (int) (displayMetrics.density*160f);
    Toast toast = new Toast(this);
    toast.setView(view);
    int width = displayMetrics.widthPixels;
    Log.d("TAG","Width::"+width);
    Log.d("TAG","y - Off::"+toast.getYOffset());
    Log.d("TAG","x-off::"+toast.getXOffset());
    if (displayDpi < 480) {
      toast.setGravity(Gravity.BOTTOM| LEFT, getXOffsetForToast(width, (float) 0.84), 64);
    } else if (displayDpi < 599) {
      toast.setGravity(Gravity.BOTTOM| LEFT, getXOffsetForToast(width, (float) 0.68), 64);
    } else if (displayDpi >= 600 && displayDpi < 959) {
      toast.setGravity(Gravity.BOTTOM| LEFT, getXOffsetForToast(width, (float) 0.68), 64);
    } else if (displayDpi >= 960 && displayDpi < 1919) {
      toast.setGravity(Gravity.BOTTOM| LEFT, getXOffsetForToast(width, (float) 0.37), 64);
    } else {
      toast.setGravity(Gravity.BOTTOM| LEFT, getXOffsetForToast(width, (float) 0.25), 64);
    }
    toast.show();

  }

  private int getXOffsetForToast(int width, float v) {
    float x = v * width;
    Log.d("TAG","x-off::"+(width-x)/2);
    return (int) ((width-x)/2);
  }
}
