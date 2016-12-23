package com.example.administrator.instantaneousbeiapp.homepage.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.instantaneousbeiapp.R;
import com.example.administrator.instantaneousbeiapp.adapter.mMoreAdapter;
import com.example.administrator.instantaneousbeiapp.login.MoreData;
import com.example.administrator.instantaneousbeiapp.login.SuggestionActivity;
import com.example.administrator.instantaneousbeiapp.menu.MenuSet;
import com.example.administrator.instantaneousbeiapp.menu.RespectInsranraneoous;
import com.example.administrator.instantaneousbeiapp.menu.UseAccount;
import com.example.administrator.instantaneousbeiapp.register.Derive;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/26.
 */
public class MoreFragment extends Fragment {
    ImageView imageView;
    TextView userName;
    ListView userlist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_more, null);
        imageView = (ImageView) view.findViewById(R.id.more_img);
        userName = (TextView) view.findViewById(R.id.more_user_name);
        userlist = (ListView) view.findViewById(R.id.more_user_list);

        LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
        View view1 = layoutinflater.inflate(R.layout.layout_more_list_top,null);
        userlist.addHeaderView(view1);

        ArrayList<MoreData> list = getUserListData();

        mMoreAdapter mMoreAdapter = new mMoreAdapter(getActivity(),list);
        userlist.setAdapter(mMoreAdapter);

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 1:
                        //跳用户资料
                        intent = new Intent(getActivity(), UseAccount.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //跳设置
                        intent = new Intent(getActivity(), MenuSet.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳意见反馈
                        intent = new Intent(getActivity(), SuggestionActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        //跳数据导出
                        intent = new Intent(getActivity(), Derive.class);
                        startActivity(intent);
                        break;
                    case 5:
                        //跳关于我们
                        intent = new Intent(getActivity(), RespectInsranraneoous.class);
                        startActivity(intent);
                        break;

                }
            }
        });
        return view;
    }


    public  ArrayList<MoreData> getUserListData(){
        ArrayList<MoreData> list = new ArrayList<MoreData>();
        //数据库没建立之前的死数据
        String[] strings = {"个人资料","个人设置","意见反馈","数据导出","关于我们"};
        for (int i = 0 ; i < strings.length ; i++){
            MoreData data = new MoreData();
            //data.setUrl("");
            data.setContent(""+strings[i]);
            list.add(data);
        }
        return list;
    }


    public static String post(String url, Map<String, String> params, Map<String, File> files)
            throws IOException {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";

        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(10 * 1000); // 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);


        // 首先组拼文本类型的参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }

        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(sb.toString().getBytes());
        // 发送文件数据
        if (files != null)
            for (Map.Entry<String, File> file : files.entrySet()) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                        + file.getValue().getName() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());


                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                outStream.write(LINEND.getBytes());
            }

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();
        // 得到响应码
        int res = conn.getResponseCode();
        InputStream in = conn.getInputStream();
        StringBuilder sb2 = new StringBuilder();
        if (res == 200) {
            int ch;
            while ((ch = in.read()) != -1) {
                sb2.append((char) ch);
            }
        }
        outStream.close();
        conn.disconnect();
        return sb2.toString();
    }

}
