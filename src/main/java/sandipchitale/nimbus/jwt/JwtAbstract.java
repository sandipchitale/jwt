package sandipchitale.nimbus.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

@Profile("HS256")
@Component
public abstract class JwtAbstract implements CommandLineRunner {
    private byte[] sharedSecret;

    protected abstract void printHeader();

    protected abstract void initTrustMaterial() throws IOException, NoSuchAlgorithmException;

    protected abstract JWSAlgorithm getJWSAlgorithm();

    protected abstract JWSSigner getSigner() throws JOSEException;

    private JWTClaimsSet getClaimsSet() {
        return new JWTClaimsSet.Builder()
                .subject(getJWSAlgorithm().getName() + " JWT")
                .issuer("http://localhost:8080")
                .expirationTime(new Date(new Date().getTime() + 5 * 1000))
                .build();
    }

    private JWSHeader getJWSHeader() {
        return new JWSHeader(getJWSAlgorithm());
    }

    protected abstract void printHint();

    protected abstract JWSVerifier getVerifier() throws JOSEException;

    private void printReport(String jwt) throws ParseException, JOSEException, InterruptedException {
        // On the consumer side, parse the JWS and verify its HMAC
        SignedJWT signedJWT = SignedJWT.parse(jwt);

        boolean verified = signedJWT.verify(getVerifier());

        // Retrieve / verify the JWT claims according to the app requirements
        String subject = signedJWT.getJWTClaimsSet().getSubject();
        String issuer = signedJWT.getJWTClaimsSet().getIssuer();
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("Verified? " + verified);
        System.out.println("Subject = " + subject);
        System.out.println("Issuer = " + issuer);
        System.out.println("Expiration Time = " + expirationTime);
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("Exipred? " + (new Date().after(expirationTime)? "Yes" : "No"));
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.print("Waiting for 10 seconds before checking expiry...");
        Thread.sleep(10*1000);
        System.out.println("Done.");
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("Exipred? " + (new Date().after(expirationTime) ? "Yes" : "No"));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------");
        printHeader();
        System.out.println("-------------------------------------------");

        initTrustMaterial();

        SignedJWT signedJWT = new SignedJWT(getJWSHeader(), getClaimsSet());

        // Apply the HMAC protection
        signedJWT.sign(getSigner());

        String jwt = signedJWT.serialize();

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("JWT: "+ jwt);
        System.out.println("Expiring in 5 seconds.");
        System.out.println("-------------------------------------------");

        System.out.println();
        System.out.println("Launching the browser with: https://jwt.io?access_token=" + jwt);
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
