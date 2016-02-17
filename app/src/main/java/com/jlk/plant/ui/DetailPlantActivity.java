package com.jlk.plant.ui;

import android.view.View;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;


public class DetailPlantActivity extends BaseFragmentActivity {

    private final String tag = "DetailPlantActivity";
    ExpandableTextView expand_text_view;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_plant_detail);
    }

    @Override
    public void initViews() {

        findViewById(R.id.back).setVisibility(View.VISIBLE);
//        expand_text_view = (ExpandableTextView) findViewById(R.id.expand_text_view);
//        expand_text_view.setText("茉莉花（学名：Jasminum sambac (L.) Ait）为木樨科馨属花卉，别名：茉莉、香魂、莫利花、没丽、没利、抹厉、末莉、末利、木梨花等。　　\n" +
//                "　　茉莉花素洁、浓郁、清芬、久远，它的花语表示忠贞、尊敬、清纯、贞洁、质朴、玲珑、迷人。许多国家将其作为爱情之花，青年男女之间，互送茉莉花以表达坚贞爱情。它也作为友谊之花，在人们中间传递。把茉莉花环套在客人颈上使之垂到胸前，表示尊敬与友好，成为一种热情好客的礼节。");
    }

    @Override
    public void initListeners() {
    }


    @Override
    public void initData() {


    }


}
