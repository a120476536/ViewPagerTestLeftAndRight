package com.qiao.viewpagertestleftandright;

import java.util.ArrayList;
import java.util.List;

import com.qiao.Fragment.FragmentOne;
import com.qiao.Fragment.FragmentThr;
import com.qiao.Fragment.FragmentTwo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
/**
 * ViewPager判断左右滑动方向。。。。。。
 * @author 有点凉了
 * QQ群：123869487
 * 求基友共同进步，求大神入群指点
 *
 */
public class MainActivity extends FragmentActivity {
	
	private static final String TAG="MainActivity";
	private ViewPager viewPager;
	private List<Fragment> list = null;//
	private boolean isClick = false;
	private static int preSelectedPage = 0;
	private static int scrollState;
	private int position=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		init();
	}

	@SuppressWarnings("unused")
	private void init() {
		list = new ArrayList<Fragment>();
		FragmentOne fragmentOne = new FragmentOne();
		list.add(fragmentOne);
		list.add(new FragmentTwo());
		list.add(new FragmentThr());
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				position = arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				if (arg2!=0) {
					if (scrollState == 1) {//手指按下
//						if(preSelectedPage == arg0){//表示往左拉，相应的tab往右走
//							Log.i(TAG, "ux==--> 手指左滑 整体页面--> ");
//						}else {
//							Log.i(TAG, "ux==--> 手指向右 整体页面<--");
//						}
					}else if (scrollState==2) {
						if(preSelectedPage == arg0){//往左拉
							Log.i(TAG, "ux==--> 手指左滑 整体页面--> 页面向右");
						}else{//表示往右拉
							Log.i(TAG, "ux==--> 手指右滑 整体页面-->  页面向左");
						}
					}
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				if(!isClick){
					scrollState = arg0;
					preSelectedPage = position;
				}
			}
		});
	}

	class MyAdapter extends FragmentPagerAdapter{
		private List<Fragment> list ;
		public MyAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			// TODO Auto-generated constructor stub
			this.list=list;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		
	}


}
