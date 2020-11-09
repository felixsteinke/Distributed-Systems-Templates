# Verteilte-Systeme-Prototypen


## System Bedingungen

* Das Java-Projekt ist auf Maven aufgebaut.
    * Java sollte installiert sein. (Vorgesehen ist 11+.)
    * Maven sollte installiert sein.
* Probleme könnte die Java-Version machen. Folgendes muss eventuell in der 'pom.xml' angepasst werden:
    
    
        < build >
            < plugins >
                < plugin >
                    < groupId>org.apache.maven.plugins</groupId >
                    < artifactId>maven-compiler-plugin</artifactId >
                    < configuration >
                        < source>11</source > <-- Diese Version muss eventuell angepasst werden.
                        < target>11</target > <-- Diese Version muss eventuell angepasst werden.
                    < /configuration >
                < /plugin >
            < /plugins >
        < /build >