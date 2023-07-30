# Springboot Nimbus Jose signing and verification example with HS256 (Shared Secret) and RS256 (KeyPair)

## Description

This is a sample project to demonstrate how to sign and veirfy a JWT token with HMAC256(HS256) or (RS256) using Nimbus Jose library.
It also launches the browser at `https://jwt.io?access_token=JWT`.

## HS256 (Shared Secret)

Here is the sample run output for HS256 (Shared Secret) - turn on `#spring.profiles.active=HS256`:

```
-------------------------------------------
Base64 encoded shared secret = XLVz317PaJOCXwKRNqrer+S+GtsL2qWvXETcRKQ4eiM=
-------------------------------------------

-------------------------------------------
JWT: eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJIUzI1NiBKV1QiLCJleHAiOjE2OTA2ODg5OTZ9.ZAUxLt2VXgJTNWE7xgzPe1FfS6FJuwJb7frZfwJKTHU
-------------------------------------------

Launched browser with: https://jwt.io?access_token=eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJIUzI1NiBKV1QiLCJleHAiOjE2OTA2ODg5OTZ9.ZAUxLt2VXgJTNWE7xgzPe1FfS6FJuwJb7frZfwJKTHU
Make sure to select '[ ] secret base64 encoded' to verify the JWT signature.

-------------------------------------------
Verified? true
Subject = HS256 JWT
Issuer = http://localhost:8080
Expiration Time = Sat Jul 29 20:49:56 PDT 2023
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
-----BEGIN RSA PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtiQkCCHHW9oYUQTHdy8B
2WGMpQQG+6wXnaK7n1Z4G5mwxlBoz+KphyrecSzHnjksJQCXg9HChkFRNPZq4FX3
3yQHiMZvQgk5xnwxvh7AU1qXU0EfQupySkNlfn2Ytd+VqUuHIyvlkFUvan7C5Pkt
BhwUe7M/pGnSGeJr5iVSRnzo/sqUiCTzYCsUe9yEoTOz4y6wtqf24iFQ2hbp/iv1
1cnYW6Kn0GlDqN8QOqw0e5Ai0KxxiklYMlYP3URm8n4YZf7yv0SaHZkNHfattqFK
Nv2swEmRB12SLf7bWexWs9iUGOhLz8wEmMgMhXlKR3TDHmjqJU/uzgCRKQorufXS
pwIDAQAB
-----END RSA PUBLIC KEY-----
-----BEGIN RSA PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2JCQIIcdb2hhR
BMd3LwHZYYylBAb7rBedorufVngbmbDGUGjP4qmHKt5xLMeeOSwlAJeD0cKGQVE0
9mrgVfffJAeIxm9CCTnGfDG+HsBTWpdTQR9C6nJKQ2V+fZi135WpS4cjK+WQVS9q
fsLk+S0GHBR7sz+kadIZ4mvmJVJGfOj+ypSIJPNgKxR73IShM7PjLrC2p/biIVDa
Fun+K/XVydhboqfQaUOo3xA6rDR7kCLQrHGKSVgyVg/dRGbyfhhl/vK/RJodmQ0d
9q22oUo2/azASZEHXZIt/ttZ7Faz2JQY6EvPzASYyAyFeUpHdMMeaOolT+7OAJEp
Ciu59dKnAgMBAAECggEAVrOgdUCDEh2gupxQSNMXCb/OP9TJcC6stSx8LG5wUQ8Z
X2VyZxbJxR5qcawzv4UOlvSPqz+D3f2kptXv7SbvDYisj3aKCoCywGFpGkuwGF2E
sCxNMcDBM8mePBaURWsPZl2eD+d66Pgr0IFTy8QzIvfl5jZewqhey1SFMTIt1oAb
rdviNewy/Xtt/Hi6TuEe5i0Ajumg7zuPFXQOVKETyfa7cGVuhZc+TIHQpAQ8eEK8
jrKeHcMlRAZjtFq/DO9AZYxyGLinKdV5PjII/z10Qtzqg+gywmUuCdbP6WyY9FAg
3opqXfZQp7YuDfrFTrDqEsD+5EhwYV9kEL5gVrpa1QKBgQDEFuDUI0OMh04zweW1
86b1SB0fhxxKVACJKJdMdUck9eZ/THdsQmg81Cx1tAYWZT9Q7eQIn+dal+hwpoU/
v3n2DPyBhJ87OA2o6qQO5IcC7TEdmUUq31PJK8d3Ix9i6feZPS9ElYx93Y8T4sTA
+lJvDEmuxZaIbtgkEDC8pONEBQKBgQDtyk4egKvGo5G8PTGHC+QmJKPFutEvRDEM
0BPCzQ3TiX6foEkj55l960istCkgkL+NxJuXBKPV80DZDE88VzbzllBqPHUcoxQX
W48Obc37k6Ce81Ri2kpfFsP6wqK8vj7+wCw3oLg9i6Qgs0ZQfshw943p9qnfaZgC
J5zNh2sHuwKBgDtL180OpNyYBfPszHQLLA+8MOJZzxpC8K6JUcdTJ6JroboY+3wH
UNIkLxWgzL50+4KTjYN2J1Xj3exCzsSKOG1JXAomeqhDuQ29dsLyJEmkRFf5A0uF
oSaSUI0cV99ndPIn6GVK++ML3rpatrjMeaUPCDELmwvCySWOp85D5l2pAoGBAMei
efVX1188QgUEZwlW7WMsjaZaknaZTIjSRFTebIY1P+5p1h3judSibPA5jPG/NGKk
NnASQC5GPCI9GGSzX/ofL0IRocy6CQsXHkJh43Yb2dS4uxcG5ztv1Hc5YwEMw7yC
wFyvfR6GlvOxLw4zcrqMy0MqiDF96FWexiEzWKMzAoGAdyzMxEcul3bvkw6YAH5k
1nKvlCZ5lVGCXwfCrAlF9UzcVgBptUD8NXNVSkXnTPxymRVO3pw0fQHRNs01eeG5
6tiO22Z7IawlIQGT6DKYLfkm5HYnhrC8G1g4jpb0pTj2rZ5ULyk3Xz/yJKjnhJZA
OQuWYTIbWW5t28/04mN6kJ0=
-----END RSA PRIVATE KEY-----
-------------------------------------------

-------------------------------------------
JWT: eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJSUzI1NiBKV1QiLCJleHAiOjE2OTA2OTY2MzJ9.Sw8bw1jny3cAFDMnwPcTtWBoznGSLrTF8fzTNK_qqSQVU8T0wirHZZGNGjx8Z7YrZOGxvybbEhyPcfDd_dsmGjEbYdikLh_LkN8t_R5HYpdWaIFIS-ddSIvDaTi9ugxltUg8x-ST79BfH_EyGQapBtL1ZKscaJ5yV1-pWlEOZH9C-oBXCil867nvpd2QfPNJ5fQLrTczwXwa-8cptOJxWzOAaKz-wR_xOIqAt3jOFe-2LnBWucFaKBRVtt3hY1YKdBZd1jV3iUi9ZS5RUFjdIoAT408ZPU3zKA3fA8JwxhgQ87qZUcLy4VF-vDv6aVk4nwq-vMn7AbBMILZ7hj4yvg
-------------------------------------------

Launched browser with: https://jwt.io?access_token=eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJSUzI1NiBKV1QiLCJleHAiOjE2OTA2OTY2MzJ9.Sw8bw1jny3cAFDMnwPcTtWBoznGSLrTF8fzTNK_qqSQVU8T0wirHZZGNGjx8Z7YrZOGxvybbEhyPcfDd_dsmGjEbYdikLh_LkN8t_R5HYpdWaIFIS-ddSIvDaTi9ugxltUg8x-ST79BfH_EyGQapBtL1ZKscaJ5yV1-pWlEOZH9C-oBXCil867nvpd2QfPNJ5fQLrTczwXwa-8cptOJxWzOAaKz-wR_xOIqAt3jOFe-2LnBWucFaKBRVtt3hY1YKdBZd1jV3iUi9ZS5RUFjdIoAT408ZPU3zKA3fA8JwxhgQ87qZUcLy4VF-vDv6aVk4nwq-vMn7AbBMILZ7hj4yvg
Use the Public and Private keys above to verify the JWT signature.

-------------------------------------------
Verified? true
Subject = RS256 JWT
Issuer = http://localhost:8080
Expiration Time = Sat Jul 29 22:57:12 PDT 2023
-------------------------------------------

Exipred? No

-------------------------------------------
Waiting for 10 seconds before checking expiry...Done.
-------------------------------------------

Exipred? Yes
```

It also outputs the Public and Private Keys. You can use them on jwt.io to verify the signature.



