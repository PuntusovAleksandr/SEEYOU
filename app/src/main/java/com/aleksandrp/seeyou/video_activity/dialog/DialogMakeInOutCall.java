package com.aleksandrp.seeyou.video_activity.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.CommentVideo;

public class DialogMakeInOutCall extends AlertDialog {

    private Context mContext;
    private EditText editText;
    private DialogSendMessage mMessage;
    private CommentVideo mCommentVideo;

    public DialogMakeInOutCall(CommentVideo mCommentVideo, Context mContext, DialogSendMessage mMessage) {
        super(mContext);
        LayoutInflater inflater = getLayoutInflater();
        this.mContext = mContext;
        this.mCommentVideo = mCommentVideo;
        this.mMessage = mMessage;
        View view = inflater.inflate(R.layout.context_make_in_out, null);

        initUi(view);
    }

    private void initUi(View view) {

        Button btOk = (Button) view.findViewById(R.id.bt_ok_count);
        btOk.setOnClickListener(listener);

        Button btCancel = (Button) view.findViewById(R.id.bt_cancel_count);
        btCancel.setVisibility(View.VISIBLE);
        btCancel.setOnClickListener(listener);

         editText = (EditText) view.findViewById(R.id.et_show_message_count);

        this.setView(view);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_ok_count:
                    String text = editText.getText().toString();
                    if (text.length()>0) {
                        mMessage.sendMess(text, mCommentVideo.getRelation());
                        cancel();
                    } else {
                        editText.setHint(R.string.enter_text);
                    }
                    break;
                case R.id.bt_cancel_count:
                    cancel();
                    break;
            }
        }
    };

    public interface DialogSendMessage {
        void sendMess(String text, String mRelation);
    }
}
