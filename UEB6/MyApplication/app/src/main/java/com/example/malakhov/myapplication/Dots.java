package com.example.malakhov.myapplication;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by malakhov on 19.11.2015.
 */
public class Dots {


    public interface DotsChangeListener{
        void onDotsChanged(Dots dots);
    }

    private final LinkedList<Dot> dotses = new LinkedList<>();
    private final List<Dot> safeDots = Collections.unmodifiableList(dotses);

    private DotsChangeListener dotsChangeListener;

    public void setDotsChangeListener(DotsChangeListener listener){
        dotsChangeListener = listener;
    }

    public Dot getLastDot(){
        return (dotses.size() <= 0) ? null : dotses.getLast();
    }

    public List<Dot> getSafeDots(){
        return safeDots;
    }

    public void addDot(float x, float y, int color, int diamet){
        dotses.add(new Dot(x,y,color,diamet));
        notifyListener();
    }

    public void clearDots(){
        dotses.clear();
        notifyListener();
    }
    private void notifyListener() {
        if (null != dotsChangeListener){
            dotsChangeListener.onDotsChanged(this);
        }
    }
}
