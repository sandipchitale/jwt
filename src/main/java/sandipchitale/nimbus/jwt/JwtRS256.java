package sandipchitale.nimbus.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Profile("RS256")
@Component
public class JwtRS256 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("Jwt with RS256 Private Key (signer) / Public Key (verifier)");
        System.out.println("-------------------------------------------");

        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(System.out));
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey  publicKey = (RSAPublicKey) keyPair.getPublic();


        System.out.println("-------------------------------------------");
        PemObject pemObject;
        pemObject = new PemObject("RSA PUBLIC KEY", publicKey.getEncoded());
        pemWriter = new PemWriter(new OutputStreamWriter(System.out));
        pemWriter.writeObject(pemObject);
        pemWriter.flush();
        pemObject = new PemObject("RSA PRIVATE KEY", privateKey.getEncoded());
        pemWriter.writeObject(pemObject);
        pemWriter.flush();
        System.out.println("-------------------------------------------");

        // Create RSA signer
        JWSSigner signer = new RSASSASigner(privateKey);

        // Prepare JWT with claims set
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(JWSAlgorithm.RS256.getName() + " JWT")
                .issuer("http://localhost:8080")
                .expirationTime(new Date(new Date().getTime() + 5 * 1000))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

        // Apply the RSA protection
        signedJWT.sign(signer);

        String jwt = signedJWT.serialize();

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("JWT: "+ jwt);
        System.out.println("Expiring in 5 seconds.");
        System.out.println("-------------------------------------------");

        System.out.println();
        System.out.println("Launching the browser with: https://jwt.io?access_token=" + jwt);
        System.out.println("Use the Public and Private keys above to verify the JWT signature.");

        SwingUtilities.invokeLater(() -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://jwt.io?access_token=" + jwt));
            } catch (IOException ignore) {
            }
        });

        // On the consumer side, parse the JWS and verify its RSA
        signedJWT = SignedJWT.parse(jwt);

        JWSVerifier verifier = new RSASSAVerifier(publicKey);

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
