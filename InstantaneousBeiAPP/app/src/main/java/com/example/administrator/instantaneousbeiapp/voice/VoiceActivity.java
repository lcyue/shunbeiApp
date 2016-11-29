package com.example.administrator.instantaneousbeiapp.voice;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONObject;

import java.util.List;


/**
 * Created by Administrator on 2016/10/20.
 */
public class VoiceActivity extends Activity {
    ImageView yuyin_btn;
    LinearLayout yuyin_linearlayout;
    ImageView voice;
    ImageView returnBtton;
    EditText contentEt;
    SpeechRecognizer mIat;
    //听写结果字符串
    String dictationResultStr = "[";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shunbei_voice_layout);

        //初始化即创建语音配置对象，只有初始化后才可以使用MSC的各项服务
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=583cdd15");

        voice = (ImageView) findViewById(R.id.voice_btn);
        contentEt = (EditText) findViewById(R.id.contentEt);
        yuyin_linearlayout = (LinearLayout) findViewById(R.id.yuyin_linearlayout);
        returnBtton = (ImageView) findViewById(R.id.return_btton);

        returnBtton.setOnClickListener(onClickListener);
        voice.setOnClickListener(onClickListener);

    }

    //听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener(){

        //音量值0~30
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }
        //开始录音
        @Override
        public void onBeginOfSpeech() {

        }
        //结束录音
        @Override
        public void onEndOfSpeech() {

        }
        //isLast等于true时会话结束。
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean isLast) {
            Log.d("Result:",recognizerResult.getResultString ());
        }
        //会话发生错误回调接口
        @Override
        public void onError(SpeechError speechError) {
            speechError.getPlainDescription(true); //获取错误码描述
        }
        //扩展用接口
        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle bundle) {

        }

        //听写结果回调接口(返回Json格式结果，用户可参见附录12.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见MscDemo中JsonParser类；
        //isLast等于true时会话结束。

    };


    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.return_btton:
                    finish();
                    break;
                case R.id.voice_btn:
                    Log.i("=========","语音按钮");
                    //1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
                    mIat= SpeechRecognizer.createRecognizer(VoiceActivity.this, null);
                    //交互动画
                    RecognizerDialog iatDialog = new RecognizerDialog(VoiceActivity.this,null);

                    //2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
                    mIat.setParameter(SpeechConstant.DOMAIN, "iat");//domain:域名
                    mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
                    mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");//mandarin：普通话
                    //3.开始听写
                    iatDialog.setListener(new RecognizerDialogListener() {
                        @Override
                        public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                            if (!isLast){
                                dictationResultStr += recognizerResult.getResultString()+",";
                            }else {
                                dictationResultStr += recognizerResult.getResultString()+"]";
                            }
                            if (isLast){
                                //解析Json列表字符串
                                JSONObject jsonObject = new JSONObject();
                                Gson gson =new Gson();
                                List<DictationResult> dictationResults = gson.fromJson(dictationResultStr,
                                        new TypeToken<List<DictationResult>>(){}.getType());

                                String finalResult = "";

                                for (int i = 0; i<dictationResults.size()-1;i++){
                                    finalResult += dictationResults.get(i).toString();
                                }
                                contentEt.setText(finalResult);
                                //获取焦点
                                contentEt.requestFocus();
                                //将光标定位到文字最后，以便修改
                                contentEt.setSelection(finalResult.length());
                            }
                        }

                        @Override
                        public void onError(SpeechError speechError) {
                            speechError.getPlainDescription(true);
                        }
                    });
                    iatDialog.show();
            }
        }
    };

}
