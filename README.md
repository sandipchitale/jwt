# Springboot Nimbus Jose signing and verification example with HS256 (Shared Secret) and RS256 (Public/Private key pair)

## Description

This is a sample project to demonstrate how to sign and veirfy a JWT token with HMAC256(HS256) (Shared Secret) or (RS256) (Public/Private key pair) using Nimbus Jose library.
It also launches the browser at `https://jwt.io?access_token=JWT`.

## HS256 (Shared Secret)

Here is the sample run output for HS256 (Shared Secret) - turn on `#spring.profiles.active=HS256`:

```
-------------------------------------------
Jwt signed and verifies with HS256 (Shared Secret)
-------------------------------------------
-------------------------------------------
Base64 encoded shared secret = rWWKVDuVZnYw5vNYJw1VZNb0aMqaGf81mo0IE8hEbdo=
-------------------------------------------

-------------------------------------------
JWT: eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJIUzI1NiBKV1QiLCJleHAiOjE2OTA3NDE4NTd9.RoNhDBzqicSH2J1qFyzyGBfzugPDd8hwMZHMeCrEN5o
Expiring in 5 seconds.
-------------------------------------------

Launching the browser with: https://jwt.io?access_token=eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJIUzI1NiBKV1QiLCJleHAiOjE2OTA3NDE4NTd9.RoNhDBzqicSH2J1qFyzyGBfzugPDd8hwMZHMeCrEN5o
Make sure to select '[ ] secret base64 encoded' to verify the JWT signature.

-------------------------------------------
Verified? true
Subject = HS256 JWT
Issuer = http://localhost:8080
Expiration Time = Sun Jul 30 11:30:57 PDT 2023
-------------------------------------------

Exipred? No

-------------------------------------------
Waiting for 10 seconds before checking expiry...Done.
-------------------------------------------

Exipred? Yes
```

It also outputs the Base64 encoded sharedSecret. You can use it on jwt.io to verify the signature.

## RS256 (KeyPair)

Here is the sample run output for RS256 (SKey Pair) - turn on `#spring.profiles.active=RS256`:

```
-------------------------------------------
Jwt with RS256 Private Key (signer) / Public Key (verifier)
-------------------------------------------
-------------------------------------------
-----BEGIN RSA PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6fj3BaEGdGUPyRHr+Spz
K/qGBL/0+ILxjEL3DRSgsosb6O6HTearcAl41hmSYEtWcqfQqVOb7w9uYsxWvBl3
ybeOU2bO3SeVAhdSJpNmGfepJuHDbifOaXw/XHxRJbTwK49ucjY+RxZJ3Wr/4Ojw
fOiFhpke3K+JfFuYJcZ20u2DcBbDdRthfcU8yQ2BemZhcnHnm+EWjBe5xrNJk5Av
hf+EajaiWjjZj0+kdhUYQB6FQBRsONqGZomy20492BWvtS4fm6kTyDy5HX2sclEn
UeCo8mvJuiycZDEi1JhsQm/6Azt8rZyqbU+lA4eyqhgRI3WYVKlDWCW9Wy4x3Sv9
uQIDAQAB
-----END RSA PUBLIC KEY-----
-----BEGIN RSA PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDp+PcFoQZ0ZQ/J
Eev5KnMr+oYEv/T4gvGMQvcNFKCyixvo7odN5qtwCXjWGZJgS1Zyp9CpU5vvD25i
zFa8GXfJt45TZs7dJ5UCF1Imk2YZ96km4cNuJ85pfD9cfFEltPArj25yNj5HFknd
av/g6PB86IWGmR7cr4l8W5glxnbS7YNwFsN1G2F9xTzJDYF6ZmFyceeb4RaMF7nG
s0mTkC+F/4RqNqJaONmPT6R2FRhAHoVAFGw42oZmibLbTj3YFa+1Lh+bqRPIPLkd
faxyUSdR4Kjya8m6LJxkMSLUmGxCb/oDO3ytnKptT6UDh7KqGBEjdZhUqUNYJb1b
LjHdK/25AgMBAAECggEAEGSqa3a2p5AZ1N+Qa42psn1OnOWVietOy6I4123zER9w
Y1fXG20Gj7EyNLoSSJ9ECCuG515IJi4BcM2wDrowFkOUpsukwde599gKIFF34r44
rVSTJ1QwVlwSFRj8FzVqwHqVD/k0pYbDwnjymF+/KIP4MGU+cChNF0Kppzfqb755
3VuKGywIwFGtVCeV9BxLgQSB/qRdqIsa84fL6VoUFYEXl0BCN94LOcA8Pd6FAbuN
QCYTMICVIyAr1KExTtLRnV+qjQKEW06YoKHSng6YoQ576xR5Vlck2FeTGuphTcnI
wX3iVFSXDOth6OM6d0yW4LkbZfpMkK4GYz92XsaWwwKBgQD44XdB9HM0t/O5MD8g
p3CF3WJmAZ+0rfm6YgZnZ2ISh5E2j9TVAZT9Dy0ptUu5GKF3eOBy4vZqYo3uv/ga
W46E9cggLVfZl77hO9u4DF56cu9KYwndpEOq+X1Ljj5gPJZYNDM3nd0lBRWEjEsP
xbiX+tdIfgV7FLzSAgpJIeL8jwKBgQDwqlPTYULeurM238XZ/17zmlZy7B/b7jlO
g+HU7GRfOx2P1YC+6LIcH8CeOTtQvOE2MiJY15vbglhHOdvjnQFZXQwJyBKncZCp
t0rbgxWodUA9dXuZqJeeoItELQqiw7vP4YZLwh5/9in38C5oyd6V2AMrENn/dUiU
dEDDVlAVNwKBgDqS1b5wCa0enf23nSPSRGk88SuQtkWPrHZxG5C6hBpYTZTJxQ1c
kfP83mQyht/JLDcx+6EOSXBAQacvBvUtFx2zuqXYBhTv4bygmdV3E8U9x5N6O5gu
UL6seRCPKZ78o2We427zeFh+GdJYMlJD9m3OW1x0TFXrS4UhdcpAb2uBAoGAeuii
EuwzOIjlCftSkjjnh0WlLOAGjU2mmsCjzxhDUpdgS5rgO5+fhtl4h9X2ww8TfZgx
DmpewMZay1VzRVVj4P2yLjStwjIvjV18r4rMvtHS0xBy5VDCzlJzjYfrTKDmBA7L
hVlINlXDrUpOiVm/gYtmYtIiY2LEhHyyrpAgKhUCgYEAm3cMm1WASaCiQZFjjZVQ
kQe7KgMz2Fq/+/z6DQCKL/9vyxzjEM0+uxnW84ZP0tYK6kcvGNP4O0UfQmE4uN2k
xotS0DH1EK8/m6TXN9OEVJEP91Gi9Imzjylffn/1GA5YkQME6dcDL+tWKGe5lZGH
Gt9HeWCczsmSA041dhxBh4c=
-----END RSA PRIVATE KEY-----
-------------------------------------------

-------------------------------------------
JWT: eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJSUzI1NiBKV1QiLCJleHAiOjE2OTA3NDE4Njl9.4sMTi2oEe4-WnN1GwPkchhez-1wRblYYr_g8DiNb_GqY6GLpgcF73uiVk3FJV037IDD-VygQdY2MeHqrk6e0KqAVLraS170m1w5efUn2Hw_sjYSXynBKC6JLhK7IMoOLhZE2iX3Fbh-iq-PZYmhJ5o4LC6U3Cl0W9GGqNl3z12n06_uyYA5V0gnHukzwDffnWB5wrzDG_nPcTVkDbnjyn0qgIljK2t7shZ3ZgkGOLU47tsLc_1QNZZi_7DLDiAAx1Vf3hTk_z1KhThALKdIU7TtOSVPA1gG_l4suBNxRUtS2SU9D-Py7cFk5Ky8EqGqWwdJQYyWshiqer8SIcD_xOA
Expiring in 5 seconds.
-------------------------------------------

Launching the browser with: https://jwt.io?access_token=eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJSUzI1NiBKV1QiLCJleHAiOjE2OTA3NDE4Njl9.4sMTi2oEe4-WnN1GwPkchhez-1wRblYYr_g8DiNb_GqY6GLpgcF73uiVk3FJV037IDD-VygQdY2MeHqrk6e0KqAVLraS170m1w5efUn2Hw_sjYSXynBKC6JLhK7IMoOLhZE2iX3Fbh-iq-PZYmhJ5o4LC6U3Cl0W9GGqNl3z12n06_uyYA5V0gnHukzwDffnWB5wrzDG_nPcTVkDbnjyn0qgIljK2t7shZ3ZgkGOLU47tsLc_1QNZZi_7DLDiAAx1Vf3hTk_z1KhThALKdIU7TtOSVPA1gG_l4suBNxRUtS2SU9D-Py7cFk5Ky8EqGqWwdJQYyWshiqer8SIcD_xOA
Use the Public and Private keys above to verify the JWT signature.

-------------------------------------------
Verified? true
Subject = RS256 JWT
Issuer = http://localhost:8080
Expiration Time = Sun Jul 30 11:31:09 PDT 2023
-------------------------------------------

Exipred? No

-------------------------------------------
Waiting for 10 seconds before checking expiry...Done.
-------------------------------------------

Exipred? Yes
```

It also outputs the Public and Private Keys. You can use them on jwt.io to verify the signature.



