package sandipchitale.nimbus.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static java.lang.System.out;

@Profile("RS256")
@Component
public class JwtRS256 extends JwtAbstract implements CommandLineRunner {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    protected void printHeader() {
        out.println("Jwt with RS256 Private Key (signer) / Public Key (verifier)");
    }

    protected void initTrustMaterial() throws IOException, NoSuchAlgorithmException {
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(out));
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();

        out.println(SEPARATOR);
        PemObject pemObject;
        pemObject = new PemObject("RSA PUBLIC KEY", publicKey.getEncoded());
        pemWriter.writeObject(pemObject);
        pemWriter.flush();
        pemObject = new PemObject("RSA PRIVATE KEY", privateKey.getEncoded());
        pemWriter.writeObject(pemObject);
        pemWriter.flush();
        out.println(SEPARATOR);
    }

    protected JWSAlgorithm getJWSAlgorithm() {
        return JWSAlgorithm.RS256;
    }

    protected JWSSigner getSigner() throws JOSEException {
        // Create RSA signer
        return new RSASSASigner(privateKey);
    }

    protected void printHint() {
        out.println("Use the Public and Private keys above to verify the JWT signature.");
    }


    protected JWSVerifier getVerifier() throws JOSEException {
        return new RSASSAVerifier(publicKey);
    }
}
