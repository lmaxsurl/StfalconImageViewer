package com.stfalcon.imageviewer.listeners;

import android.widget.ImageView;

public interface TargetViewProvider {
    ImageView getTargetView(int position);
}
