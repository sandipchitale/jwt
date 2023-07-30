package sandipchitale.nimbus.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.boot.CommandLineRunner;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import static java.lang.System.out;

public abstract class JwtAbstract implements CommandLineRunner {
    protected static final String SEPARATOR = "-".repeat(160);
    private final int expirySeconds = 5;

    protected abstract void printHeader();

    protected abstract void initTrustMaterial() throws IOException, NoSuchAlgorithmException;

    protected abstract JWSAlgorithm getJWSAlgorithm();

    protected abstract JWSSigner getSigner() throws JOSEException;

    private JWTClaimsSet getClaimsSet() {
        return new JWTClaimsSet.Builder()
                .subject(getJWSAlgorithm().getName() + " JWT")
                .issuer("http://localhost:8080")
                .expirationTime(new Date(new Date().getTime() + expirySeconds * 1000))
                .build();
    }

    private JWSHeader getJWSHeader() {
        return new JWSHeader(getJWSAlgorithm());
    }

    protected abstract void printHint();

    protected abstract JWSVerifier getVerifier() throws JOSEException;

    private void printReport(String jwt) throws ParseException, JOSEException, InterruptedException {
        // On the consumer side, parse the JWS and verify its signature
        // Retrieve
        SignedJWT signedJWT = SignedJWT.parse(jwt);

        // Verify the JWT
        boolean verified = signedJWT.verify(getVerifier());
        out.println();
        out.println(SEPARATOR);
        out.println("Verified? " + verified);

        // Verify claims according to the app requirements
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        String subject = jwtClaimsSet.getSubject();
        String issuer = jwtClaimsSet.getIssuer();
        Date expirationTime = jwtClaimsSet.getExpirationTime();
        out.println("Subject = " + subject);
        out.println("Issuer = " + issuer);
        out.println("Expiration Time = " + expirationTime);
        out.println(SEPARATOR);
        out.println();
        out.println("Expired (Expect No)? " + (new Date().after(expirationTime)? "Yes" : "No"));
        out.println();
        out.println(SEPARATOR);
        out.print("Waiting for " + (2 * expirySeconds) + " seconds before checking expiry...");
        Thread.sleep(2 * expirySeconds * 1000);
        out.println("Done.");
        out.println(SEPARATOR);
        out.println();
        out.println("Expired (Expect Yes)? " + (new Date().after(expirationTime) ? "Yes" : "No"));
    }

    @Override
    public void run(String... args) throws Exception {
        out.println();
        out.println();
        out.println(SEPARATOR);
        printHeader();
        out.println(SEPARATOR);

        initTrustMaterial();

        SignedJWT signedJWT = new SignedJWT(getJWSHeader(), getClaimsSet());

        // Apply the HMAC protection
        signedJWT.sign(getSigner());

        String jwt = signedJWT.serialize();

        out.println();
        out.println(SEPARATOR);
        out.println("JWT: "+ jwt);
        out.println("Expiring in " + expirySeconds + " seconds.");
        out.println(SEPARATOR);

        out.println();
        out.println("Launching the browser with: https://jwt.io?access_token=" + jwt);
        printHint();

        SwingUtilities.invokeLater(() -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://jwt.io?access_token=" + jwt));
            } catch (IOException ignore) {
            }
        });

        printReport(jwt);
    }
}
