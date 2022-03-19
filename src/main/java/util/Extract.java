package util;

import com.google.firebase.auth.UserRecord;
import io.javalin.http.Context;

public class Extract {

    public static String userId(Context c) {

        UserRecord user = getUser(c);

        if (user == null)
            return null;

        String userId = user.getUid();
        if (userId == null || userId.isEmpty())
            return null;

        return userId;
    }

    public static UserRecord getUser(Context c) {
        return c.attribute("user");
    }

}
