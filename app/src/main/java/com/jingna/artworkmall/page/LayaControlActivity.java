package com.jingna.artworkmall.page;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jingna.artworkmall.R;
import com.jingna.artworkmall.base.BaseActivity;
import com.jingna.artworkmall.dialog.DialogCustom;
import com.jingna.artworkmall.service.BluetoothLeService;
import com.jingna.artworkmall.util.Logger;
import com.jingna.artworkmall.util.StatusBarUtils;
import com.jingna.artworkmall.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayaControlActivity extends BaseActivity {

    private Context context = LayaControlActivity.this;

    @BindView(R.id.tv_state)
    TextView tvState;

    private final static String TAG = LayaControlActivity.class.getSimpleName();
    //蓝牙4.0的UUID,其中0000ffe1-0000-1000-8000-00805f9b34fb是广州汇承信息科技有限公司08蓝牙模块的UUID
    public static String HEART_RATE_MEASUREMENT = "0000ffe1-0000-1000-8000-00805f9b34fb";
    public static String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    public static String EXTRAS_DEVICE_RSSI = "RSSI";
    //蓝牙连接状态
    private boolean mConnected = false;
    private String status = "disconnected";
    //蓝牙名字
    private String mDeviceName;
    //蓝牙地址
    private String mDeviceAddress;
    //蓝牙信号值
    private String mRssi;
    private Bundle b;
    private String rev_str = "";
    //蓝牙service,负责后台的蓝牙服务
    private static BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    //蓝牙特征值
    private static BluetoothGattCharacteristic target_chara = null;
    private Handler mhandler = new Handler();
    private Handler myHandler = new Handler() {
        // 2.重写消息处理函数
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 判断发送的消息
                case 1: {
                    // 更新View
                    String state = msg.getData().getString("connect_state");
                    if(state.equals("connected")){
                        tvState.setText("蓝牙已连接");
                    }
                    break;
                }

            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laya_control);

        StatusBarUtils.setStatusBar(LayaControlActivity.this, getResources().getColor(R.color.theme));
        ButterKnife.bind(LayaControlActivity.this);
        initData();

        b = getIntent().getExtras();
        //从意图获取显示的蓝牙信息
        mDeviceName = b.getString(EXTRAS_DEVICE_NAME);
        mDeviceAddress = b.getString(EXTRAS_DEVICE_ADDRESS);
        mRssi = b.getString(EXTRAS_DEVICE_RSSI);

		/* 启动蓝牙service */
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

    }

    private void initData() {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除广播接收器
        unregisterReceiver(mGattUpdateReceiver);
        mBluetoothLeService = null;
    }

    // Activity出来时候，绑定广播接收器，监听蓝牙连接服务传过来的事件
    @Override
    protected void onResume() {
        super.onResume();
        //绑定广播接收器
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            //根据蓝牙地址，建立连接
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    /* BluetoothLeService绑定的回调函数 */
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
                    .getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up
            // initialization.
            // 根据蓝牙地址，连接设备
            mBluetoothLeService.connect(mDeviceAddress);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }

    };

    /**
     * 广播接收器，负责接收BluetoothLeService类发送的数据
     */
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action))//Gatt连接成功
            {
                mConnected = true;
                status = "connected";
                //更新连接状态
                updateConnectionState(status);
                System.out.println("BroadcastReceiver :" + "device connected");

            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED//Gatt连接失败
                    .equals(action)) {
                mConnected = false;
                status = "disconnected";
                //更新连接状态
                updateConnectionState(status);
                System.out.println("BroadcastReceiver :"
                        + "device disconnected");

            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED//发现GATT服务器
                    .equals(action)) {
                // Show all the supported services and characteristics on the
                // user interface.
                //获取设备的所有蓝牙服务
                displayGattServices(mBluetoothLeService
                        .getSupportedGattServices());
                System.out.println("BroadcastReceiver :"
                        + "device SERVICES_DISCOVERED");
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action))//有效数据
            {
                //处理发送过来的数据
                displayData(intent.getExtras().getString(
                        BluetoothLeService.EXTRA_DATA));
                System.out.println("BroadcastReceiver onData:"
                        + intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };

    /* 更新连接状态 */
    private void updateConnectionState(String status) {
        Message msg = new Message();
        msg.what = 1;
        Bundle b = new Bundle();
        b.putString("connect_state", status);
        Logger.e("123123", status);
        msg.setData(b);
        //将连接状态更新的UI的textview上
        myHandler.sendMessage(msg);
        System.out.println("connect_state:" + status);

    }

    /* 意图过滤器 */
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter
                .addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    /**
     * @param @param rev_string(接受的数据)
     * @return void
     * @throws
     * @Title: displayData
     * @Description: TODO(接收到的数据在scrollview上显示)
     */
    private void displayData(String rev_string) {
        Logger.e("123123", rev_string);
//        rev_str += rev_string;
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                rev_tv.setText(rev_str);
//                rev_sv.scrollTo(0, rev_tv.getMeasuredHeight());
//                System.out.println("rev:" + rev_str);
//            }
//        });

    }

    /**
     * @return void
     * @throws
     * @Title: displayGattServices
     * @Description: TODO(处理蓝牙服务)
     */
    private void displayGattServices(List<BluetoothGattService> gattServices) {

        if (gattServices == null)
            return;
        String uuid = null;
        String unknownServiceString = "unknown_service";
        String unknownCharaString = "unknown_characteristic";

        // 服务数据,可扩展下拉列表的第一级数据
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();

        // 特征数据（隶属于某一级服务下面的特征值集合）
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<ArrayList<HashMap<String, String>>>();

        // 部分层次，所有特征值集合
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {

            // 获取服务列表
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();

            // 查表，根据该uuid获取对应的服务名称。SampleGattAttributes这个表需要自定义。

            gattServiceData.add(currentServiceData);

            System.out.println("Service uuid:" + uuid);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<HashMap<String, String>>();

            // 从当前循环所指向的服务中读取特征值列表
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();

            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            // 对于当前循环所指向的服务中的每一个特征值
            for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();

                if (gattCharacteristic.getUuid().toString()
                        .equals(HEART_RATE_MEASUREMENT)) {
                    // 测试读取当前Characteristic数据，会触发mOnDataAvailable.onCharacteristicRead()
                    mhandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mBluetoothLeService
                                    .readCharacteristic(gattCharacteristic);
                        }
                    }, 200);

                    // 接受Characteristic被写的通知,收到蓝牙模块的数据后会触发mOnDataAvailable.onCharacteristicWrite()
                    mBluetoothLeService.setCharacteristicNotification(
                            gattCharacteristic, true);
                    target_chara = gattCharacteristic;
                    // 设置数据内容
                    // 往蓝牙模块写入数据
                    // mBluetoothLeService.writeCharacteristic(gattCharacteristic);
                }
                List<BluetoothGattDescriptor> descriptors = gattCharacteristic
                        .getDescriptors();
                for (BluetoothGattDescriptor descriptor : descriptors) {
                    System.out.println("---descriptor UUID:"
                            + descriptor.getUuid());
                    // 获取特征值的描述
                    mBluetoothLeService.getCharacteristicDescriptor(descriptor);
                    // mBluetoothLeService.setCharacteristicNotification(gattCharacteristic,
                    // true);
                }

                gattCharacteristicGroupData.add(currentCharaData);
            }
            // 按先后顺序，分层次放入特征值集合中，只有特征值
            mGattCharacteristics.add(charas);
            // 构件第二级扩展列表（服务下面的特征值）
            gattCharacteristicData.add(gattCharacteristicGroupData);

        }

    }

    /**
     * 将数据分包
     **/
    public int[] dataSeparate(int len) {
        int[] lens = new int[2];
        lens[0] = len / 20;
        lens[1] = len - 20 * lens[0];
        return lens;
    }

    /*
     * 发送按键的响应事件，主要发送文本框的数据
     */
    @OnClick({R.id.btn_pause, R.id.btn_start1, R.id.btn_start, R.id.ll_top, R.id.ll_bottom, R.id.ll_end})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pause:
                if (Utils.isFastClick()) {
                    DialogCustom dialogCustom = new DialogCustom(context, "是否暂停", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            byte buff[] = {(byte) 0xDF, 0x12, 0x00, 0x14, (byte) 0xFD};
                            target_chara.setValue(buff);//只能一次发送20字节，所以这里要分包发送
                            //调用蓝牙服务的写特征值方法实现发送数据
                            mBluetoothLeService.writeCharacteristic(target_chara);
                        }
                    });
                    dialogCustom.show();
                } else {
                    Toast.makeText(this, "点击过快", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_start1:
                if (Utils.isFastClick()) {
                    DialogCustom dialogCustom = new DialogCustom(context, "是否开始连接", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            byte buff[] = {(byte) 0xDF, 0x11, 0x00, (byte) 0xB4, (byte) 0xFD};
                            target_chara.setValue(buff);//只能一次发送20字节，所以这里要分包发送
                            //调用蓝牙服务的写特征值方法实现发送数据
                            mBluetoothLeService.writeCharacteristic(target_chara);
                        }
                    });
                    dialogCustom.show();
                } else {
                    Toast.makeText(this, "点击过快", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_start:
                if (Utils.isFastClick()) {
                    DialogCustom dialogCustom = new DialogCustom(context, "是否开始服务", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            byte buff1[] = {(byte) 0xDF, 0x12, 0x00, 0x13, (byte) 0xFD};
                            target_chara.setValue(buff1);//只能一次发送20字节，所以这里要分包发送
                            //调用蓝牙服务的写特征值方法实现发送数据
                            mBluetoothLeService.writeCharacteristic(target_chara);
                        }
                    });
                    dialogCustom.show();
                } else {
                    Toast.makeText(this, "点击过快", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_top:
                if (Utils.isFastClick()) {
                    byte buff[] = {(byte) 0xDF, 0x12, 0x00, 0x11, (byte) 0xFD};
                    target_chara.setValue(buff);//只能一次发送20字节，所以这里要分包发送
                    //调用蓝牙服务的写特征值方法实现发送数据
                    mBluetoothLeService.writeCharacteristic(target_chara);
                } else {
                    Toast.makeText(this, "点击过快", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_bottom:
                if (Utils.isFastClick()) {
                    byte buff[] = {(byte) 0xDF, 0x12, 0x00, 0x12, (byte) 0xFD};
                    target_chara.setValue(buff);//只能一次发送20字节，所以这里要分包发送
                    //调用蓝牙服务的写特征值方法实现发送数据
                    mBluetoothLeService.writeCharacteristic(target_chara);
                } else {
                    Toast.makeText(this, "点击过快", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_end:
                if (Utils.isFastClick()) {
                    DialogCustom dialogCustom = new DialogCustom(context, "是否立即结束服务", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            byte buff[] = {(byte) 0xDF, 0x13, 0x00, 0x11, (byte) 0xFD};
                            target_chara.setValue(buff);//只能一次发送20字节，所以这里要分包发送
                            //调用蓝牙服务的写特征值方法实现发送数据
                            mBluetoothLeService.writeCharacteristic(target_chara);
                        }
                    });
                    dialogCustom.show();
                } else {
                    Toast.makeText(this, "点击过快", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            String hexString = Integer.toHexString(bytes[i] & 0xFF);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            result += hexString.toUpperCase();
        }
        return result;
    }

    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}
