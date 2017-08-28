package helpers;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

public class fbConnection {

    public String getFBAccessToken() {
        FacebookClient.AccessToken accessToken = new DefaultFacebookClient(Version.VERSION_2_5).obtainAppAccessToken("707945306015098", "f03bad7603258756b34580e48f0111f3");
        return accessToken.getAccessToken();
    }
}
