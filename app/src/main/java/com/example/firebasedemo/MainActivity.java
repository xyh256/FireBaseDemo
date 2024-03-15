/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.firebasedemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "From: ");
        Log.e(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.message_txt);

       findViewById(R.id.logTokenButton).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d(TAG, "Onclick: ");
               FirebaseMessaging.getInstance().getToken()
                       .addOnCompleteListener(new OnCompleteListener<String>() {
                           @Override
                           public void onComplete(@NonNull Task<String> task) {
                               if (!task.isSuccessful()) {
                                   Log.e(TAG, "Fetching FCM registration token failed", task.getException());
                                   return;
                               }

                               // Get new FCM registration token
                               String token = task.getResult();
                               textView.setText(token);
                               // Log and toast
                               String msg = getString(R.string.msg_token_fmt, token);
                               Log.e(TAG, msg);
                               Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                           }
                       });
           }
       });
//
//        if (getIntent().getExtras() != null) {
//            for (String key : getIntent().getExtras().keySet()) {
//                Object value = getIntent().getExtras().get(key);
//                Log.d(TAG, "Key: " + key + " Value: " + value);
//            }
//        }
//        // [END handle_data_extras]
//
//        binding.subscribeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "Subscribing to weather topic");
//                // [START subscribe_topics]
//                FirebaseMessaging.getInstance().subscribeToTopic("weather")
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                String msg = getString(R.string.msg_subscribed);
//                                if (!task.isSuccessful()) {
//                                    msg = getString(R.string.msg_subscribe_failed);
//                                }
//                                Log.d(TAG, msg);
//                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                // [END subscribe_topics]
//            }
//        });
//
//        binding.logTokenButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get token
//                // [START log_reg_token]
//                FirebaseMessaging.getInstance().getToken()
//                    .addOnCompleteListener(new OnCompleteListener<String>() {
//                        @Override
//                        public void onComplete(@NonNull Task<String> task) {
//                          if (!task.isSuccessful()) {
//                            Log.e(TAG, "Fetching FCM registration token failed", task.getException());
//                            return;
//                          }
//
//                          // Get new FCM registration token
//                          String token = task.getResult();
//
//                          // Log and toast
//                          String msg = getString(R.string.msg_token_fmt, token);
//                          Log.e(TAG+"2", msg);
//                          Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                // [END log_reg_token]
//            }
//        });

//        askNotificationPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
