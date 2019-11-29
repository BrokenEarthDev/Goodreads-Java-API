/*
Copyright 2019 BrokenEarthDev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.github.brokenearthdev.goodreadsjapi.authentication;

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;
import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.http.javanet.NetHttpTransport;
import io.github.brokenearthdev.goodreadsjapi.GoodreadsAPI;

import java.io.IOException;

public class GoodreadsAuthentication implements GoodreadsOauth {

    private OAuthHmacSigner signer;
    private OAuthGetTemporaryToken token;
    private OAuthCredentialsResponse response;
    private String oauthLink;
    private String key;
    private String secret;

    public GoodreadsAuthentication(String key, String secret) throws IOException {
        this.key = key;
        this.secret = secret;
        signer = new OAuthHmacSigner();
        token = new OAuthGetTemporaryToken(GoodreadsAPI.TOKEN_SERVER_URL);
        signer.clientSharedSecret = secret;
        token.signer = signer;
        token.consumerKey = key;
        token.transport = new NetHttpTransport();
        response = token.execute();
        oauthLink = buildAuthLink();
    }


    private String buildAuthLink() {
        OAuthAuthorizeTemporaryTokenUrl var = new OAuthAuthorizeTemporaryTokenUrl(GoodreadsAPI.AUTHENTICATE_URL);
        var.temporaryToken = response.token;
        return var.build();
    }

    @Override
    public String getAuthLink() {
        return oauthLink;
    }

    @Override
    public OAuthHmacSigner getSigner() {
        return signer;
    }

    @Override
    public OAuthGetTemporaryToken getTemporaryToken() {
        return token;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public OAuthCredentialsResponse getAccessTokenResponse() {
        return response;
    }
}