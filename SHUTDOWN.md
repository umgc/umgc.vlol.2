# Application Shutdown Procedure

>If you are using Windows, ensure you have installed the true version of cURL, located at [https://curl.haxx.se/](https://curl.haxx.se/ "curl download site")
>If you are using Windows, you must use "curl.exe" instead of "curl"; "curl" is an alias for Microsoft's Invoke-WebRequest, and does not have the same functionality

    curl.exe -u admin@vlol.gov:P@ssW0rd  -v -X POST http://localhost:5000/actuator/shutdown

    PS C:\Users\Rob\Documents\NetBeansProjects\SWEN670\VLOL> curl.exe -u actuator:P@ssW0rd  -v -X POST http://localhost:5000/actuator/shutdown
    *   Trying ::1...
    * TCP_NODELAY set
    * Connected to localhost (::1) port 5000 (#0)
    * Server auth using Basic with user 'actuator'
    > POST /actuator/shutdown HTTP/1.1
    > Host: localhost:5000
    > Authorization: Basic YWN0dWF0b3I6UEBzc1cwcmQ=
    > User-Agent: curl/7.55.1
    > Accept: */*
    >
    < HTTP/1.1 401
    < Set-Cookie: remember-me=; Max-Age=0; Expires=Thu, 01-Jan-1970 00:00:10 GMT; Path=/
    * Authentication problem. Ignoring this.
    < WWW-Authenticate: Basic realm="Realm"
    < X-Content-Type-Options: nosniff
    < X-XSS-Protection: 1; mode=block
    < Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    < Pragma: no-cache
    < Expires: 0
    < Content-Type: application/json
    < Transfer-Encoding: chunked
    < Date: Sat, 18 Jul 2020 21:06:05 GMT
    <
    {"timestamp":1595106365298,"status":401,"error":"Unauthorized","message":"Unauthorized","path":"/actuator/shutdown"}* Connection #0 to host localhost left intact
    PS C:\Users\Rob\Documents\NetBeansProjects\SWEN670\VLOL> curl.exe -u admin@vlol.gov:P@ssW0rd  -v -X POST http://localhost:5000/actuator/shutdown
    *   Trying ::1...
    * TCP_NODELAY set
    * Connected to localhost (::1) port 5000 (#0)
    * Server auth using Basic with user 'admin@vlol.gov'
    > POST /actuator/shutdown HTTP/1.1
    > Host: localhost:5000
    > Authorization: Basic YWRtaW5AdmxvbC5nb3Y6UEBzc1cwcmQ=
    > User-Agent: curl/7.55.1
    > Accept: */*
    >
    < HTTP/1.1 200
    < Set-Cookie: JSESSIONID=41EE2A21EA1502A037580F75ED8F69F9; Path=/; HttpOnly
    < X-Content-Type-Options: nosniff
    < X-XSS-Protection: 1; mode=block
    < Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    < Pragma: no-cache
    < Expires: 0
    < Content-Type: application/vnd.spring-boot.actuator.v3+json
    < Transfer-Encoding: chunked
    < Date: Sat, 18 Jul 2020 21:13:36 GMT
    <
    {"message":"Shutting down, bye..."}* Connection #0 to host localhost left intact
    PS C:\Users\Rob\Documents\NetBeansProjects\SWEN670\VLOL>