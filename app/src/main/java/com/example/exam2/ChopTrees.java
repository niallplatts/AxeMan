package com.example.exam2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.exam2.R.raw.treefalling;

// The ChopTrees activity should have the following...
// When the tree is clicked:
//   play audio [5 marks]
//   rotate the image 90-degrees (make it look like it is falling!) [5 marks]
//           remember... trees don't spin aroun the middle when chopped
// increment and save "chopped trees" counter [5 marks]
// shortly after the tree has fallen and the sound finishes, (timer or trigger) [5 marks]
//      clear the tree,
//      set up a new tree.

public class ChopTrees extends AppCompatActivity {

    ImageView treeImageView;
    MediaPlayer mp;
    TextView tv;
    int count;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chop_trees);

        treeImageView = findViewById(R.id.treeImageView);
        MediaPlayer mp = MediaPlayer.create(this, treefalling);
        tv = findViewById(R.id.counterTextView);
        button = findViewById(R.id.reset);

        treeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                RotateAnimation ra = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(500);
                ra.setInterpolator(new LinearInterpolator());
                treeImageView.startAnimation(ra);
                treeImageView.setVisibility(View.INVISIBLE);
                Toast.makeText(ChopTrees.this, "Please Wait for new tree...", Toast.LENGTH_SHORT).show();
                treeImageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        treeImageView.setVisibility(View.VISIBLE);
                    }
                }, 3000);
                if (count == 1){
                    tv.setText(count + " Tree cut down");
                } else {
                    tv.setText(count + " Trees cut down");
                }
            }
        });
       // reset button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                treeImageView.setVisibility(View.VISIBLE);
                count = 0;
                Toast toast = Toast.makeText(getApplicationContext(), "Trees have been reset", Toast.LENGTH_SHORT);
                toast.show();
                treeImageView.setVisibility(View.VISIBLE);
                tv.setText(count + " Tree cut down");
                }
        });
        }
    }






             // need any helper functions? put them here

