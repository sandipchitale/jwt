package sandipchitale.nimbus.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Profile("HS256")
@Component
public class JwtHS256 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("Jwt signed and verifies with HS256 (Shared Secret)");
        System.out.println("-------------------------------------------");

        // Generate random 256-bit (32-byte) shared secret
        SecureRandom random = new SecureRandom();
        byte[] sharedSecret = new byte[32];
        random.nextBytes(sharedSecret);

        String base64SharedSecret = Base64.getEncoder().encodeToString(sharedSecret);

        System.out.println("-------------------------------------------");
        System.out.println("Base64 encoded shared secret = " + base64SharedSecret);
        System.out.println("-------------------------------------------");

        // Create HMAC signer
        JWSSigner signer = new MACSigner(sharedSecret);

        // Prepare JWT with claims set
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(JWSAlgorithm.HS256.getName() + " JWT")
                .issuer("http://localhost:8080")
                .expirationTime(new Date(new Date().getTime() + 5 * 1000))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        // Apply the HMAC protection
        signedJWT.sign(signer);

        String jwt = signedJWT.serialize();

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("JWT: "+ jwt);
        System.out.println("Expiring in 5 seconds.");
        System.out.println("-------------------------------------------");

        System.out.println();
        System.out.println("Launching the browser with: https://jwt.io?access_token=" + jwt);
        System.out.println("Make sure to select '[ ] secret base64 encoded' to verify the JWT signature.");

        SwingUtilities.invokeLater(() -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://jwt.io?access_token=" + jwt));
            } catch (IOException ignore) {
            }
        });

        // On the consumer side, parse the JWS and verify its HMAC
        signedJWT = SignedJWT.parse(jwt);

        JWSVerifier verifier = new MACVerifier(sharedSecret);

        boolean verified = signedJWT.verify(verifier);

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
}
