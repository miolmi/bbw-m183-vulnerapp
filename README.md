# Vulnerapp

-- A Vulnerable Sample Spring Boot Application

This application uses a relatively modern stack but is still vulernable to a set of attacks.
Featuring:

- [XSS](https://portswigger.net/web-security/cross-site-scripting)
- [SQLi](https://portswigger.net/web-security/sql-injection)
- [CSRF](https://portswigger.net/web-security/csrf)
- [SSRF](https://portswigger.net/web-security/ssrf)
- Fake Logins
- Info Exposure
- Plain Passwords
- ...

```console
./gradlew bootRun
```

## Selbstevaluation

Diskussion und Selbstevaluation:

In unserer Aufgabe ging es darum, eine unsichere Applikation zu verbessern, indem wir gängige Sicherheitsmaßnahmen implementieren. Im Folgenden werde ich die implementierten Sicherheitsmechanismen diskutieren, weitere mögliche Sicherheitsmaßnahmen erörtern und potenzielle Schwierigkeiten bei der Implementierung reflektieren.

Verwendung von korrekten REST-Verben: \
Wir haben die Applikation dahingehend überarbeitet, dass korrekte REST-Verben wie GET, POST, PUT und DELETE verwendet werden. Durch die Verwendung der entsprechenden Verben stellen wir sicher, dass die Aktionen in der Applikation gemäß den REST-Konventionen ausgeführt werden. Dies trägt zur Klarheit und Einheitlichkeit des Codes bei und reduziert potenzielle Angriffspunkte, die durch falsch verwendete oder unzureichend validierte Aktionen entstehen könnten.

Implementierung einer Authentifizierungslösung:\
Wir haben eine Authentifizierungslösung implementiert, indem wir BasicAuth verwendet haben. Benutzer müssen sich mit einem Benutzernamen und Passwort authentifizieren, um auf geschützte Ressourcen zuzugreifen. Durch die Implementierung einer grundlegenden Authentifizierungslösung schränken wir den Zugriff auf autorisierte Benutzer ein und schützen sensible Daten vor unbefugtem Zugriff.

Enforce RBAC:\
Wir haben eine Role-Based Access Control (RBAC) implementiert, um zwischen Benutzer- und Admin-Services zu unterscheiden. Dadurch können wir bestimmte Funktionalitäten und Ressourcen auf autorisierte Benutzer beschränken. Diese RBAC-Implementierung verbessert die Sicherheit der Applikation, indem sie sicherstellt, dass nur berechtigte Administratoren auf sensible Aktionen oder Daten zugreifen können.

Aktivieren von CSRF-Protection:\
Wir haben CSRF-Protection aktiviert, um vor Cross-Site Request Forgery (CSRF)-Angriffen zu schützen. Dabei generieren wir bei jedem Formular eine CSRF-Token und validieren es bei jeder Anfrage. Durch diese Implementierung stellen wir sicher, dass nur Anfragen von legitimen Quellen akzeptiert werden und bösartige Angriffe, bei denen ein Angreifer Aktionen im Namen eines authentifizierten Benutzers durchführt, verhindert werden.

Implementierung einer sicheren Passwort-Speicherung:\
Wir haben eine sichere Passwort-Speicherung implementiert, indem wir Passwörter gehasht haben. Statt die Passwörter im Klartext zu speichern, verwenden wir einen kryptografischen Hash-Algorithmus, um die Passwörter zu hashen. Dadurch wird sichergestellt, dass selbst bei einem Datenleak die Passwörter nicht im Klartext preisgegeben werden. Zusätzlich haben wir auch Passwortregeln eingeführt, um die Stärke der Passwörter zu erhöhen und einfache, leicht zu erratende Passwörter zu vermeiden.

Weitere mögliche und sinnvolle Sicherheitsmechanismen, die implementiert werden könnten:

Content Security Policy (CSP):\
Durch das Setzen des HTTP-Headers "Content-Security-Policy" können wir die Ausführ