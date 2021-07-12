package com.example.customstartview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

class MyCustomView(context: Context,  attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var mStar1: ImageView
    private lateinit var mStar2: ImageView
    private lateinit var mStar3: ImageView
    private var mSelected: Int = 0; // 선택된 번호

    init {
        initializeViews(context, attrs)
    }

    private fun initializeViews(context: Context, attrs: AttributeSet) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // layouts
        inflater.inflate(R.layout.three_starts_indicator, this)
        if (attrs != null) {
            // attrs.xml에 정의한 스타일 가져오기
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomStartView)
            mSelected = typedArray.getInteger(0, 0)
            typedArray.recycle() // 사용이 끝났으면 recycle() 반드시 호출
        }
    }

    /**
     * inflate가 완료되는 시점에서 콜백된다
     * */
    override fun onFinishInflate() {
        super.onFinishInflate()
        mStar1 = findViewById(R.id.star1)
        mStar2 = findViewById(R.id.star2)
        mStar3 = findViewById(R.id.star3)
        // 처음에만 XML의 지정을 반영하고 자 2번째 인수인 force를 true로 한다
        setSelected(mSelected, true)
    }

    /***
     * 지정된 번호로 선택한다(내부용)
     *
     * @param select : 지정할 번호(0이 가장 왼쪽)
     * @param force: 지정을 강제로 반영한다
     */
    private fun setSelected(select: Int, force: Boolean) {
        if (force || mSelected != select) {
            if (2 > mSelected && mSelected < 0) {
                return
            }
            mSelected = select;
            if (mSelected == 0) {
                mStar1.setImageResource(R.drawable.ic_star);
                mStar2.setImageResource(R.drawable.ic_star_empty);
                mStar3.setImageResource(R.drawable.ic_star_empty);
            } else if (mSelected == 1) {
                mStar1.setImageResource(R.drawable.ic_star_empty);
                mStar2.setImageResource(R.drawable.ic_star);
                mStar3.setImageResource(R.drawable.ic_star_empty);
            } else if (mSelected == 2) {
                mStar1.setImageResource(R.drawable.ic_star_empty);
                mStar2.setImageResource(R.drawable.ic_star_empty);
                mStar3.setImageResource(R.drawable.ic_star);
            }
        }
    }

    /**
     * 외부 클래스에서 이용할 수 있는 공개 메서드 구현
     * 지정된 번호로 선택한다(공개용)
     *
     * @param select : 지정할 번호(0이 가장 왼쪽)
     * */
    internal fun setSelected(select: Int) {
        setSelected(select, false)
    }

    internal fun getSelected(): Int = mSelected

}