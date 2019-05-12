package com.mystiko.smartmeter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    // list of images
    public int[] lst_images = {
            R.drawable.safety,
            R.drawable.outsafe,
            R.drawable.save,
            R.drawable.renew
    };
    // list of titles
    public String[] lst_title = {
            "INDOOR ELECTRICAL SAFETY",
            "OUTDOOR ELECTRICAL SAFETY",
            "LET’S SAVE ELECTRICITY!",
            "INTRODUCTION TO RENEWABLE ENERGY"
    }   ;
    // list of descriptions
    public String[] lst_description = {
                    "Electric appliances should not be used when in contact with water.\n" +
                    "\n" +
                    "All the electrical appliances which are not in use should be unplugged.\n" +
                    "\n" +
                    "An electrical appliance should not be touched with a metal object.\n" +
                    "\n" +
                    "It should be made sure that your hands are dry when using an appliance.,",
                    "Some overhead power lines appear insulated but only have weather protection. These are not safe to be touched. Touching a power line with any part of your body or any object such as ladders, tree trimmers, poles, ropes or kites can result in serious injury or death",
            "CFL is a wonderful idea!\n" +
                    "Clean the bulbs!\n" +
                    "Manage brightness!\n" +
                    "Optimum use of natural light! \n" +
                    "Halogen lamps & motion detectors!\n " +
                    "Right load, water, temperature & detergent on the washing machine!\n" +
                    "Cook with lids on your pans!\n" +
                    "Right care for the refrigerator!\n" +
                    "Iron all clothes at once!",
            "Renewable energy is energy which comes from natural resources such as sunlight, wind, rain, biomass tides, and geothermal heat, which are renewable (naturally replenished).\n" +
                    "About 16% of global final energy consumption comes from renewables, with 10% coming from traditional biomass, which is mainly used for heating, and 3.4% from hydroelectricity"
    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)
    };


    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}