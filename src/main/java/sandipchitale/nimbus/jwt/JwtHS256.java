package sandipchitale.nimbus.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static java.lang.System.out;

@Profile("HS256")
@Component
public class JwtHS256 extends JwtAbstract {
    private byte[] sharedSecret;

    protected void printHeader() {
        out.println("Jwt signed and verifies with HS256 (Shared Secret)");
    }

    protected void initTrustMaterial() throws IOException, NoSuchAlgorithmException {
        // Generate random 256-bit (32-byte) shared secret
        SecureRandom random = new SecureRandom();
        sharedSecret = new byte[32];
        random.nextBytes(sharedSecret);

        String base64SharedSecret = Base64.getEncoder().encodeToString(sharedSecret);

        out.println(SEPARATOR);
        out.println("Base64 encoded shared secret = " + base64SharedSecret);
        out.println(SEPARATOR);
    }

    protected JWSAlgorithm getJWSAlgorithm() {
        return JWSAlgorithm.HS256;
    }

    protected JWSSigner getSigner() throws JOSEException {
        return new MACSigner(sharedSecret);
    }

    protected JWSHeader getJWSHeader() {
        return new JWSHeader(getJWSAlgorithm());
    }

    protected void printHint() {
        out.println("Hint: Make sure to select '[ ] secret base64 encoded' to verify the JWT signature.");
    }

    protected JWSVerifier getVerifier() throws JOSEException {
        return new MACVerifier(sharedSecret);
    }
}
