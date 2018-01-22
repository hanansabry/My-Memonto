package Utilites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.View;

import learning.memento.toto.my_memonto.R;

/**
 * Created by Hanan on 1/22/2018.
 */

public abstract class SimpleCallbackTouchHelper extends ItemTouchHelper.SimpleCallback {
    public static final float ALPHA_FULL = 1.0f;

    public SimpleCallbackTouchHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            // Get RecyclerView item from the ViewHolder
//            View itemView = viewHolder.itemView;
//
//            Paint p = new Paint();
//            Bitmap icon;
//
//            if (dX > 0) {
//            /* Note, ApplicationManager is a helper class I created
//               myself to get a context outside an Activity class -
//               feel free to use your own method */
//
//                icon = BitmapFactory.decodeResource(
//                        context.getResources(), android.R.drawable.ic_menu_delete);
//
//            /* Set your color for positive displacement */
//                p.setARGB(255, 255, 0, 0);
//
//                // Draw Rect with varying right side, equal to displacement dX
//                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
//                        (float) itemView.getBottom(), p);
//
//                // Set the image icon for Right swipe
//                c.drawBitmap(icon,
//                        (float) itemView.getLeft() + convertDpToPx(16),
//                        (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2,
//                        p);
//            }
//            else {
//                icon = BitmapFactory.decodeResource(
//                        context.getResources(), android.R.drawable.arrow_down_float);
//
//            /* Set your color for negative displacement */
//                p.setARGB(255, 0, 255, 0);
//
//                // Draw Rect with varying left side, equal to the item's right side
//                // plus negative displacement dX
//                c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
//                        (float) itemView.getRight(), (float) itemView.getBottom(), p);
//
//                //Set the image icon for Left swipe
//                c.drawBitmap(icon,
//                        (float) itemView.getRight() - convertDpToPx(16) - icon.getWidth(),
//                        (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2, p);
//            }
//
//            // Fade out the view as it is swiped out of the parent's bounds
//            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(alpha);
//            viewHolder.itemView.setTranslationX(dX);
//
//        } else {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//    }

//    private int convertDpToPx(int dp) {
//        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
//    }
}
