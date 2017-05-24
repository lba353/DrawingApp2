package com.example.student.drawingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CanvasView myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCanvas = (CanvasView) findViewById(R.id.canvasview);
    }

    public void clearCanvas(View view) {
        myCanvas.clearCanvas();
    }
}
