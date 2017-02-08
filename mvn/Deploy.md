## 一 配置settings.xml
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
https://maven.apache.org/xsd/settings-1.0.0.xsd"> 
<localRepository/>
<interactiveMode/>
<usePluginRegistry/>
<offline/>
<pluginGroups/>
<servers>
    <server>
        <id>ossrh</id>
        <username>timeloveboy</username>
        <password>***</password>
    </server>
</servers>
<mirrors/>
<proxies/>
<profiles/>
<activeProfiles/>
</settings>
```
 
如果进行deploy时返回Return code is: 401错误，则需要进行用户验证或者你已经验证的信息有误。

## 二.gpg 钥匙

## 三.deploy
sudo apt install maven
mvn clean deploy -P release

## 附：官方文档
http://central.sonatype.org/pages/ossrh-guide.html
