package com.android.jesse.huitao.view.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.android.jesse.huitao.R;
import com.android.jesse.huitao.model.Constant;
import com.android.jesse.huitao.utils.DialogUtil;
import com.android.jesse.huitao.utils.ScreenManager;
import com.android.jesse.huitao.utils.ToastUtil;
import com.android.jesse.huitao.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 加福利群页面
 * @author: zhangshihao
 * @date: 2019/11/8 0008
 */
public class ChatGroupActivity extends BaseActivity {

    private static final String TAG = ChatGroupActivity.class.getSimpleName();

    @BindView(R.id.tv_title)
    TextView tv_title;

    private Dialog wxDialog;

    @Override
    protected int getLayout() {
        return R.layout.chat_group_activity;
    }

    @Override
    protected void initEventAndData() {
        ScreenManager.getInstance().setDeepStatusBar(true,mContext);

        tv_title.setText("加入福利群");
    }

    @OnClick({R.id.ll_join_wx,R.id.ll_join_qq,R.id.iv_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_join_wx:
                gotoWechat();
                break;
            case R.id.ll_join_qq:
                joinQQGroup("RvY2v0z-yEhqx7OmOjChLXbEnzCkP4fk");
                break;
        }
    }

    /**
     * 跳转到微信
     */
    private void gotoWechat(){
        try {
            wxDialog = DialogUtil.showHintDialogForCommonVersion(mContext, "点击“现在就去”会复制客服微信号，跳转到微信，添加客服微信后，让客服拉您进福利群~",
                    "现在就去", "稍后", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()){
                                case R.id.tv_positive:
                                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clipData = ClipData.newPlainText(Constant.SERVICE_WECHAT,Constant.SERVICE_WECHAT);
                                    clipboardManager.setPrimaryClip(clipData);
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setComponent(cmp);
                                    startActivity(intent);
                                    wxDialog.dismiss();
                                    break;
                                case R.id.tv_negative:
                                    wxDialog.dismiss();
                                    break;
                            }
                        }
                    });
        } catch (ActivityNotFoundException e) {
            ToastUtil.show("请客官先安装微信噢~");
        }
    }

    /****************
     *
     * 发起添加群流程。群号：惠省淘会员福利群(941130702) 的 key 为： RvY2v0z-yEhqx7OmOjChLXbEnzCkP4fk
     * 调用 joinQQGroup(RvY2v0z-yEhqx7OmOjChLXbEnzCkP4fk) 即可发起手Q客户端申请加群 惠省淘会员福利群(941130702)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }


}
