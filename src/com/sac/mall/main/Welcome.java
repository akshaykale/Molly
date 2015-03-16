package com.sac.mall.main;

import com.sac.mall.R;
import com.sac.mall.administrator.Administrator;
import com.sac.mall.libraries.ParallaxViewPager;
import com.sac.mall.shopper.MallList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class Welcome extends FragmentActivity{

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099cc")));
		setContentView(R.layout.welcome);

		SharedPreferences sp = getSharedPreferences("com.sac.file", MODE_PRIVATE);
		if(sp.getInt("islogedin", 0)==1){
			startActivity(new Intent(getApplicationContext(),MallList.class));
			finish();
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
        final ParallaxViewPager parallaxViewPager = ((ParallaxViewPager) findViewById(R.id.parallaxviewpager));
        parallaxViewPager
            .setOverlapPercentage(0.5f)
            .setAdapter(new MyAdapter(fragmentManager));

        parallaxViewPager.setPageTransformer(false, new PageTransformer() {
    		
    		@Override
    		public void transformPage(View view, float position) {
    			int pageWidth = view.getWidth();
    			
    			/*ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7;
    			iv1 = (ImageView) findViewById(R.id.i1);
    			iv2 = (ImageView) findViewById(R.id.i2);
    			iv3 = (ImageView) findViewById(R.id.i3);
    			iv4 = (ImageView) findViewById(R.id.i4);
    			iv5 = (ImageView) findViewById(R.id.i5);
    			iv6 = (ImageView) findViewById(R.id.i6);
    			iv7 = (ImageView) findViewById(R.id.i7);
     	       
    			//ImageView img = (ImageView) findViewById(R.id.imgadobe);
    	        //TextView tv1 = (TextView) findViewById(R.id.textView1);
    		    if (position < -1) { // [-Infinity,-1)
    		        // This page is way off-screen to the left.
    		        view.setAlpha(0);
    		 
    		    } else if (position <= 1) { // [-1,1]
    		          
    		    	//img.setTranslationX((float) (-(1 - position) * 0.5 * pageWidth));
    				
    				//iv2.setTranslationX((float) (-(1 - position) * pageWidth));
    				 
    				iv6.setTranslationX((position) * (pageWidth / 2));
    		 		iv7.setTranslationX(-(position) * (pageWidth / 2));
    				iv1.setTranslationX((position) * (pageWidth / 2));
    				iv2.setTranslationX(-(position) * (pageWidth / 2));
    				iv3.setTranslationX((position) * (pageWidth / 4));
    				iv5.setTranslationX(-(position) * (pageWidth / 4));
    		 
    						  
    				  
    		    } else { // (1,+Infinity]
    		        view.setAlpha(0);
    		    }*/
    		}
           	});
        

    

	}

	
	
	
	
class MyAdapter extends FragmentPagerAdapter {

	public MyAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = null;
		
		if(arg0==0){
			fragment = new FragmentA();
		}if(arg0==1){
			fragment = new FragmentB();
		}if(arg0==2){
			fragment = new FragmentC();
		}
		
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}

}
