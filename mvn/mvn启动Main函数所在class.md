# mvn 启动

java  -Xms10g -Xmx15g -classpath /opt/maven/boot/plexus-classworlds-2.5.2.jar -Dclassworlds.conf=/opt/maven/bin/m2.conf -Dmaven.home=/opt/maven -Dmaven.multiModuleProjectDirectory=/CODE/github.com/timeloveboy/moegraph org.codehaus.plexus.classworlds.launcher.Launcher test -e

test指向如下配置里的

## pom.xml

```
  <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.5.1</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                    <compilerVersion>1.8</compilerVersion>
                    <compilerArgument>-Xlint:all</compilerArgument>
                    <showWarnings>true</showWarnings>
                    <showDeprecation>true</showDeprecation>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.1.1</version>
                <executions>
                    <execution>
                        <phase>test</phase>
                        <goals>
                            <goal>java</goal>
                        </goals>
                        <configuration>
                            <mainClass>Restful</mainClass>
                            <arguments>
                            </arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>

```