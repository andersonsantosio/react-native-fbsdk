/**
 * Copyright (c) 2014-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this software in source code or binary form for use
 * in connection with the web services and APIs provided by Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use of
 * this software is subject to the Facebook Developer Principles and Policies
 * [http://developers.facebook.com/policy/]. This copyright notice shall be
 * included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package com.facebook.reactnative.androidsdk;

import android.net.Uri;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookException;
import com.facebook.applinks.AppLinkData;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * This is a {@link NativeModule} that allows JS to use AppLinks in Facebook Android SDK.
 */
@ReactModule(name = FBAppLinksModule.NAME)
public class FBAppLinksModule extends ReactContextBaseJavaModule {

    public static final String NAME = "FBAppLinks";

    private final ReactApplicationContext mReactContext;

    public FBAppLinksModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    public String getName() {
        return NAME;
    }

    /**
     * Get target url of the current app link.
     * @param callback Use callback to pass the current target url back to JS.
     */
    @ReactMethod
    public void getTargetUri(final Callback callback) {
        AppLinkData.fetchDeferredAppLinkData(mReactContext,
            new AppLinkData.CompletionHandler() {
                @Override
                public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                    if (appLinkData != null) {
                      Uri targetUri = appLinkData.getTargetUri();
                      callback.invoke(targetUri != null ? targetUri.toString() : "");
                    } else {
                      callback.invoke("");
                    }
                }
            }
        );
    }
}
