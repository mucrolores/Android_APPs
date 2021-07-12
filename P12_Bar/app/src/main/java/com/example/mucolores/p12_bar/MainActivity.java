package com.example.mucolores.p12_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private RatingBar mRatingBar;
    private TextView mSeekBarText,mRatingBarProgressText,mRatingBarValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
        mRatingBar = (RatingBar)findViewById(R.id.ratingBar);
        mSeekBarText = (TextView)findViewById(R.id.seekBar_text);
        mRatingBarProgressText = (TextView)findViewById(R.id.ratingBar_progress_text);
        mRatingBarValueText = (TextView)findViewById(R.id.ratingBar_value_text);

        mSeekBar.setOnSeekBarChangeListener(seekBarOCL);
        mRatingBar.setOnRatingBarChangeListener(ratingBatOCL);
    }

    private SeekBar.OnSeekBarChangeListener seekBarOCL = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String s = getString(R.string.seek_bar_progress);
            mSeekBarText.setText(s+String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private RatingBar.OnRatingBarChangeListener ratingBatOCL = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String progressString = getString(R.string.seek_bar_progress);
            mRatingBarProgressText.setText(progressString + String.valueOf(rating));

            String vlaueString = getString(R.string.rating_bar_value);
            mRatingBarValueText.setText(vlaueString + mRatingBar.getProgress());
        }
    };
}
