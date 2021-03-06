package com.testrn;

import androidx.annotation.NonNull;

import com.dieam.reactnativepushnotification.modules.RNPushNotificationListenerService;
import com.google.firebase.messaging.RemoteMessage;
import com.sphere.message.SpherePushMessage;

public class MyFirebaseMessagingService extends RNPushNotificationListenerService {

    @Override
    public void onNewToken(@NonNull String token) {
        // Sphere SDK 푸시 토큰 설정
        SpherePushMessage.setFcmToken(token);
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if (SpherePushMessage.isSpherePushMessage(remoteMessage.getData())) {
            remoteMessage.getData().put("sphereMsg","true");
            // Sphere 푸시 메시지 데이터 처리: 앱이 실행 중인 경우 알림창에 메시지 표시
//            SpherePushMessage.handleMessageReceived(remoteMessage.getData());
        } else {
            // Sphere 푸시 메시지가 아닌 경우 처리
        }
        super.onMessageReceived(remoteMessage);
    }
}
