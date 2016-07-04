# 4.3, 4.4 단말에서 블루투스 서비스 중지 팝업 발생 현상 원인

## 현상
- Android 4.3, 4.4 단말에서 블루투스 서비스가 중지되는 현상 발생 (LG 단말에서 빈번하게 발생)
- ble 스캔을 하는 서비스 앱을 설치하고 사용할 경우 더 빈번하게 발생
- 'Unfortunately, Bluetooth share has stopped' 팝업

![crash error] (http://www.androidpolice.com/wp-content/uploads/2014/05/nexusae0_2014-05-18-16_38_58-Gimbal-Beacons-Breaking-Bluetooth-on-Android-on-Vimeo_thumb1.png)

## 로그
```
06-30 16:47:46.601 889-909/? E/BluetoothManagerService: Unable to call onBluetoothServiceDown() on callback #3
  android.os.DeadObjectException
    at android.os.BinderProxy.transact(Native Method)
    at android.bluetooth.IBluetoothManagerCallback$Stub$Proxy.onBluetoothServiceDown(IBluetoothManagerCallback.java:105)
    at com.android.server.BluetoothManagerService.sendBluetoothServiceDownCallback(BluetoothManagerService.java:595)
    at com.android.server.BluetoothManagerService.access$3600(BluetoothManagerService.java:53)
    at com.android.server.BluetoothManagerService$BluetoothHandler.handleMessage(BluetoothManagerService.java:1053)
    at android.os.Handler.dispatchMessage(Handler.java:102)
    at android.os.Looper.loop(Looper.java:136)
    at android.os.HandlerThread.run(HandlerThread.java:61)


06-30 16:47:53.367 889-899/? I/ActivityManager: Process com.android.bluetooth (pid 28417) has died.
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/.pbap.BluetoothPbapService in 1000ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/com.lge.bluetooth.app.LGBluetoothAPIServer in 1000ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/com.broadcom.bt.service.opp.OppService in 11000ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/com.broadcom.bt.service.ftp.FTPService in 11000ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/.btservice.AdapterService in 10999ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/.a2dp.A2dpService in 20999ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/com.broadcom.bt.service.hfp.BrcmHeadsetService in 20999ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/.gatt.GattService in 20997ms
06-30 16:47:53.367 889-899/? W/ActivityManager: Scheduling restart of crashed service com.android.bluetooth/.pan.PanService in 30997ms
```

## 원인
- 많은 ble를 스캔할 경우 ble장치의 mac address를 저장하는 쪽의 android bluetooth code에서 에러 발생
```java
static cfg_node* find_add_node(cfg_node* p, const char* name)
{
    int i = -1;
    cfg_node* node = NULL;
    if((i = find_inode(p, name)) < 0)
    {
        if(!(node = find_free_node(p)))
        {
            int old_size = alloc_node(p, CFG_GROW_SIZE);
            if(old_size >= 0)
            {
                i = GET_NODE_COUNT(old_size);
                node = &p->child[i];
                ADD_CHILD_COUNT(p, 1);
            } /* else clause to handle failure of alloc_node() is missing here */
        } else ADD_CHILD_COUNT(p, 1);
    }
    else node = &p->child[i];
    if(!node->name)   /* this will SIGSEGV if node is still NULL */
        node->name = strdup(name);
    return node;
}
```

## 해결방안
1. os 업데이트
2. 공장초기화 혹은 ble 저장영역을 clear하는 앱 동작

## 참고사이트
- [stackoverflow bluetooth-share-has-stopped-working-when-performing-lescan] (http://stackoverflow.com/questions/22048721/bluetooth-share-has-stopped-working-when-performing-lescan/22355276#22355276)
- [bug-watch-bluetooth-will-begin-crashing-after-encountering-too-many-ble-devices] (http://www.androidpolice.com/2014/05/20/bug-watch-bluetooth-will-begin-crashing-after-encountering-too-many-ble-devices-affects-kitkat-4-4-and-jelly-bean-4-3/)
- [https://code.google.com/p/android/issues/detail?id=67272] (https://code.google.com/p/android/issues/detail?id=67272)
- [solution] (http://developer.radiusnetworks.com/2014/04/02/a-solution-for-android-bluetooth-crashes.html)
