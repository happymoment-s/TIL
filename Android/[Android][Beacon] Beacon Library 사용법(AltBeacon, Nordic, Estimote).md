# Beacon Library 사용법

## 1. AltBeacon Library

### 참고 사이트
* [공식사이트 - sample code](http://altbeacon.github.io/android-beacon-library/samples.html)
* [AltBeacon github 링크](https://github.com/AltBeacon/android-beacon-library)

### 코드 적용
* 코드 구현부
```Android
public class MainActivity extends AppCompatActivity implements BeaconConsumer {
    private BeaconManager mBeaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LogManager.setVerboseLoggingEnabled(true);
        mBeaconManager = BeaconManager.getInstanceForApplication(this);
        mBeaconManager.bind(this);
        mBeaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBeaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        mBeaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
            }

            @Override
            public void didExitRegion(Region region) {
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
            }
        });

        mBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {              
            }
        });
        try {
            mBeaconManager.startMonitoringBeaconsInRegion(new Region("test", null, null, null));
            mBeaconManager.startRangingBeaconsInRegion(new Region("test", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
```
* 
*

### 특징
* manifest에서 permission, service 설정할 필요 없음 (aar에 포함되어있음)
* 스캔시 1초마다 didDetermineStateForRegion()에 비콘신호 올라옴
* Nordic 라이브러리와 매우 유사(함수명까지)
* BeaconManager 싱글턴으로 객체 생성후 bind 하면 callback으로 onBeaconServiceConnect() 호출됨
* background 스캔 적용시 Application단에서 구현하면 됨(체크 필요)   
---


## 2. Nordic Library

### 참고 사이트
* [nrf-beacon-lib-v2.0 다운로드 링크](https://github.com/NordicSemiconductor/Android-nRF-Beacon/blob/master/app/libs/nrf-beacon-lib-v2.0.aar)
* [Android-nrf-beacon 샘플소스](https://github.com/NordicSemiconductor/Android-nRF-Beacon)


### 코드 적용

* manifest
```Android
	<uses-permission android:name="android.permission.BLUETOOTH"/>
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

	<service
    	android:name="no.nordicsemi.android.beacon.v21.BeaconLocationService"
        android:exported="false"/>
```

* 코드 구현부
```Android
public class MainActivity extends AppCompatActivity implements BeaconServiceConnection.BeaconsListener, BeaconServiceConnection.RegionListener {

    private boolean mServiceConnected;
    private BeaconServiceConnection mServiceConnection = new BeaconServiceConnection() {
        @Override
        public void onServiceConnected() {
            mServiceConnected = true;
            startRangingBeaconsInRegion(0x004C, BeaconRegion.ANY_UUID, MainActivity.this); // BeaconsListener
            startMonitoringForRegion(0x004C, UUID.fromString("73CB035B-8A0A-47C6-975E-2D24DA2FC927"), 1, 2020, MainActivity.this); // RegionListener 
        }

        @Override
        public void onServiceDisconnected() {
            mServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean started = ServiceProxy.bindService(getApplicationContext(), mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ServiceProxy.unbindService(getApplicationContext(), mServiceConnection);
    }

    @Override
    public void onBeaconsInRegion(Beacon[] beacons, BeaconRegion beaconRegion) {
		// 2초마다 callback
    }

    @Override
    public void onEnterRegion(BeaconRegion beaconRegion) {
    }

    @Override
    public void onExitRegion(BeaconRegion beaconRegion) {
    }
```
* bindService로 Service 시작하고, startRangingBeaconsInRegion(), startMonitoringForRegion() 으로 스캐닝 설정 가능
* 이유는 모르겠으나 startRangingBeaconsInRegion() 함수는 동작하는데 startMonitoringForRegion() 함수는 동작하지 않음.. 
### 특징
* Foreground에서만 지원, Background, boot complete 지원하지 않음
* 서비스 정상 시작되면 앱 상단에 "Beacons Service is running" 노티바 생성됨
* permission aar 파일에서 제공하지 않음
* 스캔시 2초마다 수신한 스캔정보를 배열로 내려줌

---
## 3. Estimote Library
### 참고 사이트
* [Estimote 공식 사이트](http://developer.estimote.com/android/tutorial/part-1-setting-up/#add-estimote-sdk)
* [Sample source githup site](https://github.com/Estimote/Android-SDK/releases)
* [aar download site](https://jcenter.bintray.com/com/estimote/sdk/)

### 코드 적용
```Android
public class MainActivity extends AppCompatActivity {
    BeaconManager mBeaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBeaconManager = new BeaconManager(this);
        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                mBeaconManager.startRanging(new Region("ranged region", UUID.fromString("73CB035B-8A0A-47C6-975E-2D24DA2FC927"), null, null));
            }
        });
        mBeaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
            }
        });
    }
}
```

### 특징
* manifest에서 permission, service 설정할 필요 없음 (aar에 포함되어있음)
* BeaconManager 객체 생성후(싱글턴 아님) connect() 호출시 callback으로 onServiceReady() 호출됨
* setRanging() 설정 후 startRanging() 호출시 1초마다 onBeaconsDiscovered() 콜백 호출됨
* background 스캔 가능하며, Applicaton쪽에서 가이드 해주고 있음
