package services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;

public class Firebase {

    private static final Logger log = LogManager.getLogger(Firebase.class);

    /**
     * Initializes the Firebase application using the firebaseConfig.json file.
     * This file should live in the same folder as the application.
     */
    public static void initialize() throws Exception {
        FileInputStream config = new FileInputStream("firebase.json");
        @SuppressWarnings("deprecation")
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(config)).build();
        FirebaseApp.initializeApp(options);
        log.debug("Initialized Firebase");
    }

    public static UserRecord verifyToken(String token) throws FirebaseAuthException {
        FirebaseAuth fb = FirebaseAuth.getInstance();
        FirebaseToken fToken = fb.verifyIdToken(token);
        return fb.getUserByEmail(fToken.getEmail());
    }

    public static void deleteUser(UserRecord r) {
        try {
            FirebaseAuth fb = FirebaseAuth.getInstance();
            fb.deleteUser(r.getUid());
            log.printf(Level.DEBUG, "Deleted user %s (%s) from Firebase", r.getUid(), r.getEmail());
        } catch (Exception e) {
            log.error("Caught error while deleting user from Firebase: ", e);
        }
    }
}