package in.arshad.ui.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class InputBlockingView extends FrameLayout {
  public InputBlockingView(Context context) {
    super(context);
  }

  public InputBlockingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public InputBlockingView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return true;
  }

  @Override
  public boolean onInterceptHoverEvent(MotionEvent event) {
    return true;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return true;
  }
}
