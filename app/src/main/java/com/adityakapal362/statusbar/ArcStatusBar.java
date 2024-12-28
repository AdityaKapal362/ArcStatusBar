package com.adityakapal362.statusbar;

import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.content.Context;
import android.util.TypedValue;
import java.util.ArrayList;

public class ArcStatusBar extends RelativeLayout {
	
	private ArrayList<ArcData> list;
	private ArrayList<RotateData> list2;
	
	private Paint pBar = new Paint();
	private Paint pViewed = new Paint();
	private Paint pCircle = new Paint();
	
	private int pads, saveLayerId;
	private float pad = 5f;
	private float temp, sub;
	
	private int barWidth = 2;
	private int viewedBarWidth = 2;
	
	public ArcStatusBar(Context a) {
		super(a);
		setLayoutParams(new LinearLayout.LayoutParams(getDip(50),getDip(50)));
		pBar.setStyle(Paint.Style.STROKE);
		pBar.setStrokeWidth(getDip(barWidth));
		pBar.setColor(0xFF00BFA5);
		pViewed.setStyle(Paint.Style.STROKE);
		pViewed.setStrokeWidth(getDip(viewedBarWidth));
		pViewed.setColor(0xFFBDBDBD);
		pads = getDip(barWidth);
		setBackgroundColor(Color.TRANSPARENT);
	}
	
	public void setData(ArrayList<ArcData> newData) {
		list = newData;
		list2 = countRotate();
		invalidate();
	}
	
	public void viewBar(int pos) {
		list.get(pos).viewed = true;
		list2 = countRotate();
		invalidate();
	}
	
	public void unviewBar(int pos) {
		list.get(pos).viewed = false;
		list2 = countRotate();
		invalidate();
	}
	
	public boolean isBarViewed(int pos) {
		return list.get(pos).viewed;
	}
	
	public void addBar() {
		addBar(false);
	}
	
	public void addBar(boolean viewed) {
		if (list==null) list = new ArrayList<>();
		list.add(new ArcData(viewed));
		list2 = countRotate();
		invalidate();
	}
	
	public void removeBar(int pos) {
		list.remove(pos);
		list2 = countRotate();
		invalidate();
	}
	
	public void setAllBarViewed(boolean a) {
		for (ArcData w : list) {
			w.viewed = a;
		}
		list2 = countRotate();
		invalidate();
	}
	
	public void setAllBarViewed() {
		setAllBarViewed(true);
	}
	
	public void setAllBarUnviewed() {
		setAllBarViewed(false);
	}
	
	public void setViewedBarColor(int color) {
		pViewed.setColor(color);
		invalidate();
	}
	
	public void setBarColor(int color) {
		pBar.setColor(color);
		invalidate();
	}
	
	public void setBarGap(float gap) {
		pad = gap;
		invalidate();
	}
	
	public void setBarWidth(int width) {
		barWidth = width;
		pBar.setStrokeWidth(getDip(width));
		pads = getDip(width);
		invalidate();
	}
	
	public void setViewedBarWidth(int width) {
		viewedBarWidth = width;
		pViewed.setStrokeWidth(getDip(width));
		invalidate();
	}
	
	public ArrayList<ArcData> getData() {
		return list;
	}
	
	public float getBarGap() {
		return pad;
	}
	
	public int getBarWidth() {
		return barWidth;
	}
	
	public int getViewedBarWidth() {
		return viewedBarWidth;
	}
	
	private ArrayList<RotateData> countRotate() {
		ArrayList<RotateData> xc = new ArrayList<>();
		for (ArcData i : list) {
			xc.add(new RotateData(false, false));
			xc.add(new RotateData(true, i.viewed));
			xc.add(new RotateData(false, false));
		};
		return xc;
	}
	
	protected void onDraw(Canvas c) {
		if (list == null) return;
		if (list.size() == 1) {
			c.drawArc(pads, pads, getWidth()-pads, getHeight()-pads, 0, 360, true, list.get(0).viewed ? pViewed : pBar);
		} else if (list.size() > 1) {
			temp = -90f;
			sub = (360f/list.size()) - (pad*2);
			for (RotateData n : list2) {
				if (n.val) {
					c.drawArc(pads, pads, getWidth()-pads, getHeight()-pads, temp, sub, false, n.viewed ? pViewed : pBar);
					temp += sub;
				} else {
					temp += pad;
				};
			}
		};
		saveLayerId = c.saveLayer(pads, pads, getWidth()-pads, getHeight()-pads, null);
		pCircle = new Paint();
		pCircle.setColor(Color.WHITE);
		pCircle.setStyle(Paint.Style.FILL);
		c.drawCircle(getWidth()/2, getHeight()/2, (getWidth()/2)-pads, pCircle);
		pCircle.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		c.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - pads, pCircle);
		c.restoreToCount(saveLayerId);
	}
	
	private int getDip(int _input) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
}
